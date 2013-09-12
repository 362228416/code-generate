package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.eclipse.dialog.NewDialog;
import com.ydpp.util.GeneratorUtils;


/**
 * 
 * @author john
 *
 */
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
				GeneratorUtils.generate(action, getString("repository"), dialog.getRepositoryPackage(), "Repository");
				GeneratorUtils.generate(action, getString("repositoryImpl"), dialog.getRepositoryPackage(), "RepositoryImpl");
			}
			if (dialog.selectService()) {
				GeneratorUtils.generate(action, getString("service"), dialog.getServicePackage(), "Service");
				GeneratorUtils.generate(action, getString("serviceImpl"), dialog.getServicePackage(), "ServiceImpl");
			}
			if (dialog.selectController()) {
				GeneratorUtils.generate(action, getString("controller"), dialog.getControllerPackage(), "Controller");
			}
		}
		
		
	}

}
