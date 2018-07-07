package com.ruzhen.dao;

import com.ruzhen.pojo.SysRole;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈查询角色信息〉
 *
 * @author lizhen
 * @create 2018/7/6
 * @since 1.0.0
 */
public interface RoleDao {
   List<String>  selectRoleByUserId(int userId);

   SysRole queryRole(String role);
}
