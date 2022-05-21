package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesMidType;
import com.zhouhui.esms.mapper.SuppliesMapper;
import com.zhouhui.esms.service.SuppliesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物资库存 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class SuppliesServiceImpl extends ServiceImpl<SuppliesMapper, Supplies> implements SuppliesService {

    @Override
    public IPage<Supplies> pageWithSuppliesType(IPage<Supplies> page, QueryWrapper<SuppliesMidType> queryWrapper) {
        SuppliesMapper baseMapper = getBaseMapper();
        return baseMapper.pageWithSuppliesType(page,queryWrapper);
    }
}
