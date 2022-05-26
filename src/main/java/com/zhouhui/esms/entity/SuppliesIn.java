package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @TableField("supplies_in_root")
    private String suppliesInRoot;

    @ApiModelProperty("省份")
    @TableField("supplies_in_provinces")
    private String suppliesInProvinces;

    @ApiModelProperty("城市")
    @TableField("supplies_in_city")
    private String suppliesInCity;

    @ApiModelProperty("区县")
    @TableField("supplies_in_county")
    private String suppliesInCounty;

    @ApiModelProperty("详细地址")
    @TableField("supplies_in_address")
    private String suppliesInAddress;

    @ApiModelProperty("捐赠人")
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

    @ApiModelProperty("订单中的物资")
    @TableField(exist = false)
    private List<Supplies> suppliesList;

}