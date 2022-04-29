package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 物资发放
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_supplies_out")
@ApiModel(value = "SuppliesOut对象", description = "物资发放")
public class SuppliesOut implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("出库单号;uuid")
    @TableField("supplies_out_no")
    private String suppliesOutNo;

    @ApiModelProperty("出库单主键")
    @TableId(value = "supplies_out_id", type = IdType.AUTO)
    private Integer suppliesOutId;

    @ApiModelProperty("优先级")
    @TableField("supplies_out_level")
    private Integer suppliesOutLevel;

    @ApiModelProperty("物资发放类型")
    @TableField("supplies_out_type")
    private String suppliesOutType;

    @ApiModelProperty("省份")
    @TableField("supplies_out_provinces")
    private String suppliesOutProvinces;

    @ApiModelProperty("城市")
    @TableField("supplies_out_city")
    private String suppliesOutCity;

    @ApiModelProperty("区县")
    @TableField("supplies_out_county")
    private String suppliesOutCounty;

    @ApiModelProperty("具体去向")
    @TableField("supplies_out_address")
    private String suppliesOutAddress;

    @ApiModelProperty("联系人")
    @TableField("donors")
    private String donors;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("物资ID")
    @TableField("supplies_id")
    private Integer suppliesId;

    @ApiModelProperty("物资数量")
    @TableField("supplies_count")
    private Integer suppliesCount;

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