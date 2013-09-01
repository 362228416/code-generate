package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;

import utils.GeneratorUtils;

public class GenerateRepositoryAndServiceAndControllerAction  extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {
		
//		GeneratorUtils.generate(action, "resources/repository", "repository", "Repository");
//		GeneratorUtils.generate(action, "resources/repositoryImpl", "repository", "RepositoryImpl");
//		GeneratorUtils.generate(action, "resources/service", "service", "Service");
//		GeneratorUtils.generate(action, "resources/serviceImpl", "service", "ServiceImpl");
//		GeneratorUtils.generate(action, "resources/controller", "web", "Controller");
		
		NewDialog dialog = new NewDialog(null);
		if (dialog.open() == 0) {
			if (dialog.selectRepository()) {
				GeneratorUtils.generate(action, "resources/repository", dialog.getRepositoryPackage(), "Repository");
				GeneratorUtils.generate(action, "resources/repositoryImpl", dialog.getRepositoryPackage(), "RepositoryImpl");
			}
			if (dialog.selectService()) {
				GeneratorUtils.generate(action, "resources/service", dialog.getServicePackage(), "Service");
				GeneratorUtils.generate(action, "resources/serviceImpl", dialog.getServicePackage(), "ServiceImpl");
			}
			if (dialog.selectController()) {
				GeneratorUtils.generate(action, "resources/controller", dialog.getControllerPackage(), "Controller");
			}
		}
		
		
	}

}
