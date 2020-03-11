package com.qust.web;

import com.qust.domain.Permission;
import com.qust.service.PermissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时44分
 */
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permissionList")
    @ResponseBody
    public List<Permission> permissionList(){
        return permissionService.getPermissionList();
    }

    @RequestMapping("/getPermissionByRid")
    @ResponseBody
    public List<Permission> getPermissionByRid(Long rid){
        return permissionService.getPermissionByRid(rid);
    }
}
