package ${target};

import ${fullName};


import java.util.List;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ${class}RepositoryImpl implements ${class}Repository {
	
	@PersistenceContext
    transient EntityManager entityManager;
	
	@Transactional
	public void save(${class} ${simpleName}) {
		entityManager.persist(${simpleName});
	}
	
	@Transactional
	public void delete(${class} ${simpleName}) {
        entityManager.remove(${simpleName});
	}
	
	@Transactional
	public void delete(Serializable id) {
		${class} ${simpleName} = find(id);
		if (${simpleName} != null) {
			delete(${simpleName});
		}
	}
	
	@Transactional
	public void update(${class} ${simpleName}) {
		entityManager.merge(${simpleName});
	}
	
	public ${class} find(Serializable id) {
		return entityManager.find(${class}.class, id);
	}
	
	public List<${class}> findAll() {
		return entityManager.createQuery("SELECT o FROM ${class} o", ${class}.class).getResultList();
	}
	
	public List<${class}> find${class}s(int firstResult, int maxResult) {
		return entityManager.createQuery("SELECT o FROM ${class} o", ${class}.class).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
	}
	
	public List<${class}> find${class}sForPage(int page, int size) {
		return null;
	}
	
	public List<${class}> find${class}sForPage(int page) {
		return null;
	}
	
	public long count() {
		return entityManager.createQuery("SELECT COUNT(o) FROM ${class} o", Long.class).getSingleResult();
	}
}