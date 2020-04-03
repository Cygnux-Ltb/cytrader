package com.ib.apidemo;

import com.ib.client.ContractLookuper;
import com.ib.client.OrderCondition;
import com.ib.client.VolumeCondition;

public class VolumeConditionPanel extends ContractConditionPanel<VolumeCondition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6959764367752012781L;

	public VolumeConditionPanel(VolumeCondition condition, ContractLookuper lookuper) {
		super(condition, lookuper);

		m_value.setText(condition().volume());

		add("Operator", m_operator);
		add("Volume", m_value);
	}

	@Override
	public OrderCondition onOK() {
		super.onOK();

		condition().volume(m_value.getInt());

		return condition();
	}
}
