package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 物资入库
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_supplies_in")
@ApiModel(value = "SuppliesIn对象", description = "物资入库")
public class SuppliesIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入库单号;uuid")
    @TableField("supplies_in_no")
    private String suppliesInNo;

    @ApiModelProperty("入库单主键")
    @TableId(value = "supplies_in_id", type = IdType.AUTO)
    private Integer suppliesInId;

    @ApiModelProperty("物资来源")
    @TableField("SUPPLIES_IN_ROOT")
    private String suppliesInRoot;

    @ApiModelProperty("省份")
    @TableField("SUPPLIES_IN_PROVINCES")
    private String suppliesInProvinces;

    @ApiModelProperty("城市")
    @TableField("SUPPLIES_IN_CITY")
    private String suppliesInCity;

    @ApiModelProperty("区县")
    @TableField("SUPPLIES_IN_COUNTY")
    private String suppliesInCounty;

    @ApiModelProperty("详细地址")
    @TableField("SUPPLIES_IN_ADDRESS")
    private String suppliesInAddress;

    @ApiModelProperty("捐赠人")
    @TableField("DONORS")
    private String donors;

    @ApiModelProperty("邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty("电话")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty("描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty("物资ID")
    @TableField("SUPPLIES_ID")
    private Integer suppliesId;

    @ApiModelProperty("物资数量")
    @TableField("SUPPLIES_COUNT")
    private Integer suppliesCount;

    @ApiModelProperty("创建人")
    @TableField("CREATED_BY")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATED_TIME",fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField("UPDATED_BY")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "UPDATED_TIME",fill = FieldFill.UPDATE)
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