package com.ceacsz.dao.${model};

import java.util.List;
import com.allchips.common.base.page.Page;
import com.allchips.${model}.api.bean.${objectName};

/**
* <p>I${objectName}Service接口</p>
* <p> ${author} </p>
* <p>Version:1.0.0</p>
* <p>Create Date:${createTime} </p>
* <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
*/
public interface I${objectName}Service {

	/**
	* 根据id查询${objectName}对象
	*@param id
	*@return 返回${objectName}
	*/
	public ${objectName} get${objectName}ById(String id) throws Exception;

	/**
	* 根据条件查询${objectName}列表
	* @param ${fLowerObjectName}
	* @return 返回${objectName}列表
	*/
	public List<${objectName}> getAll(${objectName} ${fLowerObjectName}) throws Exception;

	/**
	* 根据条件分页查询${objectName}列表
	* @param ${fLowerObjectName}
	* @param page
	* @return 返回${objectName}列表
	*/
	public Page<${objectName}> getPagerModelByQuery(${objectName} ${fLowerObjectName}, Page<${objectName}> page) throws Exception;

	/**
	* 添加${objectName}对象
	* @param ${fLowerObjectName}
	* @return 返回添加成功的记录条数
	*/
	public int insert${objectName}(${objectName} ${fLowerObjectName}) throws Exception;

	/**
	* 根据id删除${objectName}对象
	* @param id
	* @return 返回添加成功的记录条数
	*/
	public int del${objectName}ById(String id) throws Exception;

	/**
	* 根据${objectName}id删除${objectName}对象
	* @param ${fLowerObjectName}
	* @return 返回修改${objectName}成功的记录条数
	*/
	public int update${objectName}(${objectName} ${fLowerObjectName}) throws Exception;
}
