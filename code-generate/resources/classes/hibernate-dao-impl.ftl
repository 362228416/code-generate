package ${target};

import java.io.Serializable;
import java.util.Collection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ${domainPkg}.${domain};

public class ${class} implements ${domain}DAO{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	public void save(${domain} ${domain?uncap_first}) {
		hibernateTemplate.save(${domain?uncap_first});
	}
	
	public void delete(${domain} ${domain?uncap_first}) {
		hibernateTemplate.delete(${domain?uncap_first});
	}
	
	public void update(${domain} ${domain?uncap_first}) {
		hibernateTemplate.update(${domain?uncap_first});
	}
	
	public Blog find(Serializable id) {
		final Serializable key = id;
		return hibernateTemplate.execute(new HibernateCallback<${domain}>() {
			public ${domain} doInHibernate(Session session)
					throws HibernateException, SQLException {
				return (${domain}) session.createQuery("FROM ${domain} where id = ?").setParameter(0, key).uniqueResult();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public Collection<${domain}> findAll() {
		return hibernateTemplate.find("From ${domain}");
	}
	
	@SuppressWarnings("unchecked")
	public Collection<${domain}> find(int firstResult, int maxResults) {
		return hibernateTemplate.findByCriteria(DetachedCriteria.forClass(${domain}.class), firstResult, maxResults);
	}
	
	public long count() {
		return hibernateTemplate.execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				return (Long) session.createQuery("SELECT COUNT(o) FROM ${domain} o").uniqueResult();
			}
		});
	}
	
}