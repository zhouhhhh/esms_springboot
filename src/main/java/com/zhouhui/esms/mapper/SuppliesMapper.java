package com.zhouhui.esms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhouhui.esms.entity.Supplies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 物资库存 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface SuppliesMapper extends BaseMapper<Supplies> {


    void updateCount(@Param("suppliesId") Integer suppliesId,
                     @Param("suppliesCount") Integer suppliesCount);

    /**
     * 带有物资类型的分页
     * @param page
     * @param queryWrapper
     * @return
     */

}
