package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesOutMidSupplies;
import com.zhouhui.esms.mapper.SuppliesOutMidSuppliesMapper;
import com.zhouhui.esms.service.SuppliesOutMidSuppliesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-22
 */
@Service
public class SuppliesOutMidSuppliesServiceImpl extends ServiceImpl<SuppliesOutMidSuppliesMapper, SuppliesOutMidSupplies> implements SuppliesOutMidSuppliesService {

    @Override
    public boolean saveSuppliesOutAndSupplies(Integer suppliesOutId, List<Supplies> suppliesList) {
        SuppliesOutMidSuppliesMapper baseMapper = getBaseMapper();
        //首先这个对象集合字段只有物资id和数量，所以增加进表里就行
        for (Supplies supplies : suppliesList){
            SuppliesOutMidSupplies suppliesOutMidSupplies = new SuppliesOutMidSupplies();
            suppliesOutMidSupplies.setSuppliesId(supplies.getSuppliesId());
            suppliesOutMidSupplies.setSuppliesCount(supplies.getSuppliesCount());
            suppliesOutMidSupplies.setSuppliesOutId(suppliesOutId);
            baseMapper.insert(suppliesOutMidSupplies);
        }
        return true;
    }
}
