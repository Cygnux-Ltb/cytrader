package io.cygnus.restful.service.resources.executor;

import io.cygnus.persistence.entity.StrategyParam;
import io.cygnus.repository.dao.StrategyDao;
import io.cygnus.repository.entity.StrategyParamEntity;
import io.cygnus.repository.service.StrategyDao;
import io.cygnus.restful.service.resources.executor.bean.ValidationRule;
import io.mercury.common.character.Charsets;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class UpdateParamExecutor {

    private static final Logger log = CommonLoggerFactory.getLogger(UpdateParamExecutor.class);

    private static Map<String, ValidationRule> validationRuleMap;

    @Resource
    private StrategyDao strategyDao;

    static {
        InputStream inputStream = UpdateParamExecutor.class.getResourceAsStream("ValidationRules.json");
        try {
            String json = IOUtils.toString(inputStream, Charsets.UTF8);
            List<ValidationRule> validationRules = JsonParser.toList(json, ValidationRule.class);
            validationRuleMap = new HashMap<>(validationRules.size());
            for (ValidationRule validationRule : validationRules) {
                validationRuleMap.put(validationRule.getParamName(), validationRule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param strategyParam
     * @return
     */
    public int updateParamSafe(StrategyParamEntity strategyParam) {
        if (validationStrategyParam(strategyParam)) {
            strategyDao.save(strategyParam);
            if (resultCount > 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    private boolean validationStrategyParam(StrategyParamEntity strategyParam) {
        String strategyParamName = strategyParam.getParamName();
        ValidationRule validationRule = validationRuleMap.get(strategyParamName);
        if (!validationParamName(strategyParamName, validationRule)) {
            return false;
        }
        String strategyParamValue = strategyParam.getParamValue();
        if (!validationRegex(strategyParamValue, validationRule.getRegex())) {
            return false;
        }
        if (!validationValue(strategyParamValue, validationRule)) {
            return false;
        }
        return true;
    }

    /**
     * 如果变量[validationRule]有值,
     * 证明上层caller使用[strategyParamName]从map中成功get到了相应的ValidationRule对象,
     * 所以此InputParam为[ValidationRuleMap]的子集, 返回ture.
     *
     * @param strategyParamName
     * @param validationRule
     * @return boolean
     */
    private boolean validationParamName(String strategyParamName, ValidationRule validationRule) {
        if (strategyParamName != null && validationRule != null) {
            return true;
        }
        return false;
    }

    /**
     * 使用正则表达式验证整个参数的StringValue
     *
     * @param strategyParamValue
     * @param regex
     * @return
     */
    private boolean validationRegex(String strategyParamValue, String regex) {
        return Pattern.matches(regex, strategyParamValue);
    }

    /**
     * Validation Value Type and Range
     *
     * @param strategyParam
     * @return
     */
    private boolean validationValue(String strategyParamValue, ValidationRule validationRule) {
        String[] paramValueArray = strategyParamValue.split(";");
        String valueType = validationRule.getValueType();
        switch (valueType) {
            case "Int":
                return validationIntValue(paramValueArray, validationRule);
            case "Double":
                return validationDoubleValue(paramValueArray, validationRule);
            default:
                return false;
        }
    }

    private boolean validationIntValue(String[] paramValueArray, ValidationRule validationRule) {
        int maxValue = Integer.valueOf(validationRule.getMaxValue());
        int minValue = Integer.valueOf(validationRule.getMinValue());
        for (String paramValue : paramValueArray) {
            String valueStr = paramValue.split(",")[1];
            int value = Integer.valueOf(valueStr);
            // 大于Rule定于的最大值或小于最小值则返回false
            if (value > maxValue || value < minValue) {
                return false;
            }
        }
        return true;
    }

    private boolean validationDoubleValue(String[] paramValueArray, ValidationRule validationRule) {
        double maxValue = Double.valueOf(validationRule.getMaxValue());
        double minValue = Double.valueOf(validationRule.getMinValue());
        for (String paramValue : paramValueArray) {
            String valueStr = paramValue.split(",")[1];
            double value = Double.valueOf(valueStr);
            // 大于Rule定义的最大值或小于最小值则返回false
            if (value > maxValue || value < minValue) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String value = "ni,1197.0;pp,77.0;RM,23.8;SR,50.4;ag,30.1;au,1.925;hc,84.7;zn,322.0;";
        String regex = "^([a-zA-Z]{1,2},\\d+(\\.\\d+)?;)*([a-zA-Z]{1,2},\\d+(\\.\\d+)?;?)$";

        System.out.println(Pattern.matches(regex, value));
    }

}
