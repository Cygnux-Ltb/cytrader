package io.cygnux.console.service;

import io.cygnux.console.service.bean.ValidationRule;
import io.cygnux.repository.entities.ItParam;
import io.mercury.common.character.Charsets;
import io.mercury.common.log.Log4j2LoggerFactory;
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
public class ParamService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ParamService.class);

    private static final Map<String, ValidationRule> validationRuleMap = new HashMap<>();

    @Resource
    private StrategyService strategyService;

    static {
        try (InputStream inputStream = ParamService.class.getResourceAsStream("validation_rules.json")) {
            if (inputStream != null) {
                String json = IOUtils.toString(inputStream, Charsets.UTF8);
                List<ValidationRule> validationRules = JsonParser.toList(json, ValidationRule.class);
                for (ValidationRule validationRule : validationRules) {
                    validationRuleMap.put(validationRule.getParamName(), validationRule);
                }
            }
        } catch (IOException e) {
            log.error("IOException -> {}", e.getMessage(), e);
        }

    }

    /**
     * @param param
     * @return
     */
    public int updateParamSafe(ItParam param) {
        if (validationStrategyParam(param)) {
            if (strategyService.putStrategyParam(param)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    private boolean validationStrategyParam(ItParam param) {
        String paramName = param.getParamName();
        ValidationRule rule = validationRuleMap.get(paramName);
        if (!validationParamName(paramName, rule)) {
            return false;
        }
        String paramValue = param.getParamValue();
        if (!validationRegex(paramValue, rule.getRegex())) {
            return false;
        }
        if (!validationValue(paramValue, rule)) {
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
     * @param paramValue
     * @param regex
     * @return
     */
    private boolean validationRegex(String paramValue, String regex) {
        return Pattern.matches(regex, paramValue);
    }

    /**
     * Validation Value Type and Range
     *
     * @param paramValue
     * @return
     */
    private boolean validationValue(String paramValue, ValidationRule rule) {
        String[] valueArray = paramValue.split(";");
        String valueType = rule.getValueType();
        return switch (valueType) {
            case "Int" -> validationIntValue(valueArray, rule);
            case "Double" -> validationDoubleValue(valueArray, rule);
            default -> false;
        };
    }

    private boolean validationIntValue(String[] valueArray, ValidationRule rule) {
        int maxValue = Integer.parseInt(rule.getMaxValue());
        int minValue = Integer.parseInt(rule.getMinValue());
        for (String paramValue : valueArray) {
            String valueStr = paramValue.split(",")[1];
            int value = Integer.parseInt(valueStr);
            // 大于Rule定于的最大值或小于最小值则返回false
            if (value > maxValue || value < minValue) {
                return false;
            }
        }
        return true;
    }

    private boolean validationDoubleValue(String[] valueArray, ValidationRule rule) {
        double maxValue = Double.parseDouble(rule.getMaxValue());
        double minValue = Double.parseDouble(rule.getMinValue());
        for (String value : valueArray) {
            String valueStr = value.split(",")[1];
            double doubleValue = Double.parseDouble(valueStr);
            // 大于Rule定义的最大值或小于最小值则返回false
            if (doubleValue > maxValue || doubleValue < minValue) {
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
