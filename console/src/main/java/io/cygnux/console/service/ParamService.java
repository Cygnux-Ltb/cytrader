package io.cygnux.console.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnux.console.service.bean.ValidationRule;
import io.cygnux.repository.entities.internal.InStrategyParam;
import io.mercury.common.character.Charsets;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonParser;

@Component
public class ParamService {

	private static final Logger log = Log4j2LoggerFactory.getLogger(ParamService.class);

	private static Map<String, ValidationRule> validationRuleMap;

	@Resource
	private StrategyService strategyService;

	static {
		InputStream inputStream = ParamService.class.getResourceAsStream("validation_rules.json");
		try {
			String json = IOUtils.toString(inputStream, Charsets.UTF8);
			List<ValidationRule> validationRules = JsonParser.toList(json, ValidationRule.class);
			validationRuleMap = new HashMap<>(validationRules.size());
			for (ValidationRule validationRule : validationRules) {
				validationRuleMap.put(validationRule.getParamName(), validationRule);
			}
		} catch (IOException e) {
			log.error("IOException -> {}", e.getMessage(), e);
		}

	}

	/**
	 * @param strategyParam
	 * @return
	 */
	public int updateParamSafe(InStrategyParam strategyParam) {
		if (validationStrategyParam(strategyParam)) {
			if (strategyService.putStrategyParam(strategyParam)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}

	private boolean validationStrategyParam(InStrategyParam param) {
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
	 * @param strategyParam
	 * @return
	 */
	private boolean validationValue(String paramValue, ValidationRule rule) {
		String[] valueArray = paramValue.split(";");
		String valueType = rule.getValueType();
		switch (valueType) {
		case "Int":
			return validationIntValue(valueArray, rule);
		case "Double":
			return validationDoubleValue(valueArray, rule);
		default:
			return false;
		}
	}

	private boolean validationIntValue(String[] valueArray, ValidationRule rule) {
		int maxValue = Integer.valueOf(rule.getMaxValue());
		int minValue = Integer.valueOf(rule.getMinValue());
		for (String paramValue : valueArray) {
			String valueStr = paramValue.split(",")[1];
			int value = Integer.valueOf(valueStr);
			// 大于Rule定于的最大值或小于最小值则返回false
			if (value > maxValue || value < minValue) {
				return false;
			}
		}
		return true;
	}

	private boolean validationDoubleValue(String[] valueArray, ValidationRule rule) {
		double maxValue = Double.valueOf(rule.getMaxValue());
		double minValue = Double.valueOf(rule.getMinValue());
		for (String value : valueArray) {
			String valueStr = value.split(",")[1];
			double doubleValue = Double.valueOf(valueStr);
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
