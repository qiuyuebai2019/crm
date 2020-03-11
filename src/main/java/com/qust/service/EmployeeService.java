package com.qust.service;

import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 13时25分
 */
public interface EmployeeService {

    /*
     *获取员工列表数据
     */
    public List<Employee> getEmployeeList(QueryVo vo);

    /*
     *获取员工列表总数
     */
    public Integer getEmployeeNum();

    /*
     *添加员工
     */
    public void saveEmployee(Employee employee);

    /*
     *修改员工
     */
    public void updateEmployee(Employee employee);

    /*
     *员工离职
     */
    public void updateState(Long id);

    /*
     *根据名字获得用户信息
     */
    public Employee findEmloyeeByName(String name);

    /*
     *根据id获得用户角色
     */
    public List<String> getRoleById(Long id);

    /*
     *根据id获得用户权限
     */
    public List<String> getPermissionById(Long id);
}
