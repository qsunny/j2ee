package com.tulang.${model}.dao.${fLowerObjectName};

import java.util.List;
import org.springframework.stereotype.Repository;
import com.tulang.tools.pager.PagerModel;
import com.tulang.tools.pager.Query;
import com.tulang.tools.template.MybatisTemplate;
import com.tulang.beans.${model}.${fLowerObjectName}.${objectName};
import com.tulang.${model}.dao.${fLowerObjectName}.I${objectName}Dao;

/**
 * <p>I${objectName}Dao接口实现类</p>
 * <p>Version:1.0.0</p>
 * <p>Create Date:${createTime} </p>
 * <p>Copyright (c) 2017 ~ 2022 tulang版权所有</p>
 */
@Repository
public class ${objectName}DaoImpl extends MybatisTemplate implements I${objectName}Dao {

	@Override
	public ${objectName} get${objectName}ById(Long id) throws Exception {
		return this.selectOne("${objectName}XML.get${objectName}ById", id);
	}

	@Override
	public List<${objectName}> getAll(${objectName} ${fLowerObjectName}) throws Exception {
		return this.selectList("${objectName}XML.getAll", ${fLowerObjectName});
	}

	@Override
	public PagerModel<${objectName}> getPagerModelByQuery(${objectName} ${fLowerObjectName}, Query query)
			throws Exception {
		return this.getPagerModelByQuery(${fLowerObjectName}, query, "${objectName}XML.getPagerModelByQuery");
	}

	@Override
	public int insert${objectName}(${objectName} ${fLowerObjectName}) throws Exception {
		return this.insert("${objectName}XML.insert${objectName}", ${fLowerObjectName});
	}
	
	@Override
	public int del${objectName}ById(String id) throws Exception {
		return this.delete("${objectName}XML.del${objectName}ById", id);
	}

	@Override
	public int update${objectName}(${objectName} ${fLowerObjectName}) throws Exception {
		return this.update("${objectName}XML.update${objectName}", ${fLowerObjectName});
	}
}

