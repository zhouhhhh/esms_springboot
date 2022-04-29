package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty("物资类别;根据类别ID取自类别表")
    @TableField("supplies_type_id")
    private Integer suppliesTypeId;

    @ApiModelProperty("图片")
    @TableField("supplies_pic")
    private String suppliesPic;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField("updated_by")
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


}