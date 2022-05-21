package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesMidType;
import com.zhouhui.esms.mapper.SuppliesMidTypeMapper;
import com.zhouhui.esms.service.SuppliesMidTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-20
 */
@Service
public class SuppliesMidTypeServiceImpl extends ServiceImpl<SuppliesMidTypeMapper, SuppliesMidType> implements SuppliesMidTypeService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateBysuppliesId(Supplies supplies) {
        SuppliesMidTypeMapper baseMapper = getBaseMapper();
        //先删除
        baseMapper.deleteBySuppliesId(supplies.getSuppliesId());
        //后增加
        for (Integer typeId:supplies.getTypeId()){
            SuppliesMidType suppliesMidType = new SuppliesMidType();
            suppliesMidType.setSuppliesId(supplies.getSuppliesId());
            suppliesMidType.setSuppliesTypeId(typeId);
            baseMapper.insert(suppliesMidType);
        }
    }
}
