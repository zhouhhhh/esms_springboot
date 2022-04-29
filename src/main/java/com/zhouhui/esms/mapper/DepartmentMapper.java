package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门;代表用户所属的部门 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
