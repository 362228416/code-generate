package ${target};

import java.io.Serializable;
import java.util.Collection;

import ${domainPkg}.${domain};
import ${daoPkg}.${dao};

public class ${class} implements ${domain}Service{
	
	${dao} ${dao?uncap_first};
	
	public void save${domain}(${domain} ${domain?uncap_first}) {
		${dao?uncap_first}.save(${domain?uncap_first});
	}
	
	public void delete${domain}(${domain} ${domain?uncap_first}) {
		${dao?uncap_first}.delete(${domain?uncap_first});
	}
	
	public void update${domain}(${domain} ${domain?uncap_first}) {
		${dao?uncap_first}.update(${domain?uncap_first});
	}
	
	public ${domain} find${domain}(Serializable id) {
		return ${dao?uncap_first}.find(id);
	}
	
	public Collection<${domain}> findAll${domain}s() {
		return ${dao?uncap_first}.findAll();
	}
	
	public Collection<${domain}> find${domain}s(int firstResult, int maxResults) {
		return ${dao?uncap_first}.find(firstResult, maxResults);
	}
	
}