package io.cygnus.exchange.core.processors;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.DataProvider;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.Sequencer;

import io.cygnus.exchange.core.common.cmd.OrderCommand;
import io.cygnus.exchange.core.common.enums.CoreWaitStrategy;

public final class TwoStepSlaveProcessor implements EventProcessor {
	
    private static final int IDLE = 0;
    private static final int HALTED = IDLE + 1;
    private static final int RUNNING = HALTED + 1;

    private final AtomicInteger running = new AtomicInteger(IDLE);
    private final DataProvider<OrderCommand> dataProvider;
    private final SequenceBarrier sequenceBarrier;
    private final WaitSpinningHelper waitSpinningHelper;
    private final SimpleEventHandler eventHandler;
    private final Sequence sequence = new Sequence(Sequencer.INITIAL_CURSOR_VALUE);
    private final ExceptionHandler<? super OrderCommand> exceptionHandler;
    private final String name;

    private long nextSequence = -1;

    public TwoStepSlaveProcessor(final RingBuffer<OrderCommand> ringBuffer,
                                 final SequenceBarrier sequenceBarrier,
                                 final SimpleEventHandler eventHandler,
                                 final ExceptionHandler<? super OrderCommand> exceptionHandler,
                                 final String name) {
    	
        this.dataProvider = ringBuffer;
        this.sequenceBarrier = sequenceBarrier;
        this.waitSpinningHelper = new WaitSpinningHelper(ringBuffer, sequenceBarrier, 0, CoreWaitStrategy.SECOND_STEP_NO_WAIT);
        this.eventHandler = eventHandler;
        this.exceptionHandler = exceptionHandler;
        this.name = name;
    }

    @Override
    public Sequence getSequence() {
        return sequence;
    }

    @Override
    public void halt() {
        running.set(HALTED);
        sequenceBarrier.alert();
    }

    @Override
    public boolean isRunning() {
        return running.get() != IDLE;
    }

    /**
     * It is ok to have another thread rerun this method after a halt().
     *
     * @throws IllegalStateException if this object instance is already running in a thread
     */
    @Override
    public void run() {
        if (running.compareAndSet(IDLE, RUNNING)) {
            sequenceBarrier.clearAlert();
        } else if (running.get() == RUNNING) {
            throw new IllegalStateException("Thread is already running (S)");
        }

        nextSequence = sequence.get() + 1L;
    }

    public void handlingCycle(final long processUpToSequence) {
        while (true) {
            OrderCommand event = null;
            try {
                long availableSequence = waitSpinningHelper.tryWaitFor(nextSequence);

                // process batch
                while (nextSequence <= availableSequence && nextSequence < processUpToSequence) {
                    event = dataProvider.get(nextSequence);
                    eventHandler.onEvent(nextSequence, event); // TODO check if nextSequence is correct (not nextSequence+-1)?
                    nextSequence++;
                }

                // exit if finished processing entire group (up to specified sequence)
                if (nextSequence == processUpToSequence) {
                    sequence.set(processUpToSequence - 1);
                    waitSpinningHelper.signalAllWhenBlocking();
                    return;
                }

            } catch (final Throwable ex) {
                exceptionHandler.handleEventException(ex, nextSequence, event);
                sequence.set(nextSequence);
                waitSpinningHelper.signalAllWhenBlocking();
                nextSequence++;
            }
        }
    }

    @Override
    public String toString() {
        return "TwoStepSlaveProcessor{" + name + "}";
    }

}