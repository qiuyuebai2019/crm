package com.qust.service;

import com.qust.domain.Permission;
import com.qust.mapper.PermissionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时45分
 */
public interface PermissionService {


    public List<Permission> getPermissionList();

    public List<Permission> getPermissionByRid(Long id);

}
