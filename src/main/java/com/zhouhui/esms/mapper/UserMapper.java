package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
