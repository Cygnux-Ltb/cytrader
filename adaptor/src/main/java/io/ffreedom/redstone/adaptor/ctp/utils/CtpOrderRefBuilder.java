package io.ffreedom.redstone.adaptor.ctp.utils;

import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;

public final class CtpOrderRefBuilder {

	private int CTP_MAX_APP_ID = 214;

	private int CTP_MIN_APP_ID = 100;

	private int CTP_MAX_STRATEGY_ID = 74;

	private int CTP_MIN_STRATEGY_ID = 1;

	private MutableIntIntMap orderRefMap = new IntIntHashMap();

	public int build(int appId, int strategyId) {
		if (appId > CTP_MAX_APP_ID || strategyId > CTP_MIN_APP_ID || appId < CTP_MAX_STRATEGY_ID
				|| strategyId < CTP_MIN_STRATEGY_ID) {
			return -1;
		}
		int highOrderRef = appId * 10000000 + strategyId * 100000;
		if (orderRefMap.containsKey(highOrderRef)) {
			int orderRef = orderRefMap.get(highOrderRef);
			orderRefMap.put(highOrderRef, ++orderRef);
			return orderRef;
		} else {
			int orderRef = highOrderRef + 1;
			orderRefMap.put(highOrderRef, orderRef);
			return orderRef;
		}
	}

	public static void main(String[] args) {
		CtpOrderRefBuilder ctpOrderRefBuilder = new CtpOrderRefBuilder();

		int build0 = ctpOrderRefBuilder.build(100, 5);
		int build1 = ctpOrderRefBuilder.build(101, 7);
		int build2 = ctpOrderRefBuilder.build(100, 5);

		System.out.println(Long.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(build0);
		System.out.println(build1);
		System.out.println(build2);
		
		System.out.println(Integer.MAX_VALUE);
	}

}
