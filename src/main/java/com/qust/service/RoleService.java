package com.qust.service;

import com.qust.domain.QueryVo;
import com.qust.domain.Role;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时15分
 */
public interface RoleService {

    public List<Role> getRole(QueryVo vo);

    public Integer getRoleNum();

    public void saveRole(Role role);

    public void updateRole(Role role);

    public void deleteRole(Long id);

    public List<Role> roleList();

    public List<Long> getRoleByEid(Long id);
}
