package com.qust.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qust.domain.AjaxRes;
import com.qust.domain.Employee;
import com.qust.domain.PageListRes;
import com.qust.domain.QueryVo;
import com.qust.service.EmployeeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import  static org.apache.poi.ss.usermodel.CellType.*;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 18时42分
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*
     *前端页面的跳转
     */
    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee(){
        return "views/employee.html";
    }

    /*
     *前端页面获取员工列表数据
     */
    @RequestMapping("employeeList")
    @ResponseBody
    public PageListRes getEmployeeList(QueryVo vo){
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(employeeService.getEmployeeNum());
        pageListRes.setRows(employeeService.getEmployeeList(vo));
        return pageListRes;
    }

    /*
     *保存用户数据
     */
    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            employeeService.saveEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("操作成功");
        }
        catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("操作失败");
        }
        return ajaxRes;
    }

    /*
     *修改用户数据
     */
    @RequestMapping("/updateEmployee")
    @RequiresPermissions("employee:edit")
    @ResponseBody
    public AjaxRes updateEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("操作成功");
        }
        catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("操作失败");
        }
        return ajaxRes;
    }

    /*
     *离职
     */
    @RequestMapping("/updateState")
    @RequiresPermissions("employee:delete")
    @ResponseBody
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            employeeService.updateState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("操作成功");
        }
        catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("操作失败");
        }
        return ajaxRes;
    }


    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws Exception{
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if(methodAnnotation !=null){
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("你没有权限操作");
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(s);
        }else {
            response.sendRedirect("/404");
        }
    }



}
