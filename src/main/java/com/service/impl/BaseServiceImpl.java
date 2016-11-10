package com.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.BaseDao;
import com.domain.PageResults;
import com.service.BaseService;


public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public void save(T entity) {
		baseDao.save(entity);
		
	}

	@Override
	public void saveOrUpdate(T entity) {
		baseDao.saveOrUpdate(entity);
		
	}

	@Override
	public T load(Serializable id) {
		return (T) baseDao.load(id);
	}

	@Override
	public T get(Serializable id) {
		return (T) baseDao.get(id);
	}



	@Override
	public void delete(T t) {
		baseDao.delete(t);
	}

	@Override
	public boolean delete(Serializable id) {
		return baseDao.delete(id);
	}


	@Override
	public void deleteAll(Collection<T> entities) {
		baseDao.deleteAll(entities);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public Long countByHql(String hql, Object... values) {
		return baseDao.countByHql(hql, values);
	}


	@Override
	public T getByHQL(String hql, Object... values) {
		return (T) baseDao.getByHQL(hql, values);
	}

	@Override
	public PageResults<T> findPageByHql(String hql, String countHql, int pageNo, int pageSize, List<Object> list) {
		return baseDao.findPageByHql(hql, countHql, pageNo, pageSize, list);
	}

	@Override
	public List<T> getListByHQL(String hql, Object[] values) {
		return baseDao.getListByHQL(hql, values);
	}

	@Override
	public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object[] values) {
		return baseDao.findPageByFetchedHql(hql, countHql, pageNo, pageSize, values);
	}



}
