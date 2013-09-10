package com.ydpp.eclipse.actions.xml;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.SpringXmlUtils;


public class AddDataSourceTransactionAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		SpringXmlUtils.addBean(action, "dataSourceTransactionManager");
		SpringXmlUtils.addBean(action, "tx:annotation-driven");
		SpringXmlUtils.addBean(action, "txAdvice");
		SpringXmlUtils.addBean(action, "aop:config");
	}

}
