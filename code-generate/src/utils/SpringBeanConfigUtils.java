package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

@SuppressWarnings("rawtypes")
public class SpringBeanConfigUtils {
	
	/** uri location properties */
	public static final Properties lp = new Properties();
	
	/** namespace properties */
	public static final Properties np = new Properties();
	
	/** bean element */
	public static Map<String, Element> beans = new HashMap<String, Element>();
	
	private static DocumentFactory documentFacrory = DocumentFactory.getInstance();
	
	static {
		InputStream in1 = null;
		InputStream in2 = null;
		try {
			// 加载命名空间资源
			in1 = getResource("location.properties");
			in2 = getResource("namespaces.properties");
			lp.load(in1);
			np.load(in2);
			
			// 加载Bean元素
			Document doc = parse(getResource("tags.xml"));
			Element root = doc.getRootElement();
			List list = root.elements();
			for (Object o : list) {
				Element e = (Element) o;
				Element bean = parseBeanElement(e);
				String id = bean.attributeValue("id");
				if (id == null) {
					id = bean.getName();
				}
				beans.put(id, bean);
			}
			doc = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in1.close();
				in2.close();
			} catch (Exception e2) {
			}
		}
	}
	
	/**
	 * 将Tag元素解析成spring Bean 元素
	 * @param element
	 * @return
	 */
	public static Element parseBeanElement(Element element) {
		String elementName = element.attributeValue("name");
		Element result = documentFacrory.createElement(elementName);
		
		String text = element.getTextTrim();
		if (!"".equals(text)) {
			result.setText(text);
		}
		Element attributes = element.element("attributes");
		if (attributes != null) {
			List attrs = attributes.elements();
			for (Object attr : attrs) {
				Element e2 = (Element) attr;
				result.addAttribute(e2.getName(), e2.getText());
			}
		}
		Element childs = element.element("childs");
		if (childs != null) {
			List cs = childs.elements();
			for (Object child : cs) {
				Element e3 = (Element) child;
				Element child1 = parseBeanElement(e3);
				result.add(child1);
			}
		}
		return result;
	}
	
	/**
	 * 将输入流转化成Document，并加入指定的Bean
	 * @param in
	 * @param beanName
	 * @return
	 * @throws Exception
	 */
	public static Document addBean(InputStream in, String beanName) throws Exception {
		return addBean(in, beans.get(beanName));
	}
	
	public static Document addBean(InputStream in, String beanName, boolean check, String... beans) throws Exception {
		return addBean(in, SpringBeanConfigUtils.beans.get(beanName), check, beans);
	}
	
	/**
	 * 将输入流转化成Document，并加入指定的Bean元素
	 * @param in
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public static Document addBean(InputStream in, Element element) throws Exception {
		return addBean(in, element, true);
	}
	
	/**
	 * 将输入流转化成Document，并加入指定的Bean元素
	 * @param in
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public static Document addBean(InputStream in, Element element, boolean check) throws Exception {
		Document doc = parse(in);
		Element root = doc.getRootElement();
		if (check) {
			String name = element.getName();
			Namespace beansNamespace = root.getNamespaceForPrefix("beans");
			if (beansNamespace == null) {
				addNamespace(doc, "beans");
			}
			if (!"bean".equals(name)) {
				String prefix = name.split(":")[0];
				root = doc.getRootElement();
				Namespace prefix2 = root.getNamespaceForPrefix(prefix);
				if (prefix2 == null) {
					addNamespace(doc, prefix);
				}
			}
		}
		root.add(element.createCopy());
		return doc;
	}
	
	public static Document addBean(InputStream in, Element element, boolean check, String...beans) throws Exception {
		Document doc = parse(in);
		Element root = doc.getRootElement();
		if (check) {
			String name = element.getName();
			Namespace beansNamespace = root.getNamespaceForPrefix("beans");
			if (beansNamespace == null) {
				addNamespace(doc, "beans");
			}
			if (!"bean".equals(name)) {
				String prefix = name.split(":")[0];
				root = doc.getRootElement();
				Namespace prefix2 = root.getNamespaceForPrefix(prefix);
				if (prefix2 == null) {
					addNamespace(doc, prefix);
				}
			}
		}
		for (String bean : beans) {
			List elements = root.elements();
			boolean has = false;
			for (Object obj : elements) {
				Element e = (Element) obj;
				if (bean.equals(e.getName()) || bean.equals(e.attributeValue("id"))) {
					has = true;
					break;
				}
			}
			if (!has) {
				root.add(SpringBeanConfigUtils.beans.get(bean).createCopy());
			}
		}
		root.add(element.createCopy());
		return doc;
	}
	
	
	
	/**
	 * 将输入流转化成Document，并加入指定的xml命名空间
	 * @param in
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Document addNamespace(InputStream in, String name) throws Exception  {
		return addNamespace(parse(in), name);
	}
	
	/**
	 * 获取类路径下的资源，通过本类的ClassLoader
	 * @param resource
	 * @return
	 */
	public static InputStream getResource(String resource) {
		return SpringBeanConfigUtils.class.getResourceAsStream(resource);
	}
	
	/**
	 * 加入指定的xml命名空间
	 * @param doc
	 * @param name
	 * @return
	 */
	public static Document addNamespace(Document doc, String name) {
		Element root = getRootElement(doc);;
		Attribute attr = getXsiAttribute(root);
		root.addNamespace(name, np.getProperty(name));
		if (!attr.getText().contains(lp.getProperty(name))) {
			attr.setValue(attr.getValue() + lp.getProperty(name));
		}
		return doc;
	}
	
	/**
	 * 获取xsi命名空间，如果不存在则创建一个
	 * @param root
	 * @return
	 */
	private static Attribute getXsiAttribute(Element root) {
		Namespace ns = root.getNamespaceForPrefix("xsi");
		if (ns == null) {
			root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		}
		Attribute attr = root.attribute("schemaLocation");
		if (attr == null) {
			String schema = "xsi:schemaLocation";
			root.addAttribute(schema, "");
			attr = root.attribute(schema);
		}
		return attr;
	}
	
	/**
	 * 获取根元素，如果跟元素不是beans，则把其改成beans
	 * @param doc
	 * @return
	 */
	private static Element getRootElement(Document doc) {
		Element root = doc.getRootElement();
		if (root.getName() != "beans") {
			root.setName("beans");
		}
		return root;
	}
	
	/**
	 * 将输入流转成Document
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static Document parse(InputStream in) {
		SAXReader reader = new SAXReader();
		try {
			return reader.read(in);
		} catch (DocumentException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 将Element文本转成Element
	 * @param elementText
	 * @return
	 * @throws Exception
	 */
	public static Element parse(String elementText) throws Exception {
		StringBuffer declare = new StringBuffer("<?xml version=\"1.0\"?>");
		declare.append("<beans xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:beans=\"http://www.springframework.org/schema/beans\" xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd \">");
		declare.append(elementText);
		declare.append("</beans>");
		Document doc = parse(new ByteArrayInputStream(declare.toString().getBytes()));
		Element e = (Element) doc.getRootElement().elements().get(0);
		doc.getRootElement().remove(e);
		doc = null;
		declare = null;
		return e;
	}
}
