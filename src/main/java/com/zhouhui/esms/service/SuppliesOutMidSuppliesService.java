package com.zhouhui.esms.service;

import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesOutMidSupplies;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-22
 */
public interface SuppliesOutMidSuppliesService extends IService<SuppliesOutMidSupplies> {

    boolean saveSuppliesOutAndSupplies(Integer suppliesOutId, List<Supplies> suppliesList);
}
