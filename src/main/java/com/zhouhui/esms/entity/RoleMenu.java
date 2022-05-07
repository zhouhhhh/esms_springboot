package com.zhouhui.esms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("esms_role_menu")
@Data
public class RoleMenu {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;
    private Integer menuId;
}
