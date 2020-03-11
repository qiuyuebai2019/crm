package com.qust.web;

import com.qust.domain.AjaxRes;
import com.qust.domain.PageListRes;
import com.qust.domain.QueryVo;
import com.qust.service.MenuService;
import com.qust.domain.Menu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 14时16分
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping("/menu")
    public String role(){
        return "views/menu.html";
    }

       //    获取树形菜单数据
    @RequestMapping("/getTreeData")
    @ResponseBody
    public List<Menu> getTreeData(){
        return menuService.getTreeData();
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public PageListRes menuList(QueryVo queryVo){
        PageListRes pageListRes = new PageListRes();
        pageListRes.setRows(menuService.menuList(queryVo));
        pageListRes.setTotal(menuService.menuTotal());
        return pageListRes;
    }

    @RequestMapping("/menuNameList")
    @ResponseBody
    public List<Menu> menuNameList(){
        return menuService.menuNameList();
    }

    @RequestMapping("/saveMenu")
    @ResponseBody
    public AjaxRes saveMenu(Menu menu){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            menuService.saveMenu(menu);
            ajaxRes.setMes("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxRes updateMenu(Menu menu){
        AjaxRes ajaxRes = new AjaxRes();
        if(menu.getId().equals(menuService.selectParentById(menu.getParent().getId()))){
            ajaxRes.setMes("不能将自己设置为父菜单");
            ajaxRes.setSuccess(false);
            return ajaxRes;
        }
        try{
            menuService.updateMenu(menu);
            ajaxRes.setMes("修改成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMes("修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            menuService.updateMenuRel(id);
            menuService.deleteMenu(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("删除成功");
        }catch(Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("删除失败");
        }
        return ajaxRes;
    }
}
