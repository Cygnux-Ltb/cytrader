package io.cygnux.console.controller.util;

import io.mercury.common.character.Charsets;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

public final class ParamsValidateUtil {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ParamsValidateUtil.class);

    /**
     * 检查参数列表
     *
     * @param objs Object...
     * @return boolean
     */
    public static boolean paramIsNull(Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }


    public static String getBody(HttpServletRequest request) {
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


    public static boolean checkStrategyId(int strategyId, Logger log, String msg) {
        if (strategyId < 0) {
            log.error("{}, strategyId=={}", msg, strategyId);
            return true;
        }
        return false;
    }

    public static boolean checkStrategyName(String strategyName, Logger log, String msg) {
        if (StringSupport.isNullOrEmpty(strategyName)) {
            log.error("{}, strategyName=={}", msg, strategyName);
            return true;
        }
        return false;
    }

    public static boolean checkInvestorId(String investorId, Logger log, String msg) {
        if (StringSupport.isNullOrEmpty(investorId)) {
            log.error("{}, investorId=={}", msg, investorId);
            return true;
        }
        return false;
    }

    public static boolean checkInstrumentCode(String instrumentCode, Logger log, String msg) {
        if (StringSupport.isNullOrEmpty(instrumentCode)) {
            log.error("{}, instrumentCode=={}", msg, instrumentCode);
            return true;
        }
        return false;
    }

    public static boolean checkTradingDay(int tradingDay, Logger log, String msg) {
        if (tradingDay <= 0) {
            log.error("{}, tradingDay=={}", msg, tradingDay);
            return true;
        }
        return false;
    }

    public static boolean checkTradingDay(int startTradingDay, int endTradingDay, Logger log, String msg) {
        if (startTradingDay <= 0 || endTradingDay < startTradingDay) {
            log.error("{}, startTradingDay=={}, endTradingDay=={}", msg, startTradingDay, endTradingDay);
            return true;
        }
        return false;
    }

    public static boolean checkOrdSysId(long ordSysId, Logger log, String msg) {
        if (ordSysId <= 0) {
            log.error("{}, ordSysId=={}", msg, ordSysId);
            return true;
        }
        return false;
    }


}
