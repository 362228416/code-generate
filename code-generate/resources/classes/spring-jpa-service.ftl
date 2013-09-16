package ${target};

import java.io.Serializable;
import java.util.Collection;

import ${domainPkg}.${domain};

public interface ${class} {
	
	public void save${domain}(${domain} ${domain?uncap_first}); 
	
	public void delete${domain}(${domain} ${domain?uncap_first});
	
	public void update${domain}(${domain} ${domain?uncap_first});
	
	public ${domain} find${domain}(Serializable id);
	
	public Collection<${domain}> findAll${domain}s();
	
	public Collection<${domain}> find${domain}s(int firstResult, int maxResults);
	
}