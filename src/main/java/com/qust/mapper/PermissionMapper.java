package com.qust.mapper;

import com.qust.domain.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 22时00分
 */
public interface PermissionMapper {

    public List<Permission> getPermissionList();

    public List<Permission> getPermissionByRid(@Param("id") Long id);
}
