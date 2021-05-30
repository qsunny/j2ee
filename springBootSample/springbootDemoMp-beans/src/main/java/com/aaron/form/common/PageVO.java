package com.aaron.form.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class PageVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("当前页码，从1开始")
    private Integer page;
    @ApiModelProperty("每页显示记录数")
    private Integer limit;
}
