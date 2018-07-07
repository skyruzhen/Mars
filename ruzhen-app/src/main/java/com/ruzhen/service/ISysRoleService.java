package com.ruzhen.service;

import com.ruzhen.pojo.SysRole;

import java.util.List;

/**
 * <p>
 *  角色服务类
 * </p>
 *
 * @author lizhen
 * @since 2018-07-06
 */
public interface ISysRoleService {

    List<SysRole> selectRoleByUser(int userId) throws Exception;
}
