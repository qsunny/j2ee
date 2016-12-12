package com.aaron.springweb.core.dao;

import com.aaron.springweb.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron.Qiu on 2016/12/12.
 */
public abstract class BaseDao<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected SimpleJdbcInsert simpleJdbcInsert;

    /**
     * 插入实体
     * @param entity
     * @return
     */
    public int add(T entity) {
        if(!simpleJdbcInsert.isCompiled()){
            String tableName = entity.getClass().getSimpleName();
            simpleJdbcInsert.setTableName(tableName.toLowerCase());
        }
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        return simpleJdbcInsert.execute(namedParameters);
    }

    /**
     * 插入并返回自增主键
     * @param entity
     * @return
     */
    public long addAndReturnId(T entity) {
        String tableName =  entity.getClass().getSimpleName();
        if(!simpleJdbcInsert.isCompiled()){
            simpleJdbcInsert.setTableName(tableName.toLowerCase());
            simpleJdbcInsert.setGeneratedKeyName("id");
        }
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
        Number newId = simpleJdbcInsert.executeAndReturnKey(namedParameters);
        return newId.longValue();
    }

    /**
     * 批量插入
     * @param entityCollection
     * @param entityClazz
     * @return
     */
    public int addList(Collection<T> entityCollection, Class<T> entityClazz) {
        String tableName = entityClazz.getSimpleName();
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils
                .createBatch(entityCollection.toArray());
        if(!simpleJdbcInsert.isCompiled())
            simpleJdbcInsert.setTableName(tableName.toLowerCase());
        int[] result = simpleJdbcInsert.executeBatch(batchArgs);
        return result.length;
    }
    /**
     * sql插入
     * @param sql
     * @return
     */
    public long addBySql(final String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //创建preparestatment
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
                return ps;
            }
        };
        jdbcTemplate.update(psc,keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 删除实体
     * @param entity
     * @return
     */
    public int delete(T entity) {
        String tableName = entity.getClass().getSimpleName();
        String sql = "DELETE FROM " + tableName + " WHERE id =:id";
        return namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(entity));
    }

    /**
     * 根据id删除实体
     * @param entityClazz
     * @param id
     * @return
     */
    public int deleteById(Class<T> entityClazz, int id) {
        String tableName = entityClazz.getSimpleName();
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        return namedParameterJdbcTemplate.getJdbcOperations().update(sql, id);
    }
    /**
     * 批量删除
     * @param entityCollection
     * @param entityClazz
     * @return
     */
    public int[] deleteList(Collection<T> entityCollection, Class<T> entityClazz) {
        String tableName = entityClazz.getSimpleName();
        String sql = "delete from "+ tableName +" where id=?";
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils
                .createBatch(entityCollection.toArray());
        if(!simpleJdbcInsert.isCompiled())
            simpleJdbcInsert.setTableName(tableName.toLowerCase());
        int[] result = namedParameterJdbcTemplate.batchUpdate(sql,batchArgs);
        return result;
    }

    private  String getUpdateSql(Class entity){
        String tableName =  entity.getSimpleName().toLowerCase();
        Field[] fileds = entity.getDeclaredFields();
        StringBuilder sql = new StringBuilder("update "+tableName+" set ");
        for (Field field : fileds) {
            String fieldName = field.getName();
            if (!fieldName.equalsIgnoreCase("id")){
                sql.append(fieldName + " :" + fieldName + ",");
            }
        }
        sql.replace(sql.lastIndexOf(","), sql.length(), "");
        sql.append(" WHERE id = :id");
        return sql.toString();
    }

    /**
     * 更新实体
     * @param entity
     * @return
     */
    public int update(T entity){
        String sql = getUpdateSql(entity.getClass());
        SqlParameterSource ps = new BeanPropertySqlParameterSource(entity);

        return namedParameterJdbcTemplate.update(sql, ps);
    }


    /**
     * 批量更新
     * @param collection
     * @param cls
     * @return
     */
    public int[] updateList(Collection<T> collection,Class<T> cls){
        String sql = getUpdateSql(cls);
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils
                .createBatch(collection.toArray());
        int[] result = namedParameterJdbcTemplate.batchUpdate(sql,
                batchArgs);
        return result;
    }

    /**
     * 根据id查找对象
     * @param entityClazz
     * @param id
     * @return
     */
    public T findById(Class<T> entityClazz, int id) {
        String tableName = entityClazz.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                sql, BeanPropertyRowMapper.newInstance(entityClazz), id);
    }
    /**
     * 根据sql查找对象(单表)
     * @param sql
     * @param args
     * @param entityClazz
     * @return
     */
    public List<T> findByArgs(String sql, Object[] args, Class<T> entityClazz){
        return jdbcTemplate.queryForList(sql, args, entityClazz);
    }
    /**
     * 根据sql查找列表(多表)
     * @param sql
     * @param args
     * @return
     */
    public List<Map<String,Object>> queryForList(String sql, Object[] args){
        return jdbcTemplate.queryForList(sql, args);
    }


    /**
     * 分页查找
     * @param sql
     * @param args
     * @return
     */
    public Page<T> findBySqlForPage(String sql, Object[] args) {
        return findBySqlForPage(sql,args,null);
    }

    /**
     * 分页查找
     * @param sql
     * @param args
     * @return
     */
    public Page<T> findBySqlForPage(String sql, Object[] args,Page<T> Page) {
        Long total = getCount(sql, args);
        if(Page == null){
            Page = new Page<T>();
        }
        sql = sql + getPageLimit(Page);
        List<T> datas = (List<T>) queryForList(sql, args);

        Page.setCount(total);
        Page.setData(datas);
        return Page;
    }


    /**
     * 返回最大ID
     * @param cls
     * @return
     */
    public int getMaxId(Class cls){
        String tableName = cls.getSimpleName();
        return jdbcTemplate.queryForObject("select max(`id`) from "+tableName,Integer.class);
    }

    /**
     * 专门为?的自定义sql获取总数
     * @param sql
     * @param args
     * @return
     */
    public Long getCount(String sql, Object[] args) {
        //String e = sql.substring(sql.indexOf("from"));
        //String c = "select count(*) " + e;
        String c = "select count(*) from ( " + sql +" ) t";
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(c, args, Long.class);
    }

    /**
     * 为自定义的sql添加Limit
     * @param Page
     * @return
     */
    public String getPageLimit(Page Page) {
        int pageOffset = 0;
        int pageNum = Page.getCurrNum();
        int pageSize = Page.getPreSize();
        if(pageNum > 0){
            pageOffset = (pageNum-1)*pageSize;
        }else{
            pageOffset = 0;
        }
        Page.setOffset(pageOffset);
        Page.setPreSize(pageSize);

        StringBuilder sb = new StringBuilder(" limit ");
        sb.append(pageOffset);
        sb.append(",");
        sb.append(pageSize);
        return sb.toString();
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    public int tableExist(String tableName){
        String sql = "select count(*) from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='pplive_jcms' and TABLE_NAME='"+tableName+"'";
        return this.jdbcTemplate.queryForObject(sql,Integer.class);
    }
}
