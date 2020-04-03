package com.ib.apidemo;

import com.ib.client.OrderCondition;
import com.ib.client.TimeCondition;

public class TimeConditionPanel extends OperatorConditionPanel<TimeCondition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2932940954302798788L;

	public TimeConditionPanel(TimeCondition condition) {
		super(condition);

		m_value.setText(condition().time());
		
		add("Operator", m_operator);
		add("Time", m_value);
	}
	
	@Override
	public OrderCondition onOK() {
		super.onOK();	
		condition().time(m_value.getText());
		
		return condition();
	}
}
