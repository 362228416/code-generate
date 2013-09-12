package com.ydpp.eclipse.preferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import com.ydpp.eclipse.CodeGeneratePlugin;


/**
 * Class used to initialize default preference values.
 * @author John
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 加载模板资源保存至Eclipse Store 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	void init() throws Exception {
		// old version template
		setDefault("template", "repository;repositoryImpl;service;serviceImpl;controller;toJson;");
		setDefault("textArea", "");
		loadResourceAndStore("controller");
		loadResourceAndStore("repository");
		loadResourceAndStore("repositoryImpl");
		loadResourceAndStore("service");
		loadResourceAndStore("serviceImpl");
//		loadResourceAndStore("toJson", "methods/toJson.vm");
		
		// load config
		Properties prop = new Properties();
		prop.load(getResourceAsStream("resources/config.properties"));
		Set<Object> keySet = prop.keySet();
		for (Object key : keySet) {
			setDefault(key.toString(), prop.get(key).toString());
		}
		
		// load template
		/* 弃用，改成自动扫描所有文件
		loadResourceAndStoreFromClasses("hibernate-dao");
		loadResourceAndStoreFromClasses("hibernate-dao-impl");
		loadResourceAndStoreFromClasses("jpa-dao");
		loadResourceAndStoreFromClasses("jpa-dao-impl");
		loadResourceAndStoreFromClasses("junit-test");
		loadResourceAndStoreFromClasses("myibatis-dao");
		loadResourceAndStoreFromClasses("spring-dao-test");
		loadResourceAndStoreFromClasses("spring-service");
		loadResourceAndStoreFromClasses("spring-service-impl");
		loadResourceAndStoreFromClasses("spring-service-test");
		loadResourceAndStoreFromClasses("springmvc-controller");
		loadResourceAndStoreFromClasses("struts-action");
		loadResourceAndStoreFromClasses("struts-action-test");
		loadResourceAndStoreFromClasses("struts-rest");
		
		loadResourceAndStoreFromPages("extjs-controller");
		loadResourceAndStoreFromPages("extjs-create");
		loadResourceAndStoreFromPages("extjs-layout");
		loadResourceAndStoreFromPages("extjs-list");
		loadResourceAndStoreFromPages("extjs-menu");
		loadResourceAndStoreFromPages("extjs-model");
		loadResourceAndStoreFromPages("extjs-show");
		loadResourceAndStoreFromPages("extjs-store");
		loadResourceAndStoreFromPages("extjs-update");
		
		loadResourceAndStoreFromMethods("toJson");
		loadResourceAndStoreFromMethods("springmvc-json");
		loadResourceAndStoreFromMethods("springmvc-rest");
		*/
		
		// 加载模板文件
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
	
	
	// 加载资源，并保存至store
	void loadResourceAndStoreFromClasses(String name) {
		loadResourceAndStore(name, "classes/" + name + ".vm");
	}
	
	void loadResourceAndStoreFromPages(String name) {
		loadResourceAndStore(name, "pages/" + name + ".vm");
	}
	
	void loadResourceAndStoreFromMethods(String name) {
		loadResourceAndStore(name, "methods/" + name + ".vm");
	}
	
	void loadResourceAndStoreFromXmls(String name) {
		loadResourceAndStore(name, "xmls/" + name + ".vm");
	}
	
	void loadResourceAndStore(String name) {
		loadResourceAndStore(name, name + ".vm");
	}
	
	void loadResourceAndStore(String name, String resource) {
		setDefault(name, getContent("resources/" + resource));
	}
	
	void setDefault(String name, String value) {
		STORE_KEYS.add(name);
		CodeGeneratePlugin.getDefault().getPreferenceStore().setDefault(name, value);
	}
	
	public static Set<String> STORE_KEYS = new HashSet<String>();
	
	String getContent(String template) {
		InputStream in = getResourceAsStream(template);
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
			System.out.println(template + " not found!");
			e.printStackTrace();
		}
		return content;
	}
	
	InputStream getResourceAsStream(String resource) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
	}

	
	
	
	
}
