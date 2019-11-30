package io.redstone.adaptor.simulator;

import io.mercury.common.param.ParamKeyMap;
import io.mercury.transport.socket.config.SocketConfigurator;
import io.redstone.core.adaptor.api.Adaptor;

public abstract class BaseSimAdaptor implements Adaptor {

	protected ParamKeyMap<SimAdaptorParams> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	public BaseSimAdaptor(ParamKeyMap<SimAdaptorParams> paramMap) {
		this.paramMap = paramMap;
		initSocketConfigurator();
	}

	private void initSocketConfigurator() {
		this.mdConfigurator = SocketConfigurator.builder().host(paramMap.getString(SimAdaptorParams.MdHost))
				.port(paramMap.getInteger(SimAdaptorParams.MdPort)).build();
		this.tdConfigurator = SocketConfigurator.builder().host(paramMap.getString(SimAdaptorParams.TdHost))
				.port(paramMap.getInteger(SimAdaptorParams.TdPort)).build();
	}

	@Override
	public int adaptorId() {
		return 0;
	}

}
