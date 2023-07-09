package io.cygnuxltb.protocol.http.inbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LogInfo {

    private long epochTime;

    private String logTime;

    private String logLevel;

    private String logTitle;

    private String logText;

}
