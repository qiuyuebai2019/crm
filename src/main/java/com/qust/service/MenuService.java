package com.qust.service;

import com.qust.domain.Menu;
import com.qust.domain.QueryVo;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 14时19分
 */

public interface MenuService {

//获取树形数据
    public List<Menu> getTreeData();

    public List<Menu> menuList(QueryVo queryVo);

    public Integer menuTotal();

    public List<Menu> menuNameList();

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);

    public String selectParentById(Long id);

    public void deleteMenu(Long id);

    public void updateMenuRel(Long id);
}
