package io.ffreedom.redstone.core.account;

public interface Broker {

	int getBroketId();

	String getBroketName();

	Broker GeneralBroker = new Broker() {

		@Override
		public int getBroketId() {
			return 0;
		}

		@Override
		public String getBroketName() {
			return "CommonBroker";
		}

	};

}
