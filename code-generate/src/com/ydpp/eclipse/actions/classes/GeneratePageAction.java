package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;


public class GeneratePageAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute(IAction action) throws Exception {
		System.out.println(action);
	}
	
}
