package com.aaron.form.springbootDemoMp;

import com.aaron.form.common.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActivityQueryForm extends PageVO {
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "会员等级id")
    private Integer levelId;
}
