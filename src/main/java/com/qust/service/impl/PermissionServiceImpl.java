package com.qust.service.impl;

import com.qust.domain.Permission;
import com.qust.mapper.PermissionMapper;
import com.qust.service.PermissionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时47分
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionList() {
        return permissionMapper.getPermissionList();
    }

    @Override
    public List<Permission> getPermissionByRid(Long id) {
        return permissionMapper.getPermissionByRid(id);
    }

}
