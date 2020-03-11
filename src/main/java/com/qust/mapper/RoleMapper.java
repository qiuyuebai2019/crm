package com.qust.mapper;

import com.qust.domain.QueryVo;
import com.qust.domain.Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 21时57分
 */
public interface RoleMapper {

    public List<Role> getRole(QueryVo vo);

    public Integer getRoleNum();

    public Integer saveRole(Role role);

    public Integer saveRolePermissionRes(@Param("rid") Long rid,@Param("pid") Long pid);

    public Integer updateRole(Role role);

    public Integer deleteRolePermissionRes(Role role);

    public Integer deleteRole(@Param("rid") Long rid);

    public List<Role> roleList();

    public List<Long> getRoleByEid(@Param("id") Long id);
}
