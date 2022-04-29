package com.zhouhui.esms.mapper;

import com.zhouhui.esms.entity.SuppliesType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 物资类别;此为多级目录，一级目录下可以添加二级子目录，展开可以显示二级子目录列表，往下依次类推实现多级目录显示。 Mapper 接口
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Mapper
public interface SuppliesTypeMapper extends BaseMapper<SuppliesType> {

}
