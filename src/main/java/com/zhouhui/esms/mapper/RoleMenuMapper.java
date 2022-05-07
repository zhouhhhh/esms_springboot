package com.zhouhui.esms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouhui.esms.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from esms_role_menu where role_id = #{roleId}")
    void deleteRoleMenu(Integer roleId);

    @Select("select menu_id from esms_role_menu where role_id = #{roleId}")
    List<Integer> selectListByRoleId(Integer roleId);
}
