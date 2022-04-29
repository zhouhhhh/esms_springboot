package com.zhouhui.esms.serviceImpl;

import com.zhouhui.esms.entity.Department;
import com.zhouhui.esms.mapper.DepartmentMapper;
import com.zhouhui.esms.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门;代表用户所属的部门 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
