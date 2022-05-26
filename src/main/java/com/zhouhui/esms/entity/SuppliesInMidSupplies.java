package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-22
 */
@Data
@TableName("esms_supplies_in_mid_supplies")
@ApiModel(value = "SuppliesInMidSupplies对象", description = "")
public class SuppliesInMidSupplies implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("入库id")
    @TableField("supplies_in_id")
    private Integer suppliesInId;

    @ApiModelProperty("物资id")
    @TableField("supplies_id")
    private Integer suppliesId;

    @ApiModelProperty("物资数量")
    @TableField("supplies_count")
    private Integer suppliesCount;

    @ApiModelProperty("创建人")
    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField(value = "updated_by",fill = FieldFill.UPDATE)
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time",fill = FieldFill.UPDATE)
    private Date updatedTime;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    private Integer version;

    @ApiModelProperty("逻辑删除;0代表未删除，1代表删除，默认值为0")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


}