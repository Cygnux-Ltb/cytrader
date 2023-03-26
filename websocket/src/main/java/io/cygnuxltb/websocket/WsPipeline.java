package io.cygnuxltb.websocket;

import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqReceiver;

public class WsPipeline {

    static {

        ZmqConfigurator configurator = ZmqConfigurator.tcp(7080).ioThreads(1);

        ZmqReceiver receiver;

    }


}
