package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.SpringXmlUtils;

public class AddDataSourceTransactionAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		SpringXmlUtils.addBean(action, "dataSourceTransactionManager");
		SpringXmlUtils.addBean(action, "tx:annotation-driven");
		SpringXmlUtils.addBean(action, "txAdvice");
		SpringXmlUtils.addBean(action, "aop:config");
	}

}
