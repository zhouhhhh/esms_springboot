package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesInMidSupplies;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-22
 */
public interface SuppliesInMidSuppliesService extends IService<SuppliesInMidSupplies> {

    boolean saveSuppliesInAndSupplies(Integer id, List<Supplies> suppliesList);
}
