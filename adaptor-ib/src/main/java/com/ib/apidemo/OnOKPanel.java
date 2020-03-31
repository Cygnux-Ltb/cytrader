package com.ib.apidemo;

import com.ib.apidemo.util.VerticalPanel;
import com.ib.client.OrderCondition;

public abstract class OnOKPanel extends VerticalPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8582788976367104184L;

	public abstract OrderCondition onOK();
}