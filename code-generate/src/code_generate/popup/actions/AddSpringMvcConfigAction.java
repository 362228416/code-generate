package code_generate.popup.actions;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.jface.action.IAction;

import utils.SpringBeanConfigUtils;
import utils.SpringXmlUtils;

public class AddSpringMvcConfigAction extends IObjectActionDelegateAdapter {

	@SuppressWarnings("rawtypes")
	@Override
	public void run(IAction action) {
		InputStream in = SpringBeanConfigUtils.getResource("web.xml");
		Document doc = SpringBeanConfigUtils.parse(in);
		Element root = doc.getRootElement();
		List list = root.elements();
		for (Object obj : list) {
			Element element = (Element) obj;
			SpringXmlUtils.addBean(action, element, false);
		}
		
	}


}
