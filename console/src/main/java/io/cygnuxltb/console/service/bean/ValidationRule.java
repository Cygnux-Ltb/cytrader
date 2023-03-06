package io.cygnuxltb.console.service.bean;

public class ValidationRule {

    private String paramName;
    private String dataType;
    private String regex;
    private String valueType;
    private String minValue;
    private String maxValue;

    public String getParamName() {
        return paramName;
    }

    public ValidationRule setParamName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public ValidationRule setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getRegex() {
        return regex;
    }

    public ValidationRule setRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public ValidationRule setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getMinValue() {
        return minValue;
    }

    public ValidationRule setMinValue(String minValue) {
        this.minValue = minValue;
        return this;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public ValidationRule setMaxValue(String maxValue) {
        this.maxValue = maxValue;
        return this;
    }

}
