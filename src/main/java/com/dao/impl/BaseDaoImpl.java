package com.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;
import com.domain.PageResults;

@SuppressWarnings("unchecked")
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> cls;

	public BaseDaoImpl() {
		if (cls == null) {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			cls = (Class<T>) type.getActualTypeArguments()[0];
		}
	}

	public BaseDaoImpl(Class<T> classEntity) {
		this.cls = classEntity;
	}

	public Class<T> getEntity() {
		return cls;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 到时候测试是否是当前session
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}
	
	

	@Override
	public T load(Serializable id) {
		Object loadObj = this.getSession().load(this.cls, id);
		return (T) loadObj;
	}

	@Override
	public T get(Serializable id) {
		Object loadObj = this.getSession().get(this.cls, id);
		return (T) loadObj;
	}

	public T getFirst(String hql) {
		Query query = this.getSession().createQuery(hql);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}


	@Override
	public void delete(T t) {
		this.getSession().delete(t);
	}

	@Override
	public boolean delete(Serializable id) {
		T t = get(id);
		if (t == null) {
			return false;
		}
		delete(t);
		return true;
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		for (Object entity : entities) {
			this.getSession().delete(entity);
		}
	}


	@Override
	public void update(T entity) {
		this.getSession().update(entity);

	}

	@Override
	public Long countByHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public T getByHQL(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	
	@Override
	public List<T> getListByHQL(String hqlString, Object  ... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}
	
	public List<String> getListByHQLRetStr(String hqlString, Object... values){
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}
	
	public List<T> getListByHQLFirstN(String hqlString, int begin, int totalNum, Object... values){
		Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.setFirstResult(begin); //开始记录
        query.setMaxResults(totalNum);  //查询出来的记录数
        return query.list(); 
	}

	/**
	 * HQL 分页查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param countHql
	 *            查询记录条数的HQL语句
	 * @param pageNo
	 *            下一页
	 * @param 一页总条数
	 * @param values
	 *            不定Object数组参数
	 * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
	 */
	@Override
	public PageResults<T> findPageByFetchedHql(String hql, String countHql,
			int pageNo, int pageSize, Object... values) {
		PageResults<T> retValue = new PageResults<T>();
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int currentPage = pageNo > 1 ? pageNo : 1;
		retValue.setCurrentPage(currentPage);
		retValue.setPageSize(pageSize);
		if (countHql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
		} else {
			Long count = countByHql(countHql, values);
			retValue.setTotalCount(count.intValue());
		}
		retValue.resetPageNo();
		List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		retValue.setResults(itemList);

		return retValue;
	}
	
	@Override
	public PageResults<T> findPageByHql(String hql, String countHql,
			int pageNo, int pageSize, List<Object> list) {
		PageResults<T> retValue = new PageResults<T>();
		Query query = this.getSession().createQuery(hql);
		if (list != null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		int currentPage = pageNo > 1 ? pageNo : 1;
		retValue.setCurrentPage(currentPage);
		retValue.setPageSize(pageSize);
		if (countHql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
		} else {
			Query countQuery = this.getSession().createQuery(countHql);
			if (list != null && list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					countQuery.setParameter(i, list.get(i));
				}
			}
			Long count = (Long)countQuery.uniqueResult();
			retValue.setTotalCount(count.intValue());
		}
		retValue.resetPageNo();
		List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		retValue.setResults(itemList);

		return retValue;
	}
}
