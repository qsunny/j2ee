package com.aaron.form.common;

import com.aaron.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 分页请求参数Form
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PageVO extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("当前页码，从1开始")
    protected Integer page = 1;
    @ApiModelProperty("每页显示记录数")
    protected Integer pageSize = 20;
    @ApiModelProperty("扩展参数")
    private Map<String, Object> extraParam;

}
