package io.mercury.financial.instrument.futures.impl;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.common.collections.ImmutableMaps;
import io.mercury.common.collections.ImmutableSets;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.datetime.TimeZone;
import io.mercury.financial.instrument.Exchange;
import io.mercury.financial.instrument.Instrument.PriorityClose;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TradingPeriod;

public enum ChinaFuturesSymbol implements Symbol {

	// ************************上海期货交易所************************//
	/**
	 * 铜 cu
	 */
	CU(1, Exchange.SHFE, "cu", PriorityClose.NONE, PriceMultiplier.NONE,
			// 铜期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 铝 al
	 */
	AL(2, Exchange.SHFE, "al", PriorityClose.NONE, PriceMultiplier.NONE,
			// 铝期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 锌 zn
	 */
	ZN(3, Exchange.SHFE, "zn", PriorityClose.NONE, PriceMultiplier.NONE,
			// 锌期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 镍
	 */
	NI(5, Exchange.SHFE, "ni", PriorityClose.NONE, PriceMultiplier.NONE,
			// 镍期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 锡
	 */
	SN(6, Exchange.SHFE, "sn", PriorityClose.NONE, PriceMultiplier.NONE,
			// 锡期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 黄金
	 */
	AU(7, Exchange.SHFE, "au", PriorityClose.NONE, PriceMultiplier.TEN_THOUSAND,
			// 黄金期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(2, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 白银
	 */
	AG(8, Exchange.SHFE, "ag", PriorityClose.NONE, PriceMultiplier.NONE,
			// 白银期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(2, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 螺纹钢
	 */
	RB(9, Exchange.SHFE, "rb", PriorityClose.NONE, PriceMultiplier.NONE,
			// 螺纹钢期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 热卷扎板
	 */
	HC(10, Exchange.SHFE, "hc", PriorityClose.NONE, PriceMultiplier.NONE,
			// 热卷扎板期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 橡胶
	 */
	RU(12, Exchange.SHFE, "ru", PriorityClose.NONE, PriceMultiplier.NONE,
			// 橡胶期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 沥青
	 */
	BU(11, Exchange.SHFE, "bu", PriorityClose.NONE, PriceMultiplier.NONE,
			// 沥青期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 燃油
	 */
	FU(13, Exchange.SHFE, "fu", PriorityClose.NONE, PriceMultiplier.NONE,
			// 燃油期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	// ************************上海能源交易所************************//
	/**
	 * 原油
	 */
	SC(1, Exchange.SHINE, "sc", PriorityClose.NONE, PriceMultiplier.NONE,
			// 原油期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	// **************************大连商品交易所*************************//
	/**
	 * 大豆 a
	 */
	A(3, Exchange.DCE, "a", PriorityClose.NONE, PriceMultiplier.NONE,
			// 大豆期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 豆粕 m
	 */
	M(3, Exchange.DCE, "m", PriorityClose.NONE, PriceMultiplier.NONE,
			// 豆粕期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 豆油 y
	 */
	Y(3, Exchange.DCE, "y", PriorityClose.NONE, PriceMultiplier.NONE,
			// 豆油期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 棕榈油 p
	 */
	P(3, Exchange.DCE, "p", PriorityClose.NONE, PriceMultiplier.NONE,
			// 棕榈油期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 铁矿石 i
	 */
	I(3, Exchange.DCE, "i", PriorityClose.NONE, PriceMultiplier.NONE,
			// 铁矿石期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	// TODO 大商所品种 : 玉米, 鸡蛋, 塑料, PVC, PP,

	// *****************************郑州商品交易所***********************************//
	/**
	 * 棉花 cf
	 */
	CF(1, Exchange.ZCE, "CF", PriorityClose.NONE, PriceMultiplier.NONE,
			// 棉花交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),

	/**
	 * 白糖 sr
	 */
	SR(2, Exchange.ZCE, "SR", PriorityClose.NONE, PriceMultiplier.NONE,
			// 白糖交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(15, 15, 00))),

	/**
	 * PTA
	 */
	TA(3, Exchange.ZCE, "TA", PriorityClose.NONE, PriceMultiplier.NONE,
			// PTA交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(15, 15, 00))),

	/**
	 * 乙醇
	 */
	MA(4, Exchange.ZCE, "MA", PriorityClose.NONE, PriceMultiplier.NONE,
			// 乙醇交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(15, 15, 00))),

	/**
	 * 菜粕
	 */
	RM(5, Exchange.ZCE, "RM", PriorityClose.NONE, PriceMultiplier.NONE,
			// 菜粕交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(15, 15, 00))),

	// ************************中国金融交易所************************//
	/**
	 * 沪深300期货
	 */
	IF(1, Exchange.CFFEX, "IF", PriorityClose.NONE, PriceMultiplier.NONE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),

	/**
	 * 上证50期货
	 */
	IH(2, Exchange.CFFEX, "IH", PriorityClose.NONE, PriceMultiplier.NONE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),
	/**
	 * 中证500期货
	 */
	IC(3, Exchange.CFFEX, "IC", PriorityClose.NONE, PriceMultiplier.NONE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),
	/**
	 * 十年债券期货
	 */
	T(3, Exchange.CFFEX, "T", PriorityClose.NONE, PriceMultiplier.TEN_THOUSAND,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),

	;

	private int symbolId;

	private Exchange exchange;

	private String code;

	private PriorityClose priorityClose;

	private PriceMultiplier priceMultiplier;

	private ImmutableSortedSet<TradingPeriod> tradingPeriodSet;

	private ChinaFuturesSymbol(int exchangeSerial, Exchange exchange, String code, PriorityClose priorityClose,
			PriceMultiplier priceMultiplier, TradingPeriod... tradingPeriods) {
		this.symbolId = exchange.id() + exchangeSerial * 10000;
		this.exchange = exchange;
		this.code = code;
		this.priorityClose = priorityClose;
		this.priceMultiplier = priceMultiplier;
		this.tradingPeriodSet = ImmutableSets.newSortedSet(tradingPeriods);
	}

	@Override
	public int id() {
		return symbolId;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public Exchange exchange() {
		return exchange;
	}

	public PriorityClose priorityClose() {
		return priorityClose;
	}

	@Override
	public PriceMultiplier priceMultiplier() {
		return priceMultiplier;
	}

	@Override
	public ImmutableSortedSet<TradingPeriod> tradingPeriodSet() {
		return tradingPeriodSet;
	}

	// 建立symbolId -> symbol的映射
	private final static ImmutableIntObjectMap<ChinaFuturesSymbol> SymbolIdMap = ImmutableMaps.IntObjectMapFactory()
			.from(
					// 将ChinaFuturesSymbol转换为Iterable
					MutableLists.newFastList(ChinaFuturesSymbol.values()),
					// 取Symbol::id为Key
					ChinaFuturesSymbol::id, symbol -> symbol);

	public static ChinaFuturesSymbol of(int symbolId) {
		ChinaFuturesSymbol symbol = SymbolIdMap.get(symbolId);
		if (symbol == null)
			throw new IllegalArgumentException("Symbol Id -> " + symbolId + " is no mapping object");
		return symbol;
	}

	// 建立symbolCode -> symbol的映射
	private final static ImmutableMap<String, ChinaFuturesSymbol> SymbolCodeMap = ImmutableMaps.newMap(
			// 将ChinaFuturesSymbol转换为Map
			Stream.of(ChinaFuturesSymbol.values()).collect(Collectors.toMap(
					// 取Symbol::code为Key
					ChinaFuturesSymbol::code, symbol -> symbol)));

	public static ChinaFuturesSymbol of(String symbolCode) {
		String key = symbolCode.toUpperCase();
		ChinaFuturesSymbol symbol = SymbolCodeMap.get(key);
		if (symbol == null)
			throw new IllegalArgumentException("Symbol Code -> " + symbolCode + " is no mapping object");
		return symbol;
	}

	public int acquireInstrumentId(int term) {
		if (term > 9999)
			throw new IllegalArgumentException("Term > 9999, Is too much.");
		return symbolId + term;
	}

	public static void main(String[] args) {
		for (Symbol symbol : ChinaFuturesSymbol.values()) {
			symbol.tradingPeriodSet()
					.each(tradingPeriod -> tradingPeriod.segmentation(TimeZone.CST, TimePeriod.S30.duration())
							.each(timePeriod -> System.out.println(symbol.code() + " | " + timePeriod)));
		}
		System.out.println(ChinaFuturesSymbol.AG.exchange.id());
		System.out.println(ChinaFuturesSymbol.AG.symbolId);
	}

	@Override
	public String fmtText() {
		return "";
	}

}
