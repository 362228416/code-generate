package com.ydpp.eclipse.actions.classes.struts;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.classes.SimpleClassGenerateor;

/**
 *
 * @author john
 */
public class ActionRestGenerator extends SimpleClassGenerateor {

	@Override
	protected void run(IAction action, String packageName) throws Exception {
		generate(action, "struts-rest", packageName, "action-rest-suffix");
	}
	
	@Override
	protected String getDefaultValue() {
		return "actions";
	}

}
