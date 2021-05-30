package com.aaron.vo.springbootDemoMp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "副标题")
    private String subtitle;

    @ApiModelProperty(value = "是否固定日期 1:是 0:否")
    private Boolean fixedDate;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "领取后当天生效，有效天数")
    private Integer fixedDays;

    @ApiModelProperty(value = "状态 0:失效 1:有效")
    private Boolean enabled;

    @ApiModelProperty(value = "优惠券类型 1:满减 2:满折")
    private Integer couponType;

    @ApiModelProperty(value = "最低消费")
    private Integer limitAmount;

    @ApiModelProperty(value = "类型 减额 或 几折")
    private BigDecimal reduce;

    @ApiModelProperty(value = "发放量")
    private Integer quantity;

    @ApiModelProperty(value = "剩余数量")
    private Integer residueQuantity;

    @ApiModelProperty(value = "用户类型 1:非渠道商 2:渠道商 3:所有用户")
    private Integer userType;

    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;


}
