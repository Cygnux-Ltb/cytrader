package io.mercury.launch;

public final class FtdcAdaptorStartup {


	public static void main(String[] args) {
//		
//		Assertor.requiredLength(args, 4, "input args");
//		String confAddr = args[0];
//		String group = args[1];
//		String dataId = args[2];
//		String logName = args[3];
//		long datetime = DateTimeUtil.datetimeOfSecond();
//		LoggerSetter.logFileName("redstone-" + logName + "-" + datetime);
//		LoggerSetter.logLevel(LogLevel.INFO);
//
//		Logger logger = CommonLoggerFactory.getLogger(FtdcAdaptorStartup.class);
//
//		// 读取Nacos配置
//		String runtimeInfo = NacosReader.readSaved(confAddr, group, dataId);
//		Map<String, Object> runtimeMap = JsonUtil.toMap(runtimeInfo);
//
//		int strategyId = (int) runtimeMap.get("strategyId");
//		Assertor.greaterThan(strategyId, 0, "strategyId");
//		Assertor.lessThan(strategyId, 100, "strategyId");
//		logger.info("strategyId : {}", strategyId);
//
//		int subAccountId = (int) runtimeMap.get("subAccountId");
//		Assertor.greaterThan(subAccountId, 0, "subAccountId");
//		Assertor.lessThan(subAccountId, 100, "subAccountId");
//		logger.info("subAccountId : {}", subAccountId);
//
//		int adaptorId = (int) runtimeMap.get("adaptorId");
//		String adaptorName = (String) runtimeMap.get("adaptorName");
//		logger.info("adaptorId : {}", adaptorId);
//		logger.info("adaptorName : {}", adaptorName);
//
//		String instrumentConfName = (String) runtimeMap.get("instrumentConf");
//		String adaptorConfName = (String) runtimeMap.get("adaptorConf");
//		logger.info("read instrumentConf : {}", instrumentConfName);
//		logger.info("read adaptorConf : {}", adaptorConfName);
//
//		String adaptorConfStr = NacosReader.readSaved(confAddr, group, adaptorConfName);
//		ImmutableParamMap<FtdcAdaptorParam> ftdcParam = new ImmutableParamMap<>(FtdcAdaptorParam.values(),
//				JsonUtil.toMap(adaptorConfStr));
//
//		String instrumentConfStr = NacosReader.readSaved(confAddr, group, instrumentConfName);
//		InstrumentConf instrumentConf = JsonUtil.toObject(instrumentConfStr, InstrumentConf.class);
//
//		for (String instrument : instrumentConf.getInstruments()) {
//			String symbolCode = ChinaFuturesSupporter.analyzeSymbolCode(instrument);
//			int term = ChinaFuturesSupporter.analyzeInstrumentTerm(instrument);
//			ChinaFuturesSymbol symbol = ChinaFuturesSymbol.of(symbolCode);
//			Instrument futures = new ChinaFutures(symbol, term);
//			InstrumentKeeper.putInstrument(futures);
//		}
//
//		StrategyScheduler scheduler = new SingleStrategyScheduler();
//		// StrategyScheduler scheduler = new
//		// SpscQueueStrategyScheduler(BufferSize.POW2_12);
//
//		// TODO 实际创建账号
//		Account account = new Account(1, "", "", null);
//
//		// 创建InboundAdaptor
//		Adaptor adaptor = new FtdcAdaptor(adaptorId, adaptorName, account, scheduler, ftdcParam);
//
////		TimePeriodPool.Singleton.register(ChinaFuturesSymbol.values(), TimePeriod.values());
//
////		TradingPeriodPool.Singleton.register(ChinaFuturesSymbol.values());
//
//		VolumePowerStrategy strategy = new VolumePowerStrategy(strategyId, subAccountId,
//				new ChinaFutures(ChinaFuturesSymbol.RB, 2010), 1750);
//
//		strategy.addAdaptor(adaptor);
//		strategy.initialize(() -> true);
//
//		StrategyKeeper.putStrategy(strategy);
//		AdaptorKeeper.putAdaptor(subAccountId, adaptor);
//
//		adaptor.startup();

	}

}
