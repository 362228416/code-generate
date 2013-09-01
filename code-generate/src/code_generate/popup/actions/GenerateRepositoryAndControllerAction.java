package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.GeneratorUtils;

public class GenerateRepositoryAndControllerAction  extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {
		
		GeneratorUtils.generate(action, "resources/repository", "repository", "Repository");
		GeneratorUtils.generate(action, "resources/repositoryImpl", "repository", "RepositoryImpl");
		GeneratorUtils.generate(action, "resources/controller", "web", "Controller");
		
		
	}
}
