package code_generate.preferences;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;

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
    
    
    public FileEditor(String name, String labelText,
            String dirChooserLabelText, Composite parent) {
        init(name, labelText);
        this.dirChooserLabelText = dirChooserLabelText;
        createControl(parent);
        getAddButton().setEnabled(false);
        getRemoveButton().setEnabled(false);
        getDownButton().setEnabled(false);
        getUpButton().setEnabled(false);
        
//        getRemoveButton().removeSelectionListener(getRemoveButton());
        
        
        
        getList().add("dao");
        getList().add("service");
        getList().add("web");
    }
    
    void removeListeners(Button btn) {
    	Listener[] listeners = btn.getListeners(SWT.Selection);
    	for (Listener listener : listeners) {
    		System.out.println(listener);
			//btn.removeSelectionListener((SelectionListener) listener);
		}
    }
    
//    private Composite buttonBox;
    
    @Override
    public Composite getButtonBoxControl(Composite parent) {
    	// TODO Auto-generated method stub
    	return super.getButtonBoxControl(parent);
//    	Composite boxControl = super.getButtonBoxControl(parent);
//    	
////    	removeListeners(getAddButton());
////        removeListeners(getRemoveButton());
////        removeListeners(getDownButton());
////        removeListeners(getUpButton());
//    	
//    	boxControl.setEnabled(false);
//    	
//        return boxControl;
    	
    	
//    	if (buttonBox == null) {
//            buttonBox = new Composite(parent, SWT.NULL);
//            GridLayout layout = new GridLayout();
//            layout.marginWidth = 0;
//            buttonBox.setLayout(layout);
////            createButtons(buttonBox);
//            buttonBox.addDisposeListener(new DisposeListener() {
//                public void widgetDisposed(DisposeEvent event) {
////                    addButton = null;
////                    removeButton = null;
////                    upButton = null;
////                    downButton = null;
////                    buttonBox = null;
//                }
//            });
//
//        } else {
//            checkParent(buttonBox, parent);
//        }
//
//        selectionChanged();
//        return buttonBox;
    	
    }
    
    @Override
    public void createSelectionListener() {
    	super.createSelectionListener();
//    	super.selectionListener = new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		}; 
    }
    
    
    
	
	@Override
	protected String createList(String[] items) {
		StringBuffer path = new StringBuffer("");//$NON-NLS-1$
        for (int i = 0; i < items.length; i++) {
            path.append(items[i]);
            path.append(File.pathSeparator);
        }
        return path.toString();
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
