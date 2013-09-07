package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.GeneratorUtils;

public class GenerateRepositoryAndServiceAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {

		GeneratorUtils.generate(action, store.getString("repository"), "repository", "Repository");
		GeneratorUtils.generate(action, store.getString("repositoryImpl"), "repository", "RepositoryImpl");
		GeneratorUtils.generate(action, store.getString("service"), "service", "Service");
		GeneratorUtils.generate(action, store.getString("serviceImpl"), "service", "ServiceImpl");
		
		
		
	}
	
	
}


