package ${target};

import java.io.Serializable;
import java.util.Collection;

import ${domainPkg}.${domain};

public interface ${class} {
	
	public void save(${domain} ${domain?uncap_first});
	
	public void delete(${domain} ${domain?uncap_first});
	
	public void update(${domain} ${domain?uncap_first});
	
	public ${domain} find(Serializable id);
	
	public Collection<${domain}> findAll();
	
	public Collection<${domain}> find(int firstResult, int maxResults);
	
	public long count();
	
}