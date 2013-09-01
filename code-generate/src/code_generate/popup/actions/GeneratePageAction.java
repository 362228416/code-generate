package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

public class GeneratePageAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute(IAction action) throws Exception {
		System.out.println(action);
	}
	
}
