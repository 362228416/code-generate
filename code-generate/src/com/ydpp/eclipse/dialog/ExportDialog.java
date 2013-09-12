package com.ydpp.eclipse.dialog;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.ydpp.eclipse.CodeGeneratePlugin;
import com.ydpp.eclipse.preferences.PreferenceInitializer;

/**
 * 导出配置对话框
 * @author john
 */
public class ExportDialog extends Dialog {

	protected DirectoryFieldEditor dirEditor;

	public ExportDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void okPressed() {
		
		String fileName = getStringValue() + "/config.xml";
		IPreferenceStore store = CodeGeneratePlugin.getDefault().getPreferenceStore();
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("config");
		
		for (String key : PreferenceInitializer.STORE_KEYS) {
			Element element = root.addElement(key);
			element.setText(store.getString(key));
		}
		
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(fileName));
			writer.write(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		super.okPressed();
	}
	
	public String getStringValue() {
		return dirEditor.getStringValue();
	}
	
	@Override
	protected Control createContents(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		dirEditor = new DirectoryFieldEditor("exportFile", "Directory", panel);
		return super.createContents(parent);
	}

}
