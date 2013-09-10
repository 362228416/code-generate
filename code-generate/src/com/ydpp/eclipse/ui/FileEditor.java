package com.ydpp.eclipse.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;

import com.ydpp.eclipse.CodeGeneratePlugin;
import com.ydpp.eclipse.event.SelectChangedListener;

/**
 * 
 * @author john
 *
 */
public class FileEditor extends ListEditor {

	/**
     * The last path, or <code>null</code> if none.
     */
    private String lastPath;

    /**
     * The special label text for directory chooser, 
     * or <code>null</code> if none.
     */
    private String dirChooserLabelText;

	private SelectChangedListener changedListener;
    
    
    public FileEditor(String name, String labelText,
            String dirChooserLabelText, Composite parent) {
        init(name, labelText);
        this.dirChooserLabelText = dirChooserLabelText;
        createControl(parent);
        getAddButton().setEnabled(false);
        getRemoveButton().setEnabled(false);
        getDownButton().setEnabled(false);
        getUpButton().setEnabled(false);
        
//        List list = getList();
//        if (list.getItemCount() == 0) {
//        	list.add("repository");
//        	list.add("repositoryImpl");
//        	list.add("service");
//        	list.add("serviceImpl");
//        	list.add("controller");
//        	list.add("toJson");
//        }
        
        
    }
    
    @Override
    protected void selectionChanged() {
    	List list = getList();
    	int index = list.getSelectionIndex();
    	if (index > -1) {
	    	if (changedListener != null) {
	    		String select = list.getItem(index);
	    		changedListener.selectChanged(list.getSelectionIndex(), select);
	    	}
    	}
    	super.selectionChanged();
    }
    
    public void setListSelectChangedListener(SelectChangedListener changedListener) {
    	this.changedListener = changedListener;
    }
    
    
    @Override
    public void createSelectionListener() {
    	super.createSelectionListener();
    }
    
    
    
	
	@Override
	protected String createList(String[] items) {
//		StringBuffer path = new StringBuffer("");//$NON-NLS-1$
//        for (int i = 0; i < items.length; i++) {
//            path.append(items[i]);
//            path.append(File.pathSeparator);
//            System.out.println(items[i]);
//        }
//        System.out.println(path.toString());
//        return path.toString();
        return "";
	}
	
	
	@Override
	protected void doLoadDefault() {
		super.doLoadDefault();
		String[] items = getList().getItems();
		IPreferenceStore store = CodeGeneratePlugin.getDefault().getPreferenceStore();
		for (String item : items) {
			store.setToDefault(item);
		}
	}
	
	@Override
	protected String getNewInputObject() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SHEET);
        if (dirChooserLabelText != null) {
        	dialog.setText(dirChooserLabelText);
		}
        if (lastPath != null) {
            if (new File(lastPath).exists()) {
				dialog.setFilterPath(lastPath);
			}
        }
        String dir = dialog.open();
        if (dir != null) {
            dir = dir.trim();
            if (dir.length() == 0) {
				return null;
			}
            lastPath = dir;
        }
        return dir;
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected String[] parseString(String stringList) {
		StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator
                + "\n\r");//$NON-NLS-1$
        ArrayList v = new ArrayList();
        while (st.hasMoreElements()) {
            v.add(st.nextElement());
        }
        return (String[]) v.toArray(new String[v.size()]);
	}

}
