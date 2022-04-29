package com.zhouhui.esms.serviceImpl;

import com.zhouhui.esms.entity.SuppliesType;
import com.zhouhui.esms.mapper.SuppliesTypeMapper;
import com.zhouhui.esms.service.SuppliesTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物资类别;此为多级目录，一级目录下可以添加二级子目录，展开可以显示二级子目录列表，往下依次类推实现多级目录显示。 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class SuppliesTypeServiceImpl extends ServiceImpl<SuppliesTypeMapper, SuppliesType> implements SuppliesTypeService {

}
