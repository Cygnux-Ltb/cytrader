package io.mercury.ftdc.gateway.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ctp.thostapi.thosttraderapiConstants;

public class CtpConstant {
	
	public static Map<String, Character> priceTypeMap = new HashMap<>();
	public static Map<Character, String> priceTypeMapReverse = new HashMap<>();

	public static Map<String, Character> directionMap = new HashMap<>();
	public static Map<Character, String> directionMapReverse = new HashMap<>();

	public static Map<String, Character> offsetMap = new HashMap<>();
	public static Map<Character, String> offsetMapReverse = new HashMap<>();

	public static Map<String, String> exchangeMap = new HashMap<>();
	public static Map<String, String> exchangeMapReverse = new HashMap<>();

	public static Map<String, Character> posiDirectionMap = new HashMap<>();
	public static Map<Character, String> posiDirectionMapReverse = new HashMap<>();

	public static Map<String, Character> productClassMap = new HashMap<>();
	public static Map<Character, String> productClassMapReverse = new HashMap<>();

	public static Map<String, Character> statusMap = new HashMap<>();
	public static Map<Character, String> statusMapReverse = new HashMap<>();

	static {

		// 价格类型映射
		priceTypeMap.put(Constant.PRICETYPE_LIMITPRICE, thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
		priceTypeMap.put(Constant.PRICETYPE_MARKETPRICE, thosttraderapiConstants.THOST_FTDC_OPT_AnyPrice);
		priceTypeMapReverse = priceTypeMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		// 方向类型映射
		directionMap.put(Constant.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_D_Buy);
		directionMap.put(Constant.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_D_Sell);
		directionMapReverse = directionMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		// 开平类型映射
		offsetMap.put(Constant.OFFSET_OPEN, thosttraderapiConstants.THOST_FTDC_OF_Open);
		offsetMap.put(Constant.OFFSET_CLOSE, thosttraderapiConstants.THOST_FTDC_OF_Close);
		offsetMap.put(Constant.OFFSET_CLOSETODAY, thosttraderapiConstants.THOST_FTDC_OF_CloseToday);
		offsetMap.put(Constant.OFFSET_CLOSEYESTERDAY, thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);
		offsetMapReverse = offsetMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		// 交易所映射
		exchangeMap.put(Constant.EXCHANGE_CFFEX, "CFFEX");
		exchangeMap.put(Constant.EXCHANGE_SHFE, "SHFE");
		exchangeMap.put(Constant.EXCHANGE_CZCE, "CZCE");
		exchangeMap.put(Constant.EXCHANGE_DCE, "DCE");
		exchangeMap.put(Constant.EXCHANGE_SSE, "SSE");
		exchangeMap.put(Constant.EXCHANGE_SZSE, "SZSE");
		exchangeMap.put(Constant.EXCHANGE_INE, "INE");
		exchangeMap.put(Constant.EXCHANGE_UNKNOWN, "");
		exchangeMapReverse = exchangeMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		// 持仓类型映射
		posiDirectionMap.put(Constant.DIRECTION_NET, thosttraderapiConstants.THOST_FTDC_PD_Net);
		posiDirectionMap.put(Constant.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_PD_Long);
		posiDirectionMap.put(Constant.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_PD_Short);
		posiDirectionMapReverse = posiDirectionMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		// 产品类型映射
		productClassMap.put(Constant.PRODUCT_FUTURES, thosttraderapiConstants.THOST_FTDC_PC_Futures);
		productClassMap.put(Constant.PRODUCT_OPTION, thosttraderapiConstants.THOST_FTDC_PC_Options);
		productClassMap.put(Constant.PRODUCT_COMBINATION, thosttraderapiConstants.THOST_FTDC_PC_Combination);
		productClassMapReverse = productClassMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
//		v6.3.11不支持个股期权
//		productClassMapReverse.put(thosttraderapiConstants.THOST_FTDC_PC_ETFOption, RtConstant.PRODUCT_OPTION);
//		productClassMapReverse.put(thosttraderapiConstants.THOST_FTDC_PC_S, RtConstant.PRODUCT_EQUITY);

		// 委托状态映射
		statusMap.put(Constant.STATUS_ALLTRADED, thosttraderapiConstants.THOST_FTDC_OST_AllTraded);
		statusMap.put(Constant.STATUS_PARTTRADED, thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing);
		statusMap.put(Constant.STATUS_NOTTRADED, thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing);
		statusMap.put(Constant.STATUS_CANCELLED, thosttraderapiConstants.THOST_FTDC_OST_Canceled);
		statusMap.put(Constant.STATUS_UNKNOWN, thosttraderapiConstants.THOST_FTDC_OST_Unknown);
		statusMapReverse = statusMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

	}
}
