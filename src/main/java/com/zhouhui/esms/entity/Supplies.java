package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 物资库存
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_supplies")
@ApiModel(value = "Supplies对象", description = "物资库存")
public class Supplies implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("物资id")
    @TableId(value = "supplies_id", type = IdType.AUTO)
    private Integer suppliesId;

    @ApiModelProperty("物资名称")
    @TableField("supplies_name")
    private String suppliesName;

    @ApiModelProperty("规格")
    @TableField("supplies_size")
    private String suppliesSize;

    @ApiModelProperty("单位")
    @TableField("supplies_unit")
    private String suppliesUnit;

    @ApiModelProperty("数量")
    @TableField("supplies_count")
    private Integer suppliesCount;


    @ApiModelProperty("图片")
    @TableField("supplies_pic")
    private String suppliesPic;

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
    @Version
    private Integer version;

    @ApiModelProperty("逻辑删除;0代表未删除，1代表删除，默认值为0")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty("类别名称")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty("类别id集合")
    @TableField(exist = false)
    private Integer[] typeId;
}