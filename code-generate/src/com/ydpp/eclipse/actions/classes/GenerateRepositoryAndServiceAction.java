package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.GeneratorUtils;


/**
 * 
 * @author john
 *
 */
public class GenerateRepositoryAndServiceAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute (IAction action) throws Exception {

		GeneratorUtils.generate(action, getString("repository"), "repository", "Repository");
		GeneratorUtils.generate(action, getString("repositoryImpl"), "repository", "RepositoryImpl");
		GeneratorUtils.generate(action, getString("service"), "service", "Service");
		GeneratorUtils.generate(action, getString("serviceImpl"), "service", "ServiceImpl");
		
		
		
	}
	
	
}


