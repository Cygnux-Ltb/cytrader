package io.redstone.adaptor.simulator;

import io.mercury.common.param.ImmutableParamMap;
import io.mercury.transport.socket.configurator.SocketConfigurator;
import io.redstone.core.adaptor.api.Adaptor;

public abstract class BaseSimAdaptor implements Adaptor {

	protected ImmutableParamMap<SimAdaptorParams> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	public BaseSimAdaptor(ImmutableParamMap<SimAdaptorParams> paramMap) {
		this.paramMap = paramMap;
		initSocketConfigurator();
	}

	private void initSocketConfigurator() {
		this.mdConfigurator = SocketConfigurator.builder().host(paramMap.getString(SimAdaptorParams.MdHost))
				.port(paramMap.getInt(SimAdaptorParams.MdPort)).build();
		this.tdConfigurator = SocketConfigurator.builder().host(paramMap.getString(SimAdaptorParams.TdHost))
				.port(paramMap.getInt(SimAdaptorParams.TdPort)).build();
	}

	@Override
	public int adaptorId() {
		return 0;
	}

}
