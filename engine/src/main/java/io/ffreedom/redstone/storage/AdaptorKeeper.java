package io.ffreedom.redstone.storage;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;

public class AdaptorKeeper {

	// 存储OutboundAdaptor, 使用subAccountId索引
	private MutableIntObjectMap<OutboundAdaptor> outboundAdaptorMap = ECollections.newIntObjectHashMap();

	private final static AdaptorKeeper InnerInstance = new AdaptorKeeper();

	private AdaptorKeeper() {
	}

	public static OutboundAdaptor getOutboundAdaptor(int subAccountId) {
		return InnerInstance.outboundAdaptorMap.get(subAccountId);
	}

	public static OutboundAdaptor putOutboundAdaptor(int subAccountId, OutboundAdaptor adaptor) {
		return InnerInstance.outboundAdaptorMap.put(subAccountId, adaptor);
	}

}
