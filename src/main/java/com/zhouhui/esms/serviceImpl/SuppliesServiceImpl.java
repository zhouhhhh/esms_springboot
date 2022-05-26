package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesInMidSupplies;
import com.zhouhui.esms.entity.SuppliesOutMidSupplies;
import com.zhouhui.esms.mapper.SuppliesInMidSuppliesMapper;
import com.zhouhui.esms.mapper.SuppliesMapper;
import com.zhouhui.esms.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    @Autowired
    SuppliesInMidSuppliesMapper suppliesInMidSuppliesMapper;
    @Override
    public boolean updateCount(List<Supplies> suppliesList,boolean t) {
        SuppliesMapper baseMapper = getBaseMapper();
        for (Supplies supplies:suppliesList){
            if (t){
                baseMapper.updateCount(supplies.getSuppliesId(),supplies.getSuppliesCount());
            }else {
                baseMapper.updateCount(supplies.getSuppliesId(),-supplies.getSuppliesCount());
            }

        }
        return true;
    }

    @Override
    public List<Supplies> listByIdsWithCountIn(List<SuppliesInMidSupplies> list) {
        //首先我们可以循环获取物资信息，然后重新给数量赋值
        SuppliesMapper baseMapper = getBaseMapper();
        List<Supplies> res = new ArrayList<>();
        //遍历中间表
        for (SuppliesInMidSupplies s : list){
            //获取每一条数据，然后根据id获取物质信息，然后重新给count赋值
            Supplies supplies = baseMapper.selectById(s.getSuppliesId());
            supplies.setSuppliesCount(s.getSuppliesCount());
            res.add(supplies);
        }
        return res;
    }

    @Override
    public List<Supplies> listByIdsWithCountOut(List<SuppliesOutMidSupplies> list) {
        //首先我们可以循环获取物资信息，然后重新给数量赋值
        SuppliesMapper baseMapper = getBaseMapper();
        List<Supplies> res = new ArrayList<>();
        //遍历中间表
        for (SuppliesOutMidSupplies s : list){
            //获取每一条数据，然后根据id获取物质信息，然后重新给count赋值
            Supplies supplies = baseMapper.selectById(s.getSuppliesId());
            supplies.setSuppliesCount(s.getSuppliesCount());
            res.add(supplies);
        }
        return res;
    }
}
