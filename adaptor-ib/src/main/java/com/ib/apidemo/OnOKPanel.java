package com.ib.apidemo;

import com.ib.apidemo.util.VerticalPanel;
import com.ib.client.OrderCondition;

public abstract class OnOKPanel extends VerticalPanel {
	public abstract OrderCondition onOK();
}