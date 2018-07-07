package com.ruzhen.service;

import com.ruzhen.pojo.SysPermission;
import com.ruzhen.pojo.UserInfo;

import java.util.List;

/**
 * <p>
 *  角色权限服务类
 * </p>
 *  @author lizhen
 *  @since 2018-07-06
 */
public interface ISysPermissionService {

    List<SysPermission> selectPermByUser(UserInfo userInfo) throws Exception;

    List<SysPermission> findAllPermission();
}
