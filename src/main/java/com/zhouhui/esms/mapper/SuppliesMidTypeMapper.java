package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.SuppliesMidType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-20
 */
@Mapper
public interface SuppliesMidTypeMapper extends BaseMapper<SuppliesMidType> {

    @Delete("delete from esms_supplies_mid_type where supplies_id = #{suppliesId}")
    void deleteBySuppliesId(Integer suppliesId);
}
