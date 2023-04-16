package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 配置信息表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ParamDTO {

    /**
     *
     */
    private int strategyId;

    /**
     *
     */
    private String strategyName;

    /**
     *
     */
    private String ownerType;

    /**
     *
     */
    private String owner;

    /**
     *
     */
    private String paramName;

    /**
     *
     */
    private String paramType;

    /**
     *
     */
    private String paramValue;

}
