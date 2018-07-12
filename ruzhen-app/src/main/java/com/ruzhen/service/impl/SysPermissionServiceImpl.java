package com.ruzhen.service.impl;

import com.ruzhen.pojo.SysPermission;
import com.ruzhen.pojo.UserInfo;
import com.ruzhen.service.ISysPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author lizhen
 * @since 2018年7月4日
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl  implements ISysPermissionService {

    @Override
    public List<SysPermission> selectPermByUser(UserInfo userInfo) throws Exception {
        //TODO:模拟测试数据
        List<SysPermission> list = new ArrayList<>();
        SysPermission permission = new SysPermission();
        permission.setId(1);
        permission.setUrl("/admin");
        permission.setName("usermanager");
        list.add(permission);
        return list;
    }

    @Override
    public List<SysPermission> findAllPermission() {
        return null;
    }
}
