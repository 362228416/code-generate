package ${target};

import ${fullName};
import java.util.List;

public interface ${class} {

	public void save(${domain} ${simpleName});
	
	public void delete(${domain} ${domain?uncap_first});
	
	public void update(${domain} ${domain?uncap_first});
	
	public ${domain} find(long id);
	
	public List<${domain}> findAll();
	
	public List<${domain}> find${domain}s(int first, int max);
	
}