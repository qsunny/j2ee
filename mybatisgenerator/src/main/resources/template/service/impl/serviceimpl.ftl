package com.ceacsz.dao.${model};


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.allchips.common.base.page.Page;
import com.allchips.${model}.common.bean.${objectName};
import com.allchips.${model}.core.dao.I${objectName}Dao;

/**
* <p>I${objectName}Service接口实现类</p>
* <p> ${author} </p>
* <p>Version:1.0.0</p>
* <p>Create Date:${createTime} </p>
* <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
*/
@Service
public class ${objectName}ServiceImpl implements I${objectName}Service {
	@Resource
	private I${objectName}Dao ${fLowerObjectName}Dao;

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public ${objectName} get${objectName}ById(String id) throws Exception {
		return this.${fLowerObjectName}Dao.get${objectName}ById(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public List<${objectName}> getAll(${objectName} ${fLowerObjectName}) throws Exception {
		return this.${fLowerObjectName}Dao.getAll(${fLowerObjectName});
	}

	@Transactional(propagation=Propagation.SUPPORTS,
	isolation= Isolation.READ_COMMITTED,
	readOnly=true)
	@Override
	public Page<${objectName}> getPagerModelByQuery(${objectName} ${fLowerObjectName}, Page<${objectName}> page)
			throws Exception {
		List<${objectName}> result = this.${fLowerObjectName}Dao.getPagerModelByQuery(${fLowerObjectName}, page);
		page.setResult(result);
		return page;
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int insert${objectName}(${objectName} ${fLowerObjectName}) throws Exception {
		return this.${fLowerObjectName}Dao.insert${objectName}(${fLowerObjectName});
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int del${objectName}ById(String id) throws Exception {
		return this.${fLowerObjectName}Dao.del${objectName}ById(id);
	}

	@Transactional(propagation= Propagation.REQUIRED,
	rollbackFor=Exception.class
	)
	@Override
	public int update${objectName}(${objectName} ${fLowerObjectName}) throws Exception {
		return this.${fLowerObjectName}Dao.update${objectName}(${fLowerObjectName});
	}
}

