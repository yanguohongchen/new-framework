package com.sea.dao.impl;

import java.util.List;

public interface IBaseDao<E>
{

	E getEntityById(String idName, Object id);

	void deleteById(String idName, Object id);

	E getEntityById(Object id);

	long saveEntity(E e);

	void updateEntityById(String idName, E e);

	int getTotal(String sql, Object[] param);

	List<E> getList(String sql, Object[] param);


}
