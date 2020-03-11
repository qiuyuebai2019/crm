package com.qust.mapper;

import com.qust.domain.Menu;
import com.qust.domain.QueryVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 14时22分
 */
public interface MenuMapper {

    public List<Menu> getTreeData();

    public List<Menu> menuList(QueryVo queryVo);

    public Integer menuTotal();

    public List<Menu> menuNameList();

    public void saveMenu(Menu menu);

    public void updateMenu(Menu menu);

    public String selectParentById(@Param("id") Long id);

    public void deleteMenu(@Param("id") Long id);

    public void updateMenuRel(@Param("id") Long id);
}
