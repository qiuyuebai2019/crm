package com.qust.Realm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qust.domain.AjaxRes;
import com.qust.domain.Employee;
import com.qust.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月01日 10时45分
 */
public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        Employee employee = (Employee)collection.getPrimaryPrincipal();
        List<String> role = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        if(employee.getAdmin()){
            permissions.add("*:*");
        }else {
            role = employeeService.getRoleById(employee.getId());
            permissions = employeeService.getPermissionById(employee.getId());
        }
//        添加授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(role);
        info.addStringPermissions(permissions);
        return info;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Employee employee = employeeService.findEmloyeeByName((String)token.getPrincipal());
        if(employee == null){
            return null;
        }else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes(employee.getUsername()),this.getName());
            return info;
        }
    }
}
