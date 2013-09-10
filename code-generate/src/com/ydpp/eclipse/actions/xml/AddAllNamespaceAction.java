package com.ydpp.eclipse.actions.xml;

import java.util.Set;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.SpringBeanConfigUtils;
import com.ydpp.util.SpringXmlUtils;


public class AddAllNamespaceAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		Set<Object> set = SpringBeanConfigUtils.np.keySet();
		for (Object key : set) {
			SpringXmlUtils.addNamespace(action, key.toString());
		}
	}

}
