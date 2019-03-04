package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.Enable;
import io.ffreedom.common.fsm.EnableController;

abstract class AbstractAccount implements Enable {

	private EnableController controller = EnableController.newInstance();

	@Override
	public boolean isEnabled() {
		return controller.isEnabled();
	}

	@Override
	public boolean isDisabled() {
		return controller.isDisabled();
	}

	@Override
	public void disable() {
		controller.disable();
	}

	@Override
	public void enable() {
		controller.enable();
	}

}
