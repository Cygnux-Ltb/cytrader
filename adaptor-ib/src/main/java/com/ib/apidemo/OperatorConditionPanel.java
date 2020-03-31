package com.ib.apidemo;

import com.ib.apidemo.util.TCombo;
import com.ib.apidemo.util.UpperField;
import com.ib.client.OperatorCondition;
import com.ib.client.OrderCondition;

public class  OperatorConditionPanel<T> extends OnOKPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8423258224243201990L;
	OperatorCondition m_condition;
	final TCombo<String> m_operator = new TCombo<String>("<=", ">=");
	final UpperField m_value = new UpperField();
	
	public OperatorConditionPanel(OperatorCondition condition) {
		m_condition = condition;
		
		m_operator.setSelectedIndex(m_condition.isMore() ? 1 : 0);
	}
	
	public OrderCondition onOK() {
		m_condition.isMore(m_operator.getSelectedIndex() == 1);
		
		return m_condition;
	}
	
	protected T condition() {
		return (T)m_condition;
	}
}
