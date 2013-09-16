package ${target};

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ${domainPkg}.${domain};

public class ${class} implements ${domain}Repository{

	@PersistenceContext
	EntityManager entityManager;
	
	public void save(${domain} ${domain?uncap_first}) {
		entityManager.persist(${domain?uncap_first});
	}
	
	public void delete(${domain} ${domain?uncap_first}) {
		entityManager.remove(${domain?uncap_first});
	}
	
	public void update(${domain} ${domain?uncap_first}) {
		entityManager.merge(${domain?uncap_first});
	}
	
	public ${domain} find(Serializable id) {
		return entityManager.find(${domain}.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<${domain}> findAll() {
		return entityManager.createQuery("FROM ${domain}").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<${domain}> find(int firstResult, int maxResults) {
		return entityManager.createQuery("FROM ${domain}").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
	
	public long count() {
		return (Long) entityManager.createQuery("SELECT COUNT(*) FROM ${domain} o").getSingleResult();
	}
	
}