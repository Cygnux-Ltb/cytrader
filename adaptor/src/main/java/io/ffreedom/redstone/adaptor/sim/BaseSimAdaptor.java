package io.ffreedom.redstone.adaptor.sim;

import io.ffreedom.common.param.ParamMap;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.core.adaptor.Adaptor;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public abstract class BaseSimAdaptor implements Adaptor {

	protected ParamMap<AdaptorParams> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	public BaseSimAdaptor(ParamMap<AdaptorParams> paramMap) {
		this.paramMap = paramMap;
		initSocketConfigurator();
		init();
	}

	private void initSocketConfigurator() {
		this.mdConfigurator = SocketConfigurator
				.builder()
				.setHost(paramMap.getString(AdaptorParams.SIM_MD_HOST))
				.setPort(paramMap.getInteger(AdaptorParams.SIM_MD_PORT))
				.build();
		
		this.tdConfigurator = SocketConfigurator
				.builder()
				.setHost(paramMap.getString(AdaptorParams.SIM_TD_HOST))
				.setPort(paramMap.getInteger(AdaptorParams.SIM_TD_PORT))
				.build();
	}

	@Override
	public int getAdaptorId() {
		return 0;
	}

}
