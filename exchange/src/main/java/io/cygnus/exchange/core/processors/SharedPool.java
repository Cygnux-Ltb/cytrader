package io.cygnus.exchange.core.processors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import io.cygnus.exchange.core.common.MatcherTradeEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SharedPool {

    private final BlockingQueue<MatcherTradeEvent> eventChainsBuffer;

    @Getter
    private final int chainLength;

    public static SharedPool createTestSharedPool() {
        return new SharedPool(8, 4, 256);
    }

    /**
     * Create new shared pool
     *
     * @param poolMaxSize     - max size of pool. Will skip new chains if chains buffer is full.
     * @param poolInitialSize - initial number of pre-generated chains. Recommended to set higher than number of modules - (RE+ME)*2.
     * @param chainLength     - target chain length. Longer chain means rare requests for new chains. However longer chains can cause event placeholders starvation.
     */
    public SharedPool(final int poolMaxSize, final int poolInitialSize, final int chainLength) {

        if (poolInitialSize > poolMaxSize) {
            throw new IllegalArgumentException("too big poolInitialSize");
        }

        this.eventChainsBuffer = new LinkedBlockingQueue<>(poolMaxSize);
        this.chainLength = chainLength;

        for (int i = 0; i < poolInitialSize; i++) {
            this.eventChainsBuffer.add(MatcherTradeEvent.createEventChain(chainLength));
        }
    }

    /**
     * Request next chain from buffer
     * Threadsafe
     *
     * @return chain, otherwise null
     */
    public MatcherTradeEvent getChain() {
        MatcherTradeEvent poll = eventChainsBuffer.poll();
        log.debug("<<< POLL CHAIN HEAD  size={}", poll == null ? 0 : poll.getChainSize());
        if (poll == null) {
            poll = MatcherTradeEvent.createEventChain(chainLength);
        }

        return poll;
    }

    /**
     * Offers next chain.
     * Threadsafe (single producer safety is sufficient)
     *
     * @param head - pointer to the first element
     */
    public void putChain(MatcherTradeEvent head) {
        boolean offer = eventChainsBuffer.offer(head);
        log.debug(">>> OFFER CHAIN HEAD  size={} orrder={}", head.getChainSize(), offer);
    }

}
