package com.zhouhui.esms.service;

import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesMidType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-20
 */
public interface SuppliesMidTypeService extends IService<SuppliesMidType> {

    void saveOrUpdateBysuppliesId(Supplies supplies);
}
