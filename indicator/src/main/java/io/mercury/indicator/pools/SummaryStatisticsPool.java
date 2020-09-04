package io.mercury.indicator.pools;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.eclipse.collections.api.map.ConcurrentMutableMap;

import io.mercury.common.collections.MutableMaps;

@ThreadSafe
public final class SummaryStatisticsPool {

	private static final ConcurrentMutableMap<Long, SummaryStatistics> SummaryStatisticsMap = MutableMaps
			.newConcurrentHashMap();

	public static SummaryStatistics getSummaryStatistics() {
		return getSummaryStatistics(Thread.currentThread());
	}

	public static SummaryStatistics getSummaryStatistics(Thread currentThread) {
		return SummaryStatisticsMap.getIfAbsentPut(currentThread.getId(), SummaryStatistics::new);
	}

}
