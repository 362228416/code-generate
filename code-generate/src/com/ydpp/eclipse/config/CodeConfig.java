package com.ydpp.eclipse.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ydpp.eclipse.CodeGeneratePlugin;
import com.ydpp.util.ResourceUtil;

/**
 * 全局配置
 * @author john
 */
public class CodeConfig {

	public static final IPreferenceStore store = CodeGeneratePlugin.getDefault().getPreferenceStore();
	
	public static Set<String> STORE_KEYS = new HashSet<String>();
	
	static boolean isDebug = true;
	
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getString(String key) {
		if (isDebug) {
			String value = store.getString(key);
			return value.contains("resources") ? getContent(value) : value;
		}
		return store.getString(key);
	}
	
	public static String getDefaultString(String key) {
		return store.getDefaultString(key);
	}
	
	/**
	 * return formated text
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getMessage(String key, Object... args) {
		String value = getString(key);
		if (value == null || "".equals(value)) {
			return MessageFormat.format(key, args);
		}
		return MessageFormat.format(value, args);
	}
	
	/**
	 * load template resource and save to Eclipse Store 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	static void init() throws Exception {
		// old version template
		setDefault("template", "repository;repositoryImpl;service;serviceImpl;controller;toJson;");
		setDefault("textArea", "");
		loadResourceAndStore("controller");
		loadResourceAndStore("repository");
		loadResourceAndStore("repositoryImpl");
		loadResourceAndStore("service");
		loadResourceAndStore("serviceImpl");
		
		// load config
		Properties prop = new Properties();
		prop.load(ResourceUtil.getResourceAsStream("resources/config.properties"));
		Set<Object> keySet = prop.keySet();
		for (Object key : keySet) {
			setDefault(key.toString(), prop.get(key).toString());
		}
		
		// load template file
		String templateFile = getContent("resources/templates.xml");
		Document tDoc = DocumentHelper.parseText(templateFile);
		Element root = tDoc.getRootElement();
		List<Element> elements = root.elements("class");
		for (Element e : elements) {
			loadResourceAndStoreFromClasses(e.getText());
		}
		elements = root.elements("method");
		for (Element e : elements) {
			loadResourceAndStoreFromMethods(e.getText());
		}
		elements = root.elements("page");
		for (Element e : elements) {
			loadResourceAndStoreFromPages(e.getText());
		}
		elements = root.elements("xml");
		for (Element e : elements) {
			loadResourceAndStoreFromXmls(e.getText());
		}
		
		
	}
	
	
	// load resource and save to store
	static void loadResourceAndStoreFromClasses(String name) {
		loadResourceAndStore(name, "classes/" + name + ".ftl");
	}
	
	static void loadResourceAndStoreFromPages(String name) {
		loadResourceAndStore(name, "pages/" + name + ".ftl");
	}
	
	static void loadResourceAndStoreFromMethods(String name) {
		loadResourceAndStore(name, "methods/" + name + ".ftl");
	}
	
	static void loadResourceAndStoreFromXmls(String name) {
		loadResourceAndStore(name, "xmls/" + name + ".ftl");
	}
	
	static void loadResourceAndStore(String name) {
		loadResourceAndStore(name, name + ".ftl");
	}
	
	static void loadResourceAndStore(String name, String resource) {
		if (isDebug) {
			setDefault(name, "resources/" + resource);
			return;
		}
		setDefault(name, getContent("resources/" + resource));
	}
	
	static void setDefault(String name, String value) {
		STORE_KEYS.add(name);
		CodeGeneratePlugin.getDefault().getPreferenceStore().setDefault(name, value);
	}
	
	static String getContent(String template) {
		InputStream in = ResourceUtil.getResourceAsStream(template);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String content = null;
		try {
			int b = 0;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			content = out.toString();
			if (in != null) {
				in.close(); 
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
}
