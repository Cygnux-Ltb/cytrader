package io.cygnuxltb.protocol.http.inbound;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class ControlCommand {

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Init {
        private int sysId;
        private int tradingDay;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class InitFinish {
        private int sysId;
    }


}
