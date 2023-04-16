package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 策略表
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class StrategyDTO {

    private int strategyId;

    private String strategyName;

    private String strategyOwner;

    private String strategyInfo;

}
