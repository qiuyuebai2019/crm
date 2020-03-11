package com.qust.service.impl;

import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import com.qust.mapper.MenuMapper;
import com.qust.service.MenuService;
import com.qust.domain.Menu;
import java.util.Iterator;
import java.util.List;
import javax.sound.midi.SoundbankResource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 14时21分
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper mapper;


    @Override
    public List<Menu> getTreeData() {
        List<Menu> treeData = mapper.getTreeData();

        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        if(!employee.getAdmin()){
            checkPermission(treeData);
        }
        return treeData;
    }

    public void checkPermission(List<Menu> menus){
        Subject subject =SecurityUtils.getSubject();
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            if(menu.getPermission()!=null){
                String presource = menu.getPermission().getPresource();
                if(!subject.isPermitted(presource)){
                    iterator.remove();
                    continue;
                }
            }
            if(menu.getChildren().size()>0){
                checkPermission(menu.getChildren());
            }
        }
    }

    @Override
    public List<Menu> menuList(QueryVo vo) {
        vo.setStart((vo.getPage()-1)*vo.getRows());
        return mapper.menuList(vo);
    }

    @Override
    public Integer menuTotal() {
        return mapper.menuTotal();
    }

    @Override
    public List<Menu> menuNameList() {
        return mapper.menuNameList();
    }

    @Override
    public void saveMenu(Menu menu) {
        mapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        mapper.updateMenu(menu);
    }

    @Override
    public String selectParentById(Long id) {
        return mapper.selectParentById(id);
    }

    @Override
    public void deleteMenu(Long id) {
        mapper.deleteMenu(id);
    }

    @Override
    public void updateMenuRel(Long id) {
        mapper.updateMenuRel(id);
    }
}
