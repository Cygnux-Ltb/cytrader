package com.ib.apidemo;

import com.ib.client.ContractLookuper;
import com.ib.client.OrderCondition;
import com.ib.client.PercentChangeCondition;

public class PercentConditionPanel extends ContractConditionPanel<PercentChangeCondition> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2933136467045351380L;


	public PercentConditionPanel(PercentChangeCondition condition, ContractLookuper lookuper) {
		super(condition, lookuper);
		
		m_value.setText(condition().changePercent());
		
		add("Operator", m_operator);
		add("Percentage Change", m_value);
	}
	
	
	public OrderCondition onOK() {
		super.onOK();
		condition().changePercent(m_value.getDouble());
		
		return condition();
	}
}
