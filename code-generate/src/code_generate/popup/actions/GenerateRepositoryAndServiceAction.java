package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.GeneratorUtils;

public class GenerateRepositoryAndServiceAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {

		GeneratorUtils.generate(action, "resources/repository", "repository", "Repository");
		GeneratorUtils.generate(action, "resources/repositoryImpl", "repository", "RepositoryImpl");
		GeneratorUtils.generate(action, "resources/service", "service", "Service");
		GeneratorUtils.generate(action, "resources/serviceImpl", "service", "ServiceImpl");
		
		
		
	}
	
	
}


