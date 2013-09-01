package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.internal.ObjectPluginAction;

@SuppressWarnings("restriction")
public class SpringXmlUtils {
	
	public static void addNamespace(Object action, String name) {
		ObjectPluginAction e = (ObjectPluginAction) action;
		StructuredSelection select = (StructuredSelection) e.getSelection();
		try {
			IFile file = PluginUtils.getFile(select.getFirstElement());
			if (file != null && file.getFileExtension().equals("xml")) {
				InputStream in = file.getContents();
				Document doc = SpringBeanConfigUtils.addNamespace(in, name);
				file.setContents(new ByteArrayInputStream(doc.asXML().getBytes()), true, true, null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void addBean(Object action, String name) {
		addBean(action, name, true, new String[]{});
	}
	
	public static void addBean(Object action, String name, boolean check) {
		addBean(action, name, check, new String[]{});
	}
	
	public static void addBean(Object action, String name, String...beans) {
		addBean(action, name, true, beans);
	}
	
	public static void addBean(Object action, String name, boolean check, String...beans) {
		ObjectPluginAction e = (ObjectPluginAction) action;
		StructuredSelection select = (StructuredSelection) e.getSelection();
		try {
			IFile file = PluginUtils.getFile(select.getFirstElement());
			if (file != null && file.getFileExtension().equals("xml")) {
				InputStream in = file.getContents();
				Document doc = SpringBeanConfigUtils.addBean(in, name, check, beans);
				file.setContents(new ByteArrayInputStream(doc.asXML().getBytes()), true, true, null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void addBean(Object action, Element element) {
		addBean(action, element, true);
	}
	
	public static void addBean(Object action, Element element, boolean check) {
		addBean(action, element, check, new String[]{});
	}
	
	public static void addBean(Object action, Element element, boolean check, String...beans) {
		ObjectPluginAction e = (ObjectPluginAction) action;
		StructuredSelection select = (StructuredSelection) e.getSelection();
		try {
			IFile file = PluginUtils.getFile(select.getFirstElement());
			if (file != null && file.getFileExtension().equals("xml")) {
				InputStream in = file.getContents();
				Document doc = SpringBeanConfigUtils.addBean(in, element, check, beans);
				file.setContents(new ByteArrayInputStream(doc.asXML().getBytes()), true, true, null);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void addBean(Object action, Element element, String...beans) {
		addBean(action, element, true, beans);
	}
	
}
