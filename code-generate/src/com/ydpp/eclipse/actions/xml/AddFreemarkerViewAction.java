package com.ydpp.eclipse.actions.xml;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.SpringXmlUtils;

/**
 * 
 * @author john
 *
 */
public class AddFreemarkerViewAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
//		SpringXmlUtils.addBean(action, "freeMarkerViewResolver");
		SpringXmlUtils.addBean(action, "freeMarkerConfigurer", "freeMarkerViewResolver", "multipartResolver");
	}


}
