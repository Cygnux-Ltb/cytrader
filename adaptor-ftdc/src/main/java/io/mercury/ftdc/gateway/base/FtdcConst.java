package io.mercury.ftdc.gateway.base;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.impl.bimap.immutable.ImmutableBiMapFactoryImpl;

import ctp.thostapi.thosttraderapiConstants;

public final class FtdcConst {

	// 方向常量
	public static final String DIRECTION_NONE = "NONE";// 无方向
	public static final String DIRECTION_LONG = "LONG"; // 多
	public static final String DIRECTION_SHORT = "SHORT"; // 空
	public static final String DIRECTION_UNKNOWN = "UNKNOWN"; // 未知
	public static final String DIRECTION_NET = "NET"; // 净
	public static final String DIRECTION_SELL = "SELL"; // 卖出 IB
	public static final String DIRECTION_COVEREDSHORT = "COVEREDSHORT"; // 备兑空 // 证券期权

	// 开平常量
	public static final String OFFSET_NONE = "NONE"; // 无开平
	public static final String OFFSET_OPEN = "OPEN"; // 开仓
	public static final String OFFSET_CLOSE = "CLOSE"; // 平仓
	public static final String OFFSET_CLOSETODAY = "CLOSETODAY"; // 平今
	public static final String OFFSET_CLOSEYESTERDAY = "CLOSEYESTERDAY"; // 平昨
	public static final String OFFSET_UNKNOWN = "UNKNOWN"; // 未知

	// 状态常量
	public static final String STATUS_NOTTRADED = "NOTTRADED"; // 未成交
	public static final String STATUS_PARTTRADED = "PARTTRADED"; // 部分成交
	public static final String STATUS_ALLTRADED = "ALLTRADED"; // 全部成交
	public static final String STATUS_CANCELLED = "CANCELLED"; // 已撤销
	public static final String STATUS_REJECTED = "REJECTED"; // 拒单
	public static final String STATUS_UNKNOWN = "UNKNOWN"; // 未知

	HashSet<String> STATUS_FINISHED = new HashSet<String>() {
		private static final long serialVersionUID = 8777691797309945190L;
		{
			add(FtdcConst.STATUS_REJECTED);
			add(FtdcConst.STATUS_CANCELLED);
			add(FtdcConst.STATUS_ALLTRADED);
		}
	};

	HashSet<String> STATUS_WORKING = new HashSet<String>() {
		private static final long serialVersionUID = 909683985291870766L;
		{
			add(FtdcConst.STATUS_UNKNOWN);
			add(FtdcConst.STATUS_NOTTRADED);
			add(FtdcConst.STATUS_PARTTRADED);
		}
	};

	// 合约类型常量
	public static final String PRODUCT_EQUITY = "EQUITY"; // 股票
	public static final String PRODUCT_FUTURES = "FUTURES"; // 期货
	public static final String PRODUCT_OPTION = "OPTION"; // 期权
	public static final String PRODUCT_INDEX = "INDEX"; // 指数
	public static final String PRODUCT_COMBINATION = "COMBINATION"; // 组合
	public static final String PRODUCT_FOREX = "FOREX"; // 外汇
	public static final String PRODUCT_UNKNOWN = "UNKNOWN"; // 未知
	public static final String PRODUCT_SPOT = "SPOT"; // 现货
	public static final String PRODUCT_DEFER = "DEFER "; // 延期
	public static final String PRODUCT_ETF = "ETF"; // ETF
	public static final String PRODUCT_WARRANT = "WARRANT"; // 权证
	public static final String PRODUCT_BOND = "BOND"; // 债券
	public static final String PRODUCT_NONE = "NONE"; // NONE

	// 价格类型常量
	public static final String PRICETYPE_LIMITPRICE = "LIMITPRICE"; // 限价
	public static final String PRICETYPE_MARKETPRICE = "MARKETPRICE "; // 市价
	public static final String PRICETYPE_FAK = "FAK"; // FAK
	public static final String PRICETYPE_FOK = "FOK"; // FOK

	// 期权类型
	public static final String OPTION_CALL = "CALL"; // 看涨期权
	public static final String OPTION_PUT = "PUT"; // 看跌期权

	// 交易所类型
	public static final String EXCHANGE_SSE = "SSE"; // 上交所
	public static final String EXCHANGE_SZSE = "SZSE"; // 深交所
	public static final String EXCHANGE_CFFEX = "CFFEX"; // 中金所
	public static final String EXCHANGE_SHFE = "SHFE"; // 上期所
	public static final String EXCHANGE_CZCE = "CZCE"; // 郑商所
	public static final String EXCHANGE_DCE = "DCE"; // 大商所
	public static final String EXCHANGE_SGE = "SGE"; // 上金所
	public static final String EXCHANGE_INE = "INE"; // 国际能源交易中心
	public static final String EXCHANGE_UNKNOWN = "UNKNOWN";// 未知交易所
	public static final String EXCHANGE_NONE = ""; // 空交易所
	public static final String EXCHANGE_SEHK = "SEHK"; // 港交所
	public static final String EXCHANGE_HKFE = "HKFE"; // 香港期货交易所

	public static final String EXCHANGE_SMART = "SMART"; // IB智能路由（股票、期权）
	public static final String EXCHANGE_NYMEX = "NYMEX"; // IB 期货
	public static final String EXCHANGE_GLOBEX = "GLOBEX"; // CME电子交易平台
	public static final String EXCHANGE_IDEALPRO = "IDEALPRO"; // IB外汇ECN

	public static final String EXCHANGE_CME = "CME"; // 芝商所
	public static final String EXCHANGE_ICE = "ICE"; // 洲际交易所
	public static final String EXCHANGE_LME = "LME"; // 伦敦金属交易所

	public static final String EXCHANGE_OANDA = "OANDA"; // OANDA外汇做市商
	public static final String EXCHANGE_FXCM = "FXCM"; // FXCM外汇做市商

	public static final String EXCHANGE_OKCOIN = "OKCOIN"; // OKCOIN比特币交易所
	public static final String EXCHANGE_HUOBI = "HUOBI"; // 火币比特币交易所
	public static final String EXCHANGE_LBANK = "LBANK"; // LBANK比特币交易所
	public static final String EXCHANGE_KORBIT = "KORBIT"; // KORBIT韩国交易所
	public static final String EXCHANGE_ZB = "ZB"; // 比特币中国比特币交易所
	public static final String EXCHANGE_OKEX = "OKEX"; // OKEX比特币交易所
	public static final String EXCHANGE_ZAIF = "ZAIF"; // ZAIF日本比特币交易所
	public static final String EXCHANGE_COINCHECK = "COINCHECK"; // COINCHECK日本比特币交易所

	// 货币类型
	public static final String CURRENCY_USD = "USD"; // 美元
	public static final String CURRENCY_CNY = "CNY"; // 人民币
	public static final String CURRENCY_CNH = "CNH"; // 离岸人民币
	public static final String CURRENCY_HKD = "HKD"; // 港币
	public static final String CURRENCY_JPY = "JPY"; // 日元
	public static final String CURRENCY_EUR = "EUR"; // 欧元
	public static final String CURRENCY_GBP = "GBP"; // 英镑
	public static final String CURRENCY_DEM = "DEM"; // 德国马克
	public static final String CURRENCY_CHF = "CHF"; // 瑞士法郎
	public static final String CURRENCY_FRF = "FRF"; // 法国法郎
	public static final String CURRENCY_CAD = "CAD"; // 加拿大元
	public static final String CURRENCY_AUD = "AUD"; // 澳大利亚元
	public static final String CURRENCY_ATS = "ATS"; // 奥地利先令
	public static final String CURRENCY_FIM = "FIM"; // 芬兰马克
	public static final String CURRENCY_BEF = "BEF"; // 比利时法郎
	public static final String CURRENCY_IEP = "IEP"; // 爱尔兰镑
	public static final String CURRENCY_ITL = "ITL"; // 意大利里拉
	public static final String CURRENCY_LUF = "LUF"; // 卢森堡法郎
	public static final String CURRENCY_NLG = "NLG"; // 荷兰盾
	public static final String CURRENCY_PTE = "PTE"; // 葡萄牙埃斯库多
	public static final String CURRENCY_ESP = "ESP"; // 西班牙比塞塔
	public static final String CURRENCY_IDR = "IDR"; // 印尼盾
	public static final String CURRENCY_MYR = "MYR"; // 马来西亚林吉特
	public static final String CURRENCY_NZD = "NZD"; // 新西兰元
	public static final String CURRENCY_PHP = "PHP"; // 菲律宾比索
	public static final String CURRENCY_SUR = "SUR"; // 俄罗斯卢布
	public static final String CURRENCY_SGD = "SGD"; // 新加坡元
	public static final String CURRENCY_KRW = "KRW"; // 韩国元
	public static final String CURRENCY_THB = "THB"; // 泰铢

	public static final String CURRENCY_UNKNOWN = "UNKNOWN"; // 未知货币
	public static final String CURRENCY_NONE = ""; // 空货币

	public static final String LOG_DEBUG = "DEBUG";
	public static final String LOG_INFO = "INFO";
	public static final String LOG_WARN = "WARN";
	public static final String LOG_ERROR = "ERROR";

	public static final String DT_FORMAT_WITH_MS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DT_FORMAT_WITH_MS_INT = "yyyyMMddHHmmssSSS";
	public static final String DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DT_FORMAT_INT = "yyyyMMddHHmmss";

	public static final String T_FORMAT_WITH_MS_INT = "HHmmssSSS";
	public static final String T_FORMAT_WITH_MS = "HH:mm:ss.SSS";
	public static final String T_FORMAT_INT = "HHmmss";
	public static final String T_FORMAT = "HH:mm:ss";

	public static final String D_FORMAT_INT = "yyyyMMdd";
	public static final String D_FORMAT = "yyyy-MM-dd";

	public static final DateTimeFormatter DT_FORMAT_WITH_MS_FORMATTER = DateTimeFormatter
			.ofPattern(FtdcConst.DT_FORMAT_WITH_MS);
	public static final DateTimeFormatter DT_FORMAT_WITH_MS_INT_FORMATTER = DateTimeFormatter
			.ofPattern(FtdcConst.DT_FORMAT_WITH_MS_INT);
	public static final DateTimeFormatter DT_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(FtdcConst.DT_FORMAT);
	public static final DateTimeFormatter DT_FORMAT_INT_FORMATTER = DateTimeFormatter
			.ofPattern(FtdcConst.DT_FORMAT_INT);

	public static final DateTimeFormatter T_FORMAT_WITH_MS_INT_FORMATTER = DateTimeFormatter
			.ofPattern(FtdcConst.T_FORMAT_WITH_MS_INT);
	public static final DateTimeFormatter T_FORMAT_WITH_MS_FORMATTER = DateTimeFormatter
			.ofPattern(FtdcConst.T_FORMAT_WITH_MS);
	public static final DateTimeFormatter T_FORMAT_INT_FORMATTER = DateTimeFormatter.ofPattern(FtdcConst.T_FORMAT_INT);
	public static final DateTimeFormatter T_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(FtdcConst.T_FORMAT);

	public static final DateTimeFormatter D_FORMAT_INT_FORMATTER = DateTimeFormatter.ofPattern(FtdcConst.D_FORMAT_INT);
	public static final DateTimeFormatter D_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(FtdcConst.D_FORMAT);

	/**
	 * 
	 * 
	 * 
	 * 
	 ***************************** 以下为CTP常量映射 ******************************
	 *
	 *
	 *
	 *
	 */

	@Deprecated
	public static final Map<String, Character> priceTypeMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> priceTypeMapReverse = new HashMap<>();
	static {
		// 价格类型映射
		priceTypeMap.put(FtdcConst.PRICETYPE_LIMITPRICE, thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
		priceTypeMap.put(FtdcConst.PRICETYPE_MARKETPRICE, thosttraderapiConstants.THOST_FTDC_OPT_AnyPrice);
		priceTypeMapReverse = priceTypeMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}
	// TODO 可扩展
	// 价格类型
	public static final ImmutableBiMap<String, Character> PriceTypeBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
			// 限价单
			FtdcConst.PRICETYPE_LIMITPRICE, thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice,
			// 市价单
			FtdcConst.PRICETYPE_MARKETPRICE, thosttraderapiConstants.THOST_FTDC_OPT_AnyPrice);

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, Character> directionMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> directionMapReverse = new HashMap<>();
	static {
		// 方向类型映射
		directionMap.put(FtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_D_Buy);
		directionMap.put(FtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_D_Sell);
		directionMapReverse = directionMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}
	// 方向类型
	public static final ImmutableBiMap<String, Character> DirectionBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
			// 买
			FtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_D_Buy,
			// 卖
			FtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_D_Sell);

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, Character> offsetMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> offsetMapReverse = new HashMap<>();
	static {
		// 开平类型映射
		offsetMap.put(FtdcConst.OFFSET_OPEN, thosttraderapiConstants.THOST_FTDC_OF_Open);
		offsetMap.put(FtdcConst.OFFSET_CLOSE, thosttraderapiConstants.THOST_FTDC_OF_Close);
		offsetMap.put(FtdcConst.OFFSET_CLOSETODAY, thosttraderapiConstants.THOST_FTDC_OF_CloseToday);
		offsetMap.put(FtdcConst.OFFSET_CLOSEYESTERDAY, thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);
		offsetMapReverse = offsetMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}
	// 开平类型
	public static final ImmutableBiMap<String, Character> OffsetBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
			// 开仓
			FtdcConst.OFFSET_OPEN, thosttraderapiConstants.THOST_FTDC_OF_Open,
			// 平仓
			FtdcConst.OFFSET_CLOSE, thosttraderapiConstants.THOST_FTDC_OF_Close,
			// 平今(上期所)
			FtdcConst.OFFSET_CLOSETODAY, thosttraderapiConstants.THOST_FTDC_OF_CloseToday,
			// 平昨(上期所)
			FtdcConst.OFFSET_CLOSEYESTERDAY, thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, String> exchangeMap = new HashMap<>();
	@Deprecated
	public static Map<String, String> exchangeMapReverse = new HashMap<>();
	static {
		// 交易所映射
		exchangeMap.put(FtdcConst.EXCHANGE_CFFEX, "CFFEX");
		exchangeMap.put(FtdcConst.EXCHANGE_SHFE, "SHFE");
		exchangeMap.put(FtdcConst.EXCHANGE_CZCE, "CZCE");
		exchangeMap.put(FtdcConst.EXCHANGE_DCE, "DCE");
		exchangeMap.put(FtdcConst.EXCHANGE_SSE, "SSE");
		exchangeMap.put(FtdcConst.EXCHANGE_SZSE, "SZSE");
		exchangeMap.put(FtdcConst.EXCHANGE_INE, "INE");
		exchangeMap.put(FtdcConst.EXCHANGE_UNKNOWN, "");
		exchangeMapReverse = exchangeMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, Character> posiDirectionMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> posiDirectionMapReverse = new HashMap<>();
	static {
		// 持仓类型映射
		posiDirectionMap.put(FtdcConst.DIRECTION_NET, thosttraderapiConstants.THOST_FTDC_PD_Net);
		posiDirectionMap.put(FtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_PD_Long);
		posiDirectionMap.put(FtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_PD_Short);
		posiDirectionMapReverse = posiDirectionMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}
	// 持仓类型
	public static final ImmutableBiMap<String, Character> PosiDirectionBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
			//
			FtdcConst.DIRECTION_NET, thosttraderapiConstants.THOST_FTDC_PD_Net,
			//
			FtdcConst.DIRECTION_LONG, thosttraderapiConstants.THOST_FTDC_PD_Long,
			//
			FtdcConst.DIRECTION_SHORT, thosttraderapiConstants.THOST_FTDC_PD_Short);

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, Character> productClassMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> productClassMapReverse = new HashMap<>();
	static {
		// 产品类型映射
		productClassMap.put(FtdcConst.PRODUCT_FUTURES, thosttraderapiConstants.THOST_FTDC_PC_Futures);
		productClassMap.put(FtdcConst.PRODUCT_OPTION, thosttraderapiConstants.THOST_FTDC_PC_Options);
		productClassMap.put(FtdcConst.PRODUCT_COMBINATION, thosttraderapiConstants.THOST_FTDC_PC_Combination);
		productClassMapReverse = productClassMap.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}
	// 产品类型
	public static final ImmutableBiMap<String, Character> ProductClassBiMap = ImmutableBiMapFactoryImpl.INSTANCE.with(
			// 期货
			FtdcConst.PRODUCT_FUTURES, thosttraderapiConstants.THOST_FTDC_PC_Futures,
			// 期权
			FtdcConst.PRODUCT_OPTION, thosttraderapiConstants.THOST_FTDC_PC_Options,
			// 组合
			FtdcConst.PRODUCT_COMBINATION, thosttraderapiConstants.THOST_FTDC_PC_Combination);

	/**
	 * 
	 */
	@Deprecated
	public static final Map<String, Character> statusMap = new HashMap<>();
	@Deprecated
	public static Map<Character, String> statusMapReverse = new HashMap<>();
	static {
		// 委托状态映射
		statusMap.put(FtdcConst.STATUS_ALLTRADED, thosttraderapiConstants.THOST_FTDC_OST_AllTraded);
		statusMap.put(FtdcConst.STATUS_PARTTRADED, thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing);
		statusMap.put(FtdcConst.STATUS_NOTTRADED, thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing);
		statusMap.put(FtdcConst.STATUS_CANCELLED, thosttraderapiConstants.THOST_FTDC_OST_Canceled);
		statusMap.put(FtdcConst.STATUS_UNKNOWN, thosttraderapiConstants.THOST_FTDC_OST_Unknown);
		statusMapReverse = statusMap.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey));
	}

}
