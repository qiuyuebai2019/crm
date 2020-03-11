package com.qust.web;

import com.qust.domain.AjaxRes;
import com.qust.domain.PageListRes;
import com.qust.domain.QueryVo;
import com.qust.domain.Role;
import com.qust.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 18时40分
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String role(){
        return "views/role.html";
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public PageListRes getRole(QueryVo vo){
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(roleService.getRoleNum());
        pageListRes.setRows(roleService.getRole(vo));
        return pageListRes;
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public AjaxRes saveRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.saveRole(role);
            ajaxRes.setMes("操作成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("操作失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxRes updateRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            roleService.updateRole(role);
            ajaxRes.setMes("操作成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("操作失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxRes deleteRole(Long rid){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.deleteRole(rid);
            ajaxRes.setMes("操作成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("操作失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList(){
        return roleService.roleList();
    }

    @RequestMapping("/getRoleByEid")
    @ResponseBody
    public List<Long> getRoleByEid(Long id){
        return roleService.getRoleByEid(id);
    }
}
