package ${target};

import java.util.Collection;

import ${fullName};

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("rest-default")
public class ${class} extends ActionSupport implements ModelDriven<Object>{

	private long id;
	private Collection<${domain}> list;
	private ${domain} model;
	
	public HttpHeaders index() {
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}
	
	public Object getModel() {
		return list != null ? list : model;
	}

	public void setId(long id) {
		this.id = id;
	}
}
