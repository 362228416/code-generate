package com.ydpp.eclipse.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.ydpp.eclipse.config.CodeConfig;

/**
 * 
 * @author john
 *
 */
public class IObjectActionDelegateAdapter implements IObjectActionDelegate {

	@Override
	public void run(IAction action) {
		try {
			execute(action);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute(IAction action) throws Exception {}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
	}
	
	public String getString(String key) {
		return CodeConfig.getString(key);
	}
	
	public String getDefaultString(String key) {
		return CodeConfig.getDefaultString(key);
	}
	
	public String getMessage(String key, Object... args) {
		return CodeConfig.getMessage(key, args);
	}
	
	protected IWorkbenchPart workbenchPart;
	protected ISelection selection;
	
	public Shell getShell() {
		return workbenchPart.getSite().getShell();
	}
	
	public String getSuffix(String key) {
		return "";
	}

}
