package com.qust.web;

import com.qust.domain.AjaxRes;
import com.qust.domain.Employee;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月01日 15时48分
 */
@Controller
public class CommonController {

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public AjaxRes indexSuccess(Employee employee, HttpServletRequest request){
        AjaxRes ajaxRes = new AjaxRes();
        Subject subject = SecurityUtils.getSubject();
        Md5Hash hash = new Md5Hash(employee.getPassword(),employee.getUsername(),2);
        AuthenticationToken token = new UsernamePasswordToken(employee.getUsername(),hash.toString());
        try{
            subject.login(token);
            Employee emp = (Employee)subject.getPrincipal();
            request.getSession().setAttribute("username",emp.getUsername());
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("成功");
            return ajaxRes;
        }catch (UnknownAccountException e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("查无此人");
            return ajaxRes;
        }catch (IncorrectCredentialsException e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("密码不正确");
            return ajaxRes;
        }

    }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/404")
    public String unfind(){
        return "404.html";
    }

}
