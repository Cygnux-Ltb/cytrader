/* Copyright (C) 2013 Interactive Brokers LLC. All rights reserved.  This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package com.ib.apidemo.util;

import javax.swing.JComboBox;

public class TCombo<T> extends JComboBox<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 29655236894815143L;

	@SafeVarargs
	public TCombo(T... strs) {
		super(strs);
	}

	public String getText() {
		return getSelectedItem() == null ? null : getSelectedItem().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getSelectedItem() {
		return (T) super.getSelectedItem();
	}
}
