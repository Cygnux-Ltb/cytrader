/* Copyright (C) 2013 Interactive Brokers LLC. All rights reserved.  This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package com.ib.apidemo;

import com.ib.apidemo.util.NewTabbedPanel;

public class OptionsPanel extends NewTabbedPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7231300421254918261L;
	private final OptionChainsPanel m_optionChains = new OptionChainsPanel();
	private final ExercisePanel m_exercisePanel = new ExercisePanel();
	
	OptionsPanel() {
		NewTabbedPanel tabs = this;
		tabs.addTab( "Option Chains", m_optionChains);
		tabs.addTab( "Option Exercise", m_exercisePanel);
	}
}
