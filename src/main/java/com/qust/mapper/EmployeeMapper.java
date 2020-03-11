package com.qust.mapper;

import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 13时10分
 */
public interface EmployeeMapper {

    /*
     *查询所有员工
     */
    public List<Employee> getEmployeeList(QueryVo vo);

    /*
     *添加员工总数
     */
    public Integer getEmployeeNum();

    /*
    *添加员工
    */
    public Integer saveEmployee(Employee employee);

    /*
     *添加员工角色
     */
    public Integer saveEmployeeRoleRel(@Param("eid") Long eid, @Param("rid") Long rid);


    /*
     *修改员工
     */
    public Integer updateEmployee(Employee employee);

    /*
     *员工离职
     */
    public Integer updateState(Long id);

    public Integer deleteEmployeeRoleRel(@Param("id") Long id);

    public Employee findByName(@Param("name") String name);

    public List<String> getRoleById(@Param("id") Long id);

    public List<String> getPermissionById(@Param("id") Long id);
}
