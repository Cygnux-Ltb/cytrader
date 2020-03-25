package io.mercury.financial.instrument.futures;

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
import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TradingPeriod;

public enum ChinaFuturesSymbol implements Symbol {

	// ************************上期所************************//
	/**
	 * 铜
	 */
	CU(1, Exchange.SHFE,
			// 铜期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 铝
	 */
	AL(2, Exchange.SHFE,
			// 铝期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 锌
	 */
	ZN(3, Exchange.SHFE,
			// 锌期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 铅
	 */
	PB(4, Exchange.SHFE,
			// 铅期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 镍
	 */
	NI(5, Exchange.SHFE,
			// 镍期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 锡
	 */
	SN(6, Exchange.SHFE,
			// 锡期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 黄金
	 */
	AU(7, Exchange.SHFE,
			// 黄金期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(2, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 白银
	 */
	AG(8, Exchange.SHFE,
			// 白银期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(2, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 螺纹钢
	 */
	RB(9, Exchange.SHFE,
			// 螺纹钢期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 热卷扎板
	 */
	HC(10, Exchange.SHFE,
			// 热卷扎板期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 沥青
	 */
	BU(11, Exchange.SHFE,
			// 沥青期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	/**
	 * 橡胶
	 */
	RU(12, Exchange.SHFE,
			// 橡胶期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(23, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	// ************************上期所END************************//

	// ************************能源交易所************************//
	/**
	 * 原油
	 */
	SC(1, Exchange.SIEE,
			// 原油期货交易时段
			TradingPeriod.with(0, LocalTime.of(21, 00, 00), LocalTime.of(1, 00, 00)),
			TradingPeriod.with(1, LocalTime.of(9, 00, 00), LocalTime.of(10, 15, 00)),
			TradingPeriod.with(2, LocalTime.of(10, 30, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(3, LocalTime.of(13, 30, 00), LocalTime.of(15, 00, 00))),
	// ************************能源交易所END************************//

	// ************************中金所************************//
	/**
	 * 上证300期货
	 */
	IF(1, Exchange.CFFE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),
	/**
	 * 中证500期货
	 */
	IC(2, Exchange.CFFE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),
	/**
	 * 债券期货
	 */
	TF(3, Exchange.CFFE,
			// 股指期货交易时段
			TradingPeriod.with(0, LocalTime.of(9, 15, 00), LocalTime.of(11, 30, 00)),
			TradingPeriod.with(1, LocalTime.of(13, 00, 00), LocalTime.of(15, 15, 00))),
	// ************************中金所END************************//

	;

	private int symbolId;

	private Exchange exchange;

	private ImmutableSortedSet<TradingPeriod> tradingPeriodSet;

	private ChinaFuturesSymbol(int exchangeNo, Exchange exchange, TradingPeriod... tradingPeriods) {
		this.symbolId = exchange.id() + exchangeNo * 10000;
		this.exchange = exchange;
		this.tradingPeriodSet = ImmutableSets.newSortedSet(tradingPeriods);
	}

	@Override
	public int id() {
		return symbolId;
	}

	@Override
	public String code() {
		return name();
	}

	@Override
	public ImmutableSortedSet<TradingPeriod> tradingPeriodSet() {
		return tradingPeriodSet;
	}

	@Override
	public Exchange exchange() {
		return exchange;
	}

	// 建立SymbolId -> Symbol的映射
	private final static ImmutableIntObjectMap<ChinaFuturesSymbol> symbolIdMap = ImmutableMaps.IntObjectMapFactory()
			.from(
					// 将ChinaFuturesSymbol转换为Iterable
					MutableLists.newFastList(ChinaFuturesSymbol.values()),
					// 取SymbolId为Key
					ChinaFuturesSymbol::id, symbol -> symbol);

	// 建立SymbolNeam -> Symbol的映射
	private final static ImmutableMap<String, ChinaFuturesSymbol> symbolCodeMap = ImmutableMaps.newMap(
			// 将ChinaFuturesSymbol转换为Map
			Stream.of(ChinaFuturesSymbol.values()).collect(Collectors.toMap(
					// 取SymbolName为Key
					ChinaFuturesSymbol::name, symbol -> symbol)));

	public static ChinaFuturesSymbol of(String symbolCode) {
		String key = symbolCode.toUpperCase();
		ChinaFuturesSymbol chinaFuturesSymbol = symbolCodeMap.get(key);
		if (chinaFuturesSymbol == null)
			throw new IllegalArgumentException("Symbol Code -> " + symbolCode + " is no mapping object");
		return chinaFuturesSymbol;
	}

	public static ChinaFuturesSymbol of(int symbolId) {
		ChinaFuturesSymbol chinaFuturesSymbol = symbolIdMap.get(symbolId);
		if (chinaFuturesSymbol == null)
			throw new IllegalArgumentException("Symbol Id -> " + symbolId + " is no mapping object");
		return chinaFuturesSymbol;
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

	}

}
