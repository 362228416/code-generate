package com.ydpp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;

/**
 * 代码生成历史管理
 * @author john
 */
public class CodeHistoryManager {

//	static Queue<History> histories = new LinkedList<History>();
//
//	public static void addHistory(String project, String domain, String packageName, String className, CodeType type) {
//		if (histories.size() == 1000) {
//			histories.remove();
//		}
//		History history = new History();
//		history.project = project;
//		history.dao = packageName;
//		history.domain = domain;
//		history.type = type;
//		histories.add(history);
//		
//		
//		
//		
//		
//	}
	
	
//	public void addHistory(IAction action, String domain, String dao, String service, String domainPkg, String daoPkg, String servicePkg) throws Exception {
//		Document doc = getHistoryDocument(action);
//		Element history = doc.getRootElement().addElement("history");
//		history.addAttribute("id", domain);
//		history.addElement("domain").setText(domain);
//		history.addElement("dao").setText(dao == null ? "" : dao);
//		history.addElement("service").setText(service == null ? "" : service);
//		history.addElement("domainPkg").setText(domainPkg == null ? "" : domainPkg);
//		history.addElement("daoPkg").setText(daoPkg == null ? "" : daoPkg);
//		history.addElement("servicePkg").setText(servicePkg == null ? "" : servicePkg);
//	}
//
//	private static Document getHistoryDocument(IAction action) throws Exception {
//		Document doc = null;
//		IFile file = PluginUtils.createFile(PluginUtils.getProject(action), ".code.xml");
//		
//		try {
//			InputStream in = file.getContents();
//			doc = DocumentHelper.parseText(IOUtils.toString(in));
//			in.close();
//			
//			
//			file.setContents(new ByteArrayInputStream(doc.asXML().getBytes()), true, true, null);
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//		
//		return doc;
//	}
	
	
	public static void putHistory(IAction action, String domain, String dao, String service, String domainPkg, String daoPkg, String servicePkg) throws Exception {
		
		// load history file
		IFile file = PluginUtils.createFile(PluginUtils.getProject(action), ".histories");
//		InputStream in = file.getContents();
//		Document doc = DocumentHelper.parseText(IOUtils.toString(in));
//		in.close();
		Document doc = getHistoryFile(action);
		
		Element root = doc.getRootElement();
		
		// find domain 
		if (domain == null) {
			/*
			List<Element> list = root.elements("history");
			for (Element e : list) {
				if (e.elementText("dao").equals(dao) || e.elementText("service").equals(service)) {
					domain = e.attributeValue("id");
					break;
				}
			}
			*/
			
			List<Element> list = root.elements("history");
			for (Element e : list) {
				String keys = e.attributeValue("keys");
				if (domain != null && keys.contains(domain) || dao != null && keys.contains(dao) || service != null && keys.contains(service)) {
					domain = e.attributeValue("id");
					break;
				}
				
			}
			
		}
		
		// update elements
		List<Element> list = root.elements("history");
		boolean exists = false;
		for (Element e : list) {
			if (e.attributeValue("id").equals(domain)) {
				Element eDao = e.element("dao");
				eDao.setText(dao == null ? eDao.getText() : dao);
				Element eService = e.element("service");
				eService.setText(service == null ? eService.getText() : service);
				Element eDop = e.element("domainPkg");
				eDop.setText(domainPkg == null ? eDop.getText() : domainPkg);
				Element eDaoP = e.element("daoPkg");
				eDaoP.setText(daoPkg == null ? eDaoP.getText() : daoPkg);
				Element eSp = e.element("servicePkg");
				eSp.setText(servicePkg == null ? eSp.getText() : servicePkg);
				
				String keys = e.attributeValue("keys");
				if (dao != null && !keys.contains(dao)) {
					keys += "," + dao;
				}
				if (service != null && !keys.contains(service)) {
					keys += "," + service;
				}
				e.attributeValue("keys", keys);
				
				exists = true;
				break;
			}
			
		}
		
		// add history
		if (!exists && domain != null) {
			Element history = root.addElement("history");
			history.addAttribute("id", domain);
			history.addElement("dao").setText(dao == null ? "" : dao);
			history.addElement("service").setText(service == null ? "" : service);
			history.addElement("domainPkg").setText(domainPkg == null ? "" : domainPkg);
			history.addElement("daoPkg").setText(daoPkg == null ? "" : daoPkg);
			history.addElement("servicePkg").setText(servicePkg == null ? "" : servicePkg);
			StringBuffer keys = new StringBuffer();
			keys.append(domain).append(dao == null ? "" : "," + dao).append(service == null ? "" : "," + service);
			history.addAttribute("keys", keys.toString());
		}
		
		
		// update histtory file
		file.setContents(new ByteArrayInputStream(doc.asXML().getBytes()), true, true, null);
	}
	
	
	public static History getHistry(IAction action, String domain, String dao, String service) throws Exception {
		History history = new History();
		
		// load history file
//		IFile file = PluginUtils.createFile(PluginUtils.getProject(action), ".histories");
//		InputStream in = file.getContents();
//		Document doc = DocumentHelper.parseText(IOUtils.toString(in));
//		in.close();
		
		Document doc = getHistoryFile(action);
		Element root = doc.getRootElement();
		
		// find domain 
		List<Element> list = root.elements("history");
		for (Element e : list) {
			String keys = e.attributeValue("keys");
//			if (keys.contains(domain) || keys.contains(dao) || keys.contains(service)) {
			if (domain != null && keys.contains(domain) || dao != null && keys.contains(dao) || service != null && keys.contains(service)) {
				history.domain = e.attributeValue("id");
				history.dao = e.elementText("dao");
				history.service = e.elementText("service");
				history.domainPkg = e.elementText("domainPkg");
				history.daoPkg = e.elementText("daoPkg");
				history.servicePkg = e.elementText("servicePkg");
				break;
			}
			/*
			if (e.attributeValue("id").equals(domain) || e.elementText("dao").equals(dao) || e.elementText("service").equals(service)) {
				history.domain = e.attributeValue("id");
				history.dao = e.elementText("dao");
				history.service = e.elementText("service");
				history.domainPkg = e.elementText("domainPkg");
				history.daoPkg = e.elementText("daoPkg");
				history.servicePkg = e.elementText("servicePkg");
				break;
			}
			*/
		}
		
		return history;
	}
	
	
	static Document getHistoryFile(IAction action) throws Exception {
		IFile file = PluginUtils.createFile(PluginUtils.getProject(action), ".histories");
		Document doc = null;
		
		InputStream in = file.getContents();
		String content = IOUtils.toString(in);
		if (content.length() == 0) {
			doc = DocumentHelper.createDocument();
			doc.addElement("histories");
		} else {
			doc = DocumentHelper.parseText(content);
		}
		in.close();
		return doc;
	}
	
	

}

class History {
	String project, domain, dao, service, domainPkg, daoPkg, servicePkg;
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDao() {
		return dao;
	}
	public void setDao(String dao) {
		this.dao = dao;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDomainPkg() {
		return domainPkg;
	}
	public void setDomainPkg(String domainPkg) {
		this.domainPkg = domainPkg;
	}
	public String getDaoPkg() {
		return daoPkg;
	}
	public void setDaoPkg(String daoPkg) {
		this.daoPkg = daoPkg;
	}
	public String getServicePkg() {
		return servicePkg;
	}
	public void setServicePkg(String servicePkg) {
		this.servicePkg = servicePkg;
	}
	
}