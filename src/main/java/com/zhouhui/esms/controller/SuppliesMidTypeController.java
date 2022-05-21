package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouhui.esms.entity.SuppliesMidType;
import com.zhouhui.esms.service.SuppliesMidTypeService;
import com.zhouhui.esms.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/esms/supplies-mid-type")
public class SuppliesMidTypeController {

    @Autowired
    SuppliesMidTypeService suppliesMidTypeService;

    @GetMapping("/suppliesTypeIds/{suppliesId}")
    public R getSuppliesTypesBySuppliesId(@PathVariable Integer suppliesId){
        QueryWrapper<SuppliesMidType> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("supplies_id",suppliesId);
        List<SuppliesMidType> list = suppliesMidTypeService.list(queryWrapper);
        return R.ok().data("typeIdList",list.stream().map(x -> x.getSuppliesTypeId()).collect(Collectors.toList()));

    }
}
