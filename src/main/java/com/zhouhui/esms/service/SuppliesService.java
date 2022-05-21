package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhouhui.esms.entity.Supplies;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.SuppliesMidType;

/**
 * <p>
 * 物资库存 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
public interface SuppliesService extends IService<Supplies> {

    IPage<Supplies> pageWithSuppliesType(IPage<Supplies> page, QueryWrapper<SuppliesMidType> queryWrapper);
}
