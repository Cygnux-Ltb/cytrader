package io.redstone.core.adaptor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;

public class AdaptorKeeper {

	// 存储OutboundAdaptor, 使用subAccountId索引
	private MutableIntObjectMap<Adaptor> outboundAdaptorMap = MutableMaps.newIntObjectHashMap();

	private final static AdaptorKeeper InnerInstance = new AdaptorKeeper();

	private AdaptorKeeper() {
	}

	public static Adaptor getAdaptor(int subAccountId) {
		return InnerInstance.outboundAdaptorMap.get(subAccountId);
	}

	public static Adaptor putAdaptor(int subAccountId, Adaptor adaptor) {
		return InnerInstance.outboundAdaptorMap.put(subAccountId, adaptor);
	}

}
