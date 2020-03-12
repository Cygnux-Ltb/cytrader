package io.mercury.gateway.ctp.base;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 */
public interface Constant {

	// 方向常量
	String DIRECTION_NONE = "NONE";// 无方向
	String DIRECTION_LONG = "LONG"; // 多
	String DIRECTION_SHORT = "SHORT"; // 空
	String DIRECTION_UNKNOWN = "UNKNOWN"; // 未知
	String DIRECTION_NET = "NET"; // 净
	String DIRECTION_SELL = "SELL"; // 卖出 IB
	String DIRECTION_COVEREDSHORT = "COVEREDSHORT"; // 备兑空 // 证券期权

	// 开平常量
	String OFFSET_NONE = "NONE"; // 无开平
	String OFFSET_OPEN = "OPEN"; // 开仓
	String OFFSET_CLOSE = "CLOSE"; // 平仓
	String OFFSET_CLOSETODAY = "CLOSETODAY"; // 平今
	String OFFSET_CLOSEYESTERDAY = "CLOSEYESTERDAY"; // 平昨
	String OFFSET_UNKNOWN = "UNKNOWN"; // 未知

	// 状态常量
	String STATUS_NOTTRADED = "NOTTRADED"; // 未成交
	String STATUS_PARTTRADED = "PARTTRADED"; // 部分成交
	String STATUS_ALLTRADED = "ALLTRADED"; // 全部成交
	String STATUS_CANCELLED = "CANCELLED"; // 已撤销
	String STATUS_REJECTED = "REJECTED"; // 拒单
	String STATUS_UNKNOWN = "UNKNOWN"; // 未知

	HashSet<String> STATUS_FINISHED = new HashSet<String>() {
		private static final long serialVersionUID = 8777691797309945190L;
		{
			add(Constant.STATUS_REJECTED);
			add(Constant.STATUS_CANCELLED);
			add(Constant.STATUS_ALLTRADED);
		}
	};

	HashSet<String> STATUS_WORKING = new HashSet<String>() {
		private static final long serialVersionUID = 909683985291870766L;
		{
			add(Constant.STATUS_UNKNOWN);
			add(Constant.STATUS_NOTTRADED);
			add(Constant.STATUS_PARTTRADED);
		}
	};

	// 合约类型常量
	String PRODUCT_EQUITY = "EQUITY"; // 股票
	String PRODUCT_FUTURES = "FUTURES"; // 期货
	String PRODUCT_OPTION = "OPTION"; // 期权
	String PRODUCT_INDEX = "INDEX"; // 指数
	String PRODUCT_COMBINATION = "COMBINATION"; // 组合
	String PRODUCT_FOREX = "FOREX"; // 外汇
	String PRODUCT_UNKNOWN = "UNKNOWN"; // 未知
	String PRODUCT_SPOT = "SPOT"; // 现货
	String PRODUCT_DEFER = "DEFER "; // 延期
	String PRODUCT_ETF = "ETF"; // ETF
	String PRODUCT_WARRANT = "WARRANT"; // 权证
	String PRODUCT_BOND = "BOND"; // 债券
	String PRODUCT_NONE = "NONE"; // NONE

	// 价格类型常量
	String PRICETYPE_LIMITPRICE = "LIMITPRICE"; // 限价
	String PRICETYPE_MARKETPRICE = "MARKETPRICE "; // 市价
	String PRICETYPE_FAK = "FAK"; // FAK
	String PRICETYPE_FOK = "FOK"; // FOK

	// 期权类型
	String OPTION_CALL = "CALL"; // 看涨期权
	String OPTION_PUT = "PUT"; // 看跌期权

	// 交易所类型
	String EXCHANGE_SSE = "SSE"; // 上交所
	String EXCHANGE_SZSE = "SZSE"; // 深交所
	String EXCHANGE_CFFEX = "CFFEX"; // 中金所
	String EXCHANGE_SHFE = "SHFE"; // 上期所
	String EXCHANGE_CZCE = "CZCE"; // 郑商所
	String EXCHANGE_DCE = "DCE"; // 大商所
	String EXCHANGE_SGE = "SGE"; // 上金所
	String EXCHANGE_INE = "INE"; // 国际能源交易中心
	String EXCHANGE_UNKNOWN = "UNKNOWN";// 未知交易所
	String EXCHANGE_NONE = ""; // 空交易所
	String EXCHANGE_SEHK = "SEHK"; // 港交所
	String EXCHANGE_HKFE = "HKFE"; // 香港期货交易所

	String EXCHANGE_SMART = "SMART"; // IB智能路由（股票、期权）
	String EXCHANGE_NYMEX = "NYMEX"; // IB 期货
	String EXCHANGE_GLOBEX = "GLOBEX"; // CME电子交易平台
	String EXCHANGE_IDEALPRO = "IDEALPRO"; // IB外汇ECN

	String EXCHANGE_CME = "CME"; // 芝商所
	String EXCHANGE_ICE = "ICE"; // 洲际交易所
	String EXCHANGE_LME = "LME"; // 伦敦金属交易所

	String EXCHANGE_OANDA = "OANDA"; // OANDA外汇做市商
	String EXCHANGE_FXCM = "FXCM"; // FXCM外汇做市商

	String EXCHANGE_OKCOIN = "OKCOIN"; // OKCOIN比特币交易所
	String EXCHANGE_HUOBI = "HUOBI"; // 火币比特币交易所
	String EXCHANGE_LBANK = "LBANK"; // LBANK比特币交易所
	String EXCHANGE_KORBIT = "KORBIT"; // KORBIT韩国交易所
	String EXCHANGE_ZB = "ZB"; // 比特币中国比特币交易所
	String EXCHANGE_OKEX = "OKEX"; // OKEX比特币交易所
	String EXCHANGE_ZAIF = "ZAIF"; // ZAIF日本比特币交易所
	String EXCHANGE_COINCHECK = "COINCHECK"; // COINCHECK日本比特币交易所

	// 货币类型
	String CURRENCY_USD = "USD"; // 美元
	String CURRENCY_CNY = "CNY"; // 人民币
	String CURRENCY_CNH = "CNH"; // 离岸人民币
	String CURRENCY_HKD = "HKD"; // 港币
	String CURRENCY_JPY = "JPY"; // 日元
	String CURRENCY_EUR = "EUR"; // 欧元
	String CURRENCY_GBP = "GBP"; // 英镑
	String CURRENCY_DEM = "DEM"; // 德国马克
	String CURRENCY_CHF = "CHF"; // 瑞士法郎
	String CURRENCY_FRF = "FRF"; // 法国法郎
	String CURRENCY_CAD = "CAD"; // 加拿大元
	String CURRENCY_AUD = "AUD"; // 澳大利亚元
	String CURRENCY_ATS = "ATS"; // 奥地利先令
	String CURRENCY_FIM = "FIM"; // 芬兰马克
	String CURRENCY_BEF = "BEF"; // 比利时法郎
	String CURRENCY_IEP = "IEP"; // 爱尔兰镑
	String CURRENCY_ITL = "ITL"; // 意大利里拉
	String CURRENCY_LUF = "LUF"; // 卢森堡法郎
	String CURRENCY_NLG = "NLG"; // 荷兰盾
	String CURRENCY_PTE = "PTE"; // 葡萄牙埃斯库多
	String CURRENCY_ESP = "ESP"; // 西班牙比塞塔
	String CURRENCY_IDR = "IDR"; // 印尼盾
	String CURRENCY_MYR = "MYR"; // 马来西亚林吉特
	String CURRENCY_NZD = "NZD"; // 新西兰元
	String CURRENCY_PHP = "PHP"; // 菲律宾比索
	String CURRENCY_SUR = "SUR"; // 俄罗斯卢布
	String CURRENCY_SGD = "SGD"; // 新加坡元
	String CURRENCY_KRW = "KRW"; // 韩国元
	String CURRENCY_THB = "THB"; // 泰铢

	String CURRENCY_UNKNOWN = "UNKNOWN"; // 未知货币
	String CURRENCY_NONE = ""; // 空货币

	// 网关类型
	String GATEWAYTYPE_EQUITY = "equity"; // 股票、ETF、债券
	String GATEWAYTYPE_FUTURES = "futures"; // 期货、期权、贵金属
	String GATEWAYTYPE_INTERNATIONAL = "international"; // 外盘
	String GATEWAYTYPE_BTC = "btc"; // 比特币
	String GATEWAYTYPE_DATA = "data"; // 数据（非交易）

	String RED_TORCH_DB_NAME = "redtorch_j_db";

	String LOG_DEBUG = "DEBUG";
	String LOG_INFO = "INFO";
	String LOG_WARN = "WARN";
	String LOG_ERROR = "ERROR";

	String DT_FORMAT_WITH_MS = "yyyy-MM-dd HH:mm:ss.SSS";
	String DT_FORMAT_WITH_MS_INT = "yyyyMMddHHmmssSSS";
	String DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	String DT_FORMAT_INT = "yyyyMMddHHmmss";

	String T_FORMAT_WITH_MS_INT = "HHmmssSSS";
	String T_FORMAT_WITH_MS = "HH:mm:ss.SSS";
	String T_FORMAT_INT = "HHmmss";
	String T_FORMAT = "HH:mm:ss";

	String D_FORMAT_INT = "yyyyMMdd";
	String D_FORMAT = "yyyy-MM-dd";

	DateTimeFormatter DT_FORMAT_WITH_MS_FORMATTER = DateTimeFormatter.ofPattern(Constant.DT_FORMAT_WITH_MS);
	DateTimeFormatter DT_FORMAT_WITH_MS_INT_FORMATTER = DateTimeFormatter.ofPattern(Constant.DT_FORMAT_WITH_MS_INT);
	DateTimeFormatter DT_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(Constant.DT_FORMAT);
	DateTimeFormatter DT_FORMAT_INT_FORMATTER = DateTimeFormatter.ofPattern(Constant.DT_FORMAT_INT);

	DateTimeFormatter T_FORMAT_WITH_MS_INT_FORMATTER = DateTimeFormatter.ofPattern(Constant.T_FORMAT_WITH_MS_INT);
	DateTimeFormatter T_FORMAT_WITH_MS_FORMATTER = DateTimeFormatter.ofPattern(Constant.T_FORMAT_WITH_MS);
	DateTimeFormatter T_FORMAT_INT_FORMATTER = DateTimeFormatter.ofPattern(Constant.T_FORMAT_INT);
	DateTimeFormatter T_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(Constant.T_FORMAT);

	DateTimeFormatter D_FORMAT_INT_FORMATTER = DateTimeFormatter.ofPattern(Constant.D_FORMAT_INT);
	DateTimeFormatter D_FORMAT_FORMATTER = DateTimeFormatter.ofPattern(Constant.D_FORMAT);

}
