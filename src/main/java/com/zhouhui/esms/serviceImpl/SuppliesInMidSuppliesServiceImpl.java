package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesInMidSupplies;
import com.zhouhui.esms.mapper.SuppliesInMidSuppliesMapper;
import com.zhouhui.esms.service.SuppliesInMidSuppliesService;
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
public class SuppliesInMidSuppliesServiceImpl extends ServiceImpl<SuppliesInMidSuppliesMapper, SuppliesInMidSupplies> implements SuppliesInMidSuppliesService {

    @Override
    public boolean saveSuppliesInAndSupplies(Integer id,List<Supplies> suppliesList) {
        SuppliesInMidSuppliesMapper baseMapper = getBaseMapper();
        //首先这个对象集合字段只有物资id和数量，所以增加进表里就行
        for (Supplies supplies : suppliesList){
            SuppliesInMidSupplies suppliesInMidSupplies = new SuppliesInMidSupplies();
            suppliesInMidSupplies.setSuppliesId(supplies.getSuppliesId());
            suppliesInMidSupplies.setSuppliesCount(supplies.getSuppliesCount());
            suppliesInMidSupplies.setSuppliesInId(id);
            baseMapper.insert(suppliesInMidSupplies);
        }
        return true;
    }
}
