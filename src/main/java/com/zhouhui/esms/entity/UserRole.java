package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户角色;用户与角色的中间表
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Data
@TableName("esms_user_role")
@ApiModel(value = "UserRole对象", description = "用户角色;用户与角色的中间表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

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