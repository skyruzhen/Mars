package com.ruzhen.shiro;


import com.ruzhen.pojo.SysPermission;
import com.ruzhen.pojo.SysRole;
import com.ruzhen.pojo.UserInfo;
import com.ruzhen.service.ISysPermissionService;
import com.ruzhen.service.ISysRoleService;
import com.ruzhen.service.IUserService;
import com.ruzhen.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by lizhen 2018年7月4日
 * 自定义权限匹配和账号密码匹配
 */
@Configuration
public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ISysRoleService iSysRoleService;

    @Resource
    private ISysPermissionService iSysPermissionService;

    @Resource
    private IUserService iUserService = new UserServiceImpl();

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        try {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            List<SysRole> roles = iSysRoleService.selectRoleByUser(userInfo.getUserid());
            for (SysRole role : roles) {
                authorizationInfo.addRole(role.getName());
            }
            List<SysPermission> sysPermissions = iSysPermissionService.selectPermByUser(userInfo);
            for (SysPermission perm : sysPermissions) {
                authorizationInfo.addStringPermission(perm.getName());
            }
            return authorizationInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /*登录认证，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = iUserService.selectOne(username);
        if (userInfo == null) {
            logger.info("--------------------未查到此用户{}--------------------",username);
            return null;
        }
        logger.info("--------------------查询成功{}--------------------",username);
        //放入session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user", userInfo);//成功则放入session

        if ("N".equals(userInfo.getActive())) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                new SimpleByteSource(String.valueOf(userInfo.getSalt()).toCharArray()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
