package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.SuppliesType;
import com.zhouhui.esms.mapper.SuppliesTypeMapper;
import com.zhouhui.esms.service.SuppliesTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SuppliesType> findAll() {

        SuppliesTypeMapper baseMapper = getBaseMapper();
        QueryWrapper<SuppliesType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        List<SuppliesType> list = list(queryWrapper);
        //找出一级菜单
        List<SuppliesType> level1Node = list.stream().filter(suppliesType -> {
            if (suppliesType.getParentId() == null) {
                suppliesType.setLevel(1);
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        //最多三级菜单
        for (SuppliesType type1 : level1Node) {
            List<SuppliesType> level2Node = list.stream().filter(m -> {
                if (type1.getTypeId().equals(m.getParentId())) {
                    m.setLevel(2);
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            type1.setChildren(level2Node);
            for (SuppliesType type2 : level2Node) {
                List<SuppliesType> level3Node = list.stream().filter(m -> {
                    if (type2.getTypeId().equals(m.getParentId())) {
                        m.setLevel(3);
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());
                type2.setChildren(level3Node);
            }
        }
        return level1Node;
    }

    @Override
    public List<Integer> findChildrenTypeIds(Integer id) {

        ArrayList<Integer> list = new ArrayList<>();
        //加入此id
        list.add(id);
        SuppliesTypeMapper baseMapper = getBaseMapper();
        QueryWrapper<SuppliesType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.eq("parent_id",id);
        //查询子节点集合
        List<SuppliesType> suppliesTypes = baseMapper.selectList(queryWrapper);
        List<Integer> list1 = suppliesTypes.stream().map(type -> type.getTypeId()).collect(Collectors.toList());
        if (list1 != null && list1.size() > 0){
            //不为空，将所有子节点id加入集合
            list.addAll(new ArrayList<>(list1));
            QueryWrapper<SuppliesType> queryWrapper1 = new QueryWrapper<>();
            queryWrapper.eq("del_flag", 0);
            queryWrapper1.in("parent_id",list1);
            //继续查询子节点，因为一共就三级目录
            List<SuppliesType> suppliesTypes1 = baseMapper.selectList(queryWrapper1);
            if (suppliesTypes1 != null){
                List<Integer> list2 = suppliesTypes1.stream().map(type -> type.getTypeId()).collect(Collectors.toList());
                if (list2 != null && list2.size() > 0){
                    //同上
                    list.addAll(new ArrayList<>(list2));
                }
            }
        }
        return list;

    }
}
