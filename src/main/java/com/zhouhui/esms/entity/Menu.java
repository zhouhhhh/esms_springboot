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
 *
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-06
 */
@Data
@TableName("esms_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty("名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("路径")
    @TableField("menu_path")
    private String menuPath;

    @ApiModelProperty("图标")
    @TableField("menu_icon")
    private String menuIcon;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("父id")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty("页面目录")
    @TableField("dir")
    private String dir;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private Date updatedTime;

    @ApiModelProperty("乐观锁")
    @TableField("version")
    private Integer version;

    @ApiModelProperty("逻辑删除;0代表未删除，1代表删除，默认值为0")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private Integer level;

}