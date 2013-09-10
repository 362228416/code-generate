package com.ydpp.eclipse.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.ydpp.eclipse.CodeGeneratePlugin;

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
		return store.getString(key);
	}
	
	public String getDefaultString(String key) {
		return store.getDefaultString(key);
	}
	
	protected IWorkbenchPart workbenchPart;
	protected ISelection selection;
	protected IPreferenceStore store = CodeGeneratePlugin.getDefault().getPreferenceStore();

}
