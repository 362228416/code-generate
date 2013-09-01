package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.SpringXmlUtils;

public class AddTilesViewAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
//		SpringXmlUtils.addBean(action, "tilesViewResolver");
		SpringXmlUtils.addBean(action, "tilesConfigurer", "tilesViewResolver", "multipartResolver");
	}


}
