package com.ydpp.eclipse.preferences;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ydpp.eclipse.CodeGeneratePlugin;
import com.ydpp.eclipse.dialog.ExportDialog;
import com.ydpp.eclipse.event.SelectChangedListener;
import com.ydpp.eclipse.listener.MouseListenerAdapter;
import com.ydpp.eclipse.ui.FileEditor;
import com.ydpp.eclipse.ui.TextAreaEditor;


/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class CodePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private FileEditor fileEditor;
	private TextAreaEditor textAreaEditor;
	private IPreferenceStore store;
	private Button exportBtn;
	private Button importBtn;

	public CodePreferencePage() {
		super(GRID);
		setPreferenceStore(CodeGeneratePlugin.getDefault().getPreferenceStore());
		setDescription("Code generate config");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
//		addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH,
//				"&Directory preference:", getFieldEditorParent()));
//		addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//				"&An example of a boolean preference", getFieldEditorParent()));
//
//		addField(new RadioGroupFieldEditor(PreferenceConstants.P_CHOICE,
//				"An example of a multiple-choice preference", 1,
//				new String[][] { { "&Choice 1", "choice1" },
//						{ "C&hoice 2", "choice2" } }, getFieldEditorParent()));
//		addField(new StringFieldEditor(PreferenceConstants.P_STRING,
//				"A &text preference:", getFieldEditorParent()));
		
		
		fileEditor = new FileEditor("template", "Template", "template", getFieldEditorParent());
		textAreaEditor = new TextAreaEditor("textarea", "Preview", getFieldEditorParent());
		store = CodeGeneratePlugin.getDefault().getPreferenceStore();
		fileEditor.setListSelectChangedListener(new SelectChangedListener() {
			@Override
			public void selectChanged(int index, String value) {
				listSelectChanged(index, value);
			}
		});
		addField(fileEditor);
		addField(textAreaEditor);
		
		RowLayout btnLayout = new RowLayout();
		Composite btns = new Composite(getFieldEditorParent(), NONE);
		btns.setLayout(btnLayout);
		
		exportBtn = new Button(btns, NONE);
		importBtn = new Button(btns, NONE);
		
		exportBtn.setText("export");
		importBtn.setText("import");
		exportBtn.addMouseListener(new MouseListenerAdapter(){
			@Override
			public void mouseUp(MouseEvent event) {
				exportConfig();
			}
		});
		importBtn.addMouseListener(new MouseListenerAdapter(){
			@Override
			public void mouseUp(MouseEvent event) {
				importConfig();
			}
		});
		
		
	}

	/**
	 * 导入配置
	 */
	void importConfig() {
		FileDialog dialog = new FileDialog(getShell());
		String fileName = dialog.open();
		File file = new File(fileName);
		if (file.exists()) {
			try {
				String content = IOUtils.toString(new FileInputStream(file));
				Element root = DocumentHelper.parseText(content).getRootElement();
				
				for (String key : PreferenceInitializer.STORE_KEYS) {
					store.setValue(key, root.elementText(key));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		textAreaEditor.setStringValue(store.getString(textAreaEditor.getPreferenceName()));
	}
	
	/**
	 * 导出配置
	 */
	void exportConfig() {
		new ExportDialog(getShell()).open();
	}
	
	void listSelectChanged(int index, String value) {
		textAreaEditor.setPreferenceName(value);
		textAreaEditor.setStringValue(store.getString(value));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}