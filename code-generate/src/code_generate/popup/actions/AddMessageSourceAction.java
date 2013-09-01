package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.SpringXmlUtils;

public class AddMessageSourceAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		SpringXmlUtils.addBean(action, "messageSource");
	}


}
