package io.ffreedom.redstone.adaptor.simulator;

import io.ffreedom.common.param.ParamMap;
import io.ffreedom.redstone.core.adaptor.Adaptor;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public abstract class BaseSimAdaptor implements Adaptor {

	protected ParamMap<SimAdaptorParams> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	public BaseSimAdaptor(ParamMap<SimAdaptorParams> paramMap) {
		this.paramMap = paramMap;
		initSocketConfigurator();
		init();
	}

	private void initSocketConfigurator() {
		this.mdConfigurator = SocketConfigurator
				.builder()
				.setHost(paramMap.getString(SimAdaptorParams.SIM_MD_HOST))
				.setPort(paramMap.getInteger(SimAdaptorParams.SIM_MD_PORT))
				.build();
		
		this.tdConfigurator = SocketConfigurator
				.builder()
				.setHost(paramMap.getString(SimAdaptorParams.SIM_TD_HOST))
				.setPort(paramMap.getInteger(SimAdaptorParams.SIM_TD_PORT))
				.build();
	}

	@Override
	public int getAdaptorId() {
		return 0;
	}

}
