package com.qust.service.impl;

import com.qust.domain.Permission;
import com.qust.domain.QueryVo;
import com.qust.domain.Role;
import com.qust.mapper.RoleMapper;
import com.qust.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时16分
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole(QueryVo vo) {
        return roleMapper.getRole(vo);
    }

    @Override
    public Integer getRoleNum() {
        return roleMapper.getRoleNum();
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.saveRole(role);
        for(Permission permission : role.getPermissions()) {
            roleMapper.saveRolePermissionRes(role.getRid(),permission.getPid());
        }
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.deleteRolePermissionRes(role);
        roleMapper.updateRole(role);
        for(Permission permission : role.getPermissions()) {
            roleMapper.saveRolePermissionRes(role.getRid(),permission.getPid());
        }
    }

    @Override
    public void deleteRole(Long id) {
        Role role = new Role();
        role.setRid(id);
        roleMapper.deleteRolePermissionRes(role);
        roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.roleList();
    }

    @Override
    public List<Long> getRoleByEid(Long id) {
        return roleMapper.getRoleByEid(id);
    }
}
