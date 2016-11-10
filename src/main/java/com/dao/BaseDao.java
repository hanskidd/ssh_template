package com.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.domain.PageResults;

/**
 * BaseDaoSupport
 * @author jie.lin
 * @description : 数据层泛型基类接口
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 保存实体类
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 保存或更新实体类
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 根据Id加载实体类的load方法
	 * 
	 * @param id
	 * @return 查出的实体类
	 */
	public T load(Serializable id);

	/**
	 * 根据Id获取实体类的get方法
	 * 
	 * @param id
	 * @return 查出的实体类
	 */
	public T get(Serializable id);


	/**
	 * 删除表中的实体类t
	 * 
	 * @param t  实体	
	 */
	public void delete(T t);

	/**
	 * 根据id删除实体类
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Serializable id);

    
	/**
	 * 删除集合中所有实体类
	 * 
	 * @param entities
	 *            实体的Collection集合
	 */
	public void deleteAll(Collection<T> entities);


	/**
	 * update
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 根据HQL得到记录数
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 记录总数
	 */
	public Long countByHql(String hql, Object... values);

	/**
	 * 根据HQL语句查找唯一实体
	 * 
	 * @param hqlString
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 查询实体
	 */
	public T getByHQL(String hqlString, Object... values);


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
	 * @param list
	 *            HQL查询条件--arrayList
	 * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
	 */
	public PageResults<T> findPageByHql(String hql, String countHql, int pageNo, int pageSize, List<Object> list);

	/**
	  * getListByHQL 方法
	  * <p>方法说明:</p>
	  * @param hqlString
	  * @param values
	  * @return
	  * @return List<T>
	*/
	List<T> getListByHQL(String hqlString, Object ... values);
	/**
	  * findPageByFetchedHql 方法
	  * <p>方法说明:</p>
	  * @param hql
	  * @param countHql
	  * @param pageNo
	  * @param pageSize
	  * @param values
	  * @return
	  * @return PageResults<T>
	*/
	PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object ... values);

}
