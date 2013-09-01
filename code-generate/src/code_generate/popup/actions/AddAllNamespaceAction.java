package code_generate.popup.actions;

import java.util.Set;

import org.eclipse.jface.action.IAction;

import utils.SpringBeanConfigUtils;
import utils.SpringXmlUtils;

public class AddAllNamespaceAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		Set<Object> set = SpringBeanConfigUtils.np.keySet();
		for (Object key : set) {
			SpringXmlUtils.addNamespace(action, key.toString());
		}
	}

}
