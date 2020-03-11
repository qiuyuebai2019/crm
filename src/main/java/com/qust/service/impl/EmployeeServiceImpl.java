package com.qust.service.impl;

import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import com.qust.domain.Role;
import com.qust.mapper.EmployeeMapper;
import com.qust.service.EmployeeService;
import java.util.List;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 13时26分
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployeeList(QueryVo vo) {
        vo.setStart((vo.getPage()-1)*vo.getRows());
        return employeeMapper.getEmployeeList(vo);
    }

    @Override
    public Integer getEmployeeNum() {
        return employeeMapper.getEmployeeNum();
    }

    @Override
    public void saveEmployee(Employee employee) {

//        密码加密
        Md5Hash md5Hash = new Md5Hash(employee.getPassword(),employee.getUsername(),2);
        employee.setPassword(md5Hash.toString());

        employee.setState(true);
        employeeMapper.saveEmployee(employee);
        for(Role role : employee.getRoles()){
            employeeMapper.saveEmployeeRoleRel(employee.getId(),role.getRid());
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.deleteEmployeeRoleRel(employee.getId());
        employeeMapper.updateEmployee(employee);
        for(Role role : employee.getRoles()){
            employeeMapper.saveEmployeeRoleRel(employee.getId(),role.getRid());
        }
    }

    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

    @Override
    public Employee findEmloyeeByName(String name) {
        return employeeMapper.findByName(name);
    }

    @Override
    public List<String> getRoleById(Long id) {
        return employeeMapper.getRoleById(id);
    }

    @Override
    public List<String> getPermissionById(Long id) {
        return employeeMapper.getPermissionById(id);
    }

}
