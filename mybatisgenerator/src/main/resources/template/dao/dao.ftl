package com.tulang.dao.${model};

import java.util.List;
import com.tulang.common.base.page.Page;
import com.tulang.beans.${model}.${fLowerObjectName}.${objectName};
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

 /**
 * <p>I${objectName}Dao接口</p>
 * <p> ${author} </p>
 * <p>Version:1.0.0</p>
 * <p>Create Date:${createTime} </p>
 * <p>Copyright (c) 2017 ~ 2022 tulang版权所有</p>
 */
@Repository
public interface I${objectName}Dao {

	/**
	* 根据id查询${objectName}对象
	* @param id
	* @return 返回${objectName}
	* @throws Exception
	*/
	public ${objectName} get${objectName}ById(Long id) throws Exception;

	/**
	 * 根据条件查询${objectName}列表	 
	 * @param ${fLowerObjectName}
     * @return 返回${objectName}列表
     * @throws Exception
	 */
	public List<${objectName}> getAll(${objectName} ${fLowerObjectName}) throws Exception;

	/**
	* 根据条件分页查询${objectName}列表
	* @param ${fLowerObjectName}
	* @param page
	* @return 返回${objectName}列表
	* @throws Exception
	*/
	public List<${objectName}> getPagerModelByQuery(@Param("${fLowerObjectName}") ${objectName} ${fLowerObjectName}, @Param("page") Page<${objectName}> page) throws Exception;

	/**
	 * 添加${objectName}对象
	 * @param ${fLowerObjectName}
	 * @return 返回添加成功的记录条数
	 * @throws Exception
	 */
	public int insert${objectName}(${objectName} ${fLowerObjectName}) throws Exception;

	/**
	* 根据id删除${objectName}对象
	* @param id
	* @return 返回添加成功的记录条数
	* @throws Exception
	*/
	public int del${objectName}ById(String id) throws Exception;

	/**
	* 根据${objectName}id删除${objectName}对象
	* @param ${fLowerObjectName}
	* @return 返回修改${objectName}成功的记录条数
	* @throws Exception
	*/
	public int update${objectName}(${objectName} ${fLowerObjectName}) throws Exception;
}
