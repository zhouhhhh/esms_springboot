package com.zhouhui.esms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouhui.esms.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-06
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findAll(String menuName);

    boolean saveOrUpdateAndSoOn(Menu menu);

}
