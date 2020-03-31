package com.ib.apidemo;

import com.ib.client.ContractCondition;
import com.ib.client.ContractLookuper;

public class ContractConditionPanel<T> extends OperatorConditionPanel<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5798334042136033142L;

	public ContractConditionPanel(ContractCondition c, ContractLookuper lookuper) {
		super(c);

		final ContractCondition condition = (ContractCondition) m_condition;

		add(new ContractLookupButton(condition.conId(), condition.exchange(), lookuper) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4161338713801384024L;

			protected void actionPerformed(int refConId, String refExchId) {
				condition.conId(refConId);
				condition.exchange(refExchId);
			}
		});
	}

}
