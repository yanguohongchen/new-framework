package com.sea.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

public class BaseDao<E> implements IBaseDao<E>
{

	protected final Log logger = LogFactory.getLog(getClass());

	private Class<E> entityClass;

	protected static Map<String, HashSet<String>> classFieldsList;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public BaseDao()
	{
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType)
		{
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<E>) p[0];
		}
	}

	@Override
	public E getEntityById(String idName, Object id)
	{
		HashSet<String> fields = this.returnFields(entityClass);
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		for (String field : fields)
		{
			sql.append(field);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" from ");
		sql.append(entityClass.getSimpleName());
		sql.append(" where ");
		sql.append(idName);
		sql.append("=?");
		logger.debug("当前执行sql为:" + sql);
		E entity = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<E>(entityClass), new Object[] { id });
		return entity;
	}

	@Override
	public void deleteById(String idName, Object id)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append(entityClass.getSimpleName());
		sql.append(" where ");
		sql.append(idName);
		sql.append("=?");
		jdbcTemplate.update(sql.toString(), new Object[] { id });
	}

	@Override
	public E getEntityById(Object id)
	{
		return this.getEntityById("id", id);
	}

	@Override
	public long saveEntity(E e)
	{
		Map<String, Object> fields = getRefectEntityInfo(e);
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(underscoreName(e.getClass().getSimpleName()));
		sql.append("(");
		StringBuffer param = new StringBuffer();
		Object[] valueArr = new Object[fields.keySet().size()];
		int i = 0;
		for (String field : fields.keySet())
		{
			sql.append(underscoreName(field));
			sql.append(",");
			param.append("?");
			param.append(",");
			valueArr[i] = fields.get(field);
			i++;
		}
		if (sql.length() > 0)
		{
			sql.deleteCharAt(sql.length() - 1);
		}
		if (param.length() > 0)
		{
			param.deleteCharAt(param.length() - 1);
		}
		sql.append(") values (");
		sql.append(param);
		sql.append(")");
		logger.debug("当前执行sql为:" + sql);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new SimplePreparedStatementCreator(sql.toString(), valueArr);
		jdbcTemplate.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void updateEntityById(String idName, E e)
	{
		Map<String, Object> fields = getRefectEntityInfo(e);
		// UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
		StringBuffer sql = new StringBuffer();
		sql.append(" update ");
		sql.append(underscoreName(e.getClass().getSimpleName()));
		sql.append(" set ");
		Object[] valueArr = new Object[fields.keySet().size() + 1];
		int i = 0;
		for (String field : fields.keySet())
		{
			sql.append(underscoreName(field));
			sql.append("=? and ");
			valueArr[i] = fields.get(field);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.deleteCharAt(sql.length() - 1);
		sql.deleteCharAt(sql.length() - 1);
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where ");
		sql.append(idName);
		sql.append("=?");
		try
		{
			Field fie = e.getClass().getDeclaredField(idName);
			fie.setAccessible(true);
			valueArr[fields.keySet().size()] = fie.get(e);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1)
		{
			throw new RuntimeException(e1);
		}
		logger.debug("当前执行sql为:" + sql);
		jdbcTemplate.update(sql.toString(), valueArr);
	}

	@Override
	public int getTotal(String sql, Object[] param)
	{
		Integer num = jdbcTemplate.queryForObject(sql, param, Integer.class);
		return num;
	}

	@Override
	public List<E> getList(String sql, Object[] param)
	{
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<E>(entityClass));
	}

	public Map<String, Object> getRefectEntityInfo(E entity)
	{
		Map<String, Object> map = new HashMap<>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			try
			{
				field.setAccessible(true);
				map.put(field.getName(), field.get(entity));
			} catch (IllegalArgumentException | IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		}
		return map;
	}

	public HashSet<String> returnFields(Class<?> mappedClass)
	{
		HashSet<String> fieldsSet = classFieldsList.get(mappedClass.getName());
		if (fieldsSet != null)
		{
			return fieldsSet;
		}
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		HashSet<String> mappedProperties = new HashSet<String>();
		for (PropertyDescriptor pd : pds)
		{
			if (pd.getWriteMethod() != null)
			{
				mappedProperties.add(pd.getName());
			}
		}
		classFieldsList.put(mappedClass.getName(), mappedProperties);
		return mappedProperties;
	}

	/**
	 * 转换成标准数据命名 user_name
	 * 
	 * @param name
	 * @return
	 */
	public String underscoreName(String name)
	{
		if (!StringUtils.hasLength(name))
		{
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(name.substring(0, 1).toLowerCase());
		for (int i = 1; i < name.length(); i++)
		{
			String s = name.substring(i, i + 1);
			String slc = s.toLowerCase();
			if (!s.equals(slc))
			{
				result.append("_").append(slc);
			} else
			{
				result.append(s);
			}
		}
		return result.toString();
	}

}
