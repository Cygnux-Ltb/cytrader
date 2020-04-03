package com.ib.apidemo;

import com.ib.client.MarginCondition;
import com.ib.client.OrderCondition;

public class MarginContidionPanel extends OperatorConditionPanel<MarginCondition> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8588702493163206116L;

	public MarginContidionPanel(MarginCondition condition) {
		super(condition);
		
		m_value.setText(condition().percent());
		
		add("Operator", m_operator);
		add("Cushion (%)", m_value);
	}
	
	public OrderCondition onOK() {
		super.onOK();
		condition().percent(m_value.getInt());
		
		return condition();
	}
}
