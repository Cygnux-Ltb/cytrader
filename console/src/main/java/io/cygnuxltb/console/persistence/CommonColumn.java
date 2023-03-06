package io.cygnuxltb.console.persistence;

public interface CommonColumn {

    /**
     * 策略ID
     */
    String STRATEGY_ID = "strategy_id";

    /**
     * 策略名称
     */
    String STRATEGY_NAME = "strategy_name";

    /**
     * 经纪商
     */
    String BROKER_ID = "broker_id";

    /**
     * 投资者ID
     */
    String INVESTOR_ID = "investor_id";

    /**
     * 账户ID
     */
    String ACCOUNT_ID = "account_id";

    /**
     * 子账户ID (逻辑账户)
     */
    String SUB_ACCOUNT_ID = "sub_account_id";

    /**
     * 用户ID
     */
    String USER_ID = "user_id";

    /**
     * 交易标的代码
     */
    String INSTRUMENT_CODE = "instrument_code";

    /**
     * 交易日
     */
    String TRADING_DAY = "trading_day";

}
