package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色;不同的角色有不同的权限
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_role")
@ApiModel(value = "Role对象", description = "角色;不同的角色有不同的权限")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;


    @ApiModelProperty("角色名")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("角色介绍;大致介绍角色的权限范围")
    @TableField("role_description")
    private String roleDescription;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField(value = "updated_by",fill = FieldFill.INSERT)
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time",fill = FieldFill.UPDATE)
    private Date updatedTime;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    @Version
    private Integer version;

    @ApiModelProperty("逻辑删除;0代表未删除，1代表删除，默认值为0")
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag;


}