package io.cygnuxltb.console.controller.util;

import io.mercury.common.character.Charsets;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

public final class ControllerUtil {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ControllerUtil.class);

    /**
     * 检查参数列表
     *
     * @param objs Object...
     * @return boolean
     */
    public static boolean paramIsNull(Object... objs) {
        for (Object obj : objs)
            if (obj != null)
                return false;
        return true;
    }

    private static String getBody(HttpServletRequest request) {
        try {
            return IOUtils.toString(request.getInputStream(), Charsets.UTF8);
        } catch (IOException e) {
            log.error("get body content has IOException -> {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> T bodyToObject(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toObject(body, clazz);
    }

    public static <T> List<T> bodyToList(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toList(body, clazz);
    }


    public static boolean illegalStrategyId(int strategyId, Logger log) {
        if (strategyId < 0) {
            log.error("illegal param -> strategyId=={}", strategyId);
            return true;
        }
        return false;
    }

    public static boolean illegalStrategyName(String strategyName, Logger log) {
        if (StringSupport.isNullOrEmpty(strategyName)) {
            log.error("illegal param -> strategyName=={}", strategyName);
            return true;
        }
        return false;
    }

    public static boolean illegalInvestorId(String investorId, Logger log) {
        if (StringSupport.isNullOrEmpty(investorId)) {
            log.error("illegal param -> investorId=={}", investorId);
            return true;
        }
        return false;
    }

    public static boolean illegalInstrumentCode(String instrumentCode, Logger log) {
        if (StringSupport.isNullOrEmpty(instrumentCode)) {
            log.error("illegal param -> instrumentCode=={}", instrumentCode);
            return true;
        }
        return false;
    }

    public static boolean illegalTradingDay(int tradingDay, Logger log) {
        if (tradingDay <= 0) {
            log.error("illegal param -> tradingDay=={}", tradingDay);
            return true;
        }
        return false;
    }

    public static boolean illegalTradingDay(int startTradingDay, int endTradingDay,
                                            Logger log) {
        if (startTradingDay <= 0 || endTradingDay < startTradingDay) {
            log.error("illegal param -> startTradingDay=={}, endTradingDay=={}",
                    startTradingDay, endTradingDay);
            return true;
        }
        return false;
    }

    public static boolean illegalOrdSysId(long ordSysId, Logger log) {
        if (ordSysId <= 0) {
            log.error("illegal param -> ordSysId=={}", ordSysId);
            return true;
        }
        return false;
    }


}
