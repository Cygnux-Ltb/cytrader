package io.redstone.core.keeper;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.redstone.core.adaptor.Adaptor;

public final class AdaptorKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(AdaptorKeeper.class);

	// 存储OutboundAdaptor, 使用subAccountId索引
	private static final MutableIntObjectMap<Adaptor> SubAccountAdaptorMap = MutableMaps.newIntObjectHashMap();

	private AdaptorKeeper() {
	}

	public static Adaptor getAdaptor(int subAccountId) {
		return SubAccountAdaptorMap.get(subAccountId);
	}

	public static void putAdaptor(@Nonnull Adaptor adaptor) {
		adaptor.account().subAccounts().each(subAccount -> {
			int subAccountId = subAccount.subAccountId();
			SubAccountAdaptorMap.put(subAccountId, adaptor);
			log.info("AdaptorKeeper :: Put adaptor, subAccountId==[{}], ");
		});

	}

}
