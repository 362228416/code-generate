package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.SpringXmlUtils;

public class AddJspViewAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		SpringXmlUtils.addBean(action, "jstlViewResolver", "multipartResolver");
	}


}
