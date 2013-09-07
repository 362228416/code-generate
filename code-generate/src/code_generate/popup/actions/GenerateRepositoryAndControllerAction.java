package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.GeneratorUtils;

public class GenerateRepositoryAndControllerAction  extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {
		
		GeneratorUtils.generate(action, store.getString("repository"), "repository", "Repository");
		GeneratorUtils.generate(action, store.getString("repositoryImpl"), "repository", "RepositoryImpl");
		GeneratorUtils.generate(action, store.getString("controller"), "web", "Controller");
		
		
	}
}
