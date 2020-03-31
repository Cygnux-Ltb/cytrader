package com.ib.apidemo;

import java.awt.event.MouseEvent;

import com.ib.apidemo.util.HtmlButton;
import com.ib.client.ContractLookuper;

public abstract class ContractLookupButton extends HtmlButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3928289256388305124L;
	final ContractSearchDlg m_contractSearchdlg;
			
	public ContractLookupButton(int conId, String exchange, ContractLookuper lookuper) {
		super("");
		
		m_contractSearchdlg = new ContractSearchDlg(conId, exchange, lookuper);
		
		setText(m_contractSearchdlg.refContract() == null ? "search..." : m_contractSearchdlg.refContract());
	}
	
	@Override
	protected void onClicked(MouseEvent e) {		
		m_contractSearchdlg.setLocationRelativeTo(this.getParent());
		m_contractSearchdlg.setVisible(true);
				
		setText(m_contractSearchdlg.refContract() == null ? "search..." : m_contractSearchdlg.refContract());
		actionPerformed();
		actionPerformed(m_contractSearchdlg.refConId(), m_contractSearchdlg.refExchId());
	}

	protected abstract void actionPerformed(int refConId, String refExchId);
}
