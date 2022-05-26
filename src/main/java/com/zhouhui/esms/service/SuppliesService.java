package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesInMidSupplies;
import com.zhouhui.esms.entity.SuppliesOutMidSupplies;

import java.util.List;

/**
 * <p>
 * 物资库存 服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
public interface SuppliesService extends IService<Supplies> {

    boolean updateCount(List<Supplies> suppliesList,boolean t);

    List<Supplies> listByIdsWithCountIn(List<SuppliesInMidSupplies> list);
    List<Supplies> listByIdsWithCountOut(List<SuppliesOutMidSupplies> list);
}
