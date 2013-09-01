package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.SpringXmlUtils;

public class AddFreemarkerViewAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
//		SpringXmlUtils.addBean(action, "freeMarkerViewResolver");
		SpringXmlUtils.addBean(action, "freeMarkerConfigurer", "freeMarkerViewResolver", "multipartResolver");
	}


}
