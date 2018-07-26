package com.ruzhen.service.impl;

import com.ruzhen.pojo.SysRole;
import com.ruzhen.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lizhen
 * @since 2018年7月4日
 */
@Service("iSysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {
//    private RoleDao roleDao;

    @Override
    public List<SysRole> selectRoleByUser(int userId) throws Exception {
    /*    List<SysRole> sysRoles = new ArrayList<>();
        List<String> roles = roleDao.selectRoleByUserId(userId);
        for (String role:roles)sysRoles.add(roleDao.queryRole(role));*/
        return null;
    }




}
