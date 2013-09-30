package ${target};

<#assign sClass=domain?uncap_first>
<#assign service=domain + "Service">
<#assign sService=service?uncap_first>

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvdao.commons.aliyun.AliyunService;
import com.lvdao.commons.web.struts2.BaseAction;
import com.lvdao.webservice.commons.client.PageWrapper;
import com.lvdao.webservice.model.product.${domain};
import com.lvdao.webservice.service.product.${service};

/**
 * 酒店管理(后台)
 * @author john	
 * @version 1.0 ${.now?string('yyyy-MM-dd hh:mm:ss')}
 */
@Results({@Result(name="view",location="/WEB-INF/pages/admin/${sClass}/view.ftl"), @Result(name="notFound", location="/WEB-INF/pages/admin/${sClass}/notFound.ftl")})
@Namespace("/admin/${sClass}")
public class ${class} extends BaseAction {

	private int id;
	private ${domain} model;
	private PageWrapper<${domain}> pageWrapper;
	private int page;
	private int size;
	private String status;
	private String distCode;
	private int errorCode;		// 错误代码，0正常，1验证错误，2服务端错误
	
	@Autowired(required = false)
	${service} ${sService};
	
	@Autowired
	AliyunService aliyunService;

	@Action("index")
	public String index() {
		// TODO
		size = size == 0 ? 10 : size;
		PageWrapper<${domain}> wrapper = new PageWrapper<${domain}>(page, size);
		pageWrapper = ${sService}.search${domain}(wrapper);
		return SUCCESS;
	}
	
	@Action("form")
	public String createForm() {
		// TODO
		return "view";
	}

	@Action(value = "create")
	public String create() {
		// TODO
		if (!createValidate()) {
			errorCode = 1;
		} else {
			model = ${sService}.add${domain}(model);
		}
		System.out.println(model);
		return SUCCESS;
	}
	
	boolean createValidate() {
		// TODO
		return true;
	}

	@Action("update")
	public String update() {
		// TODO
		if (!updateValidate()) {
			errorCode = 1;
		} else if (${sService}.update${domain}(model)) {
			errorCode = 2;
		};
		System.out.println(model);
		return SUCCESS;
	}
	
	boolean updateValidate() {
		// TODO
		return true;
	}

	@Action("view")
	public String view() {
		model = ${sService}.get${domain}(id, distCode);
		return SUCCESS;
	}
	
	@Action("staticize")
	public String staticize() {
		return SUCCESS;
	}

	@Action("downup")
	public String downup() {
		if (!downupValidate()) {
			errorCode = 1;
		}
		${sService}.set${domain}Valid(id, distCode, status);
		return SUCCESS;
	}
	
	boolean downupValidate() {
		// TODO
		return true;
	}
	

	// getter setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ${domain} getModel() {
		return model;
	}

	public void setModel(${domain} ${sClass}) {
		this.model = ${sClass};
	}

	public PageWrapper<${domain}> getPageWrapper() {
		return pageWrapper;
	}

	public void setPageWrapper(PageWrapper<${domain}> pageWrapper) {
		this.pageWrapper = pageWrapper;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}

