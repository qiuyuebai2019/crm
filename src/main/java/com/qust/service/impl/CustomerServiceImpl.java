package com.qust.service.impl;

import com.qust.domain.Customer;
import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import com.qust.mapper.CustomerMapper;
import com.qust.mapper.RoleMapper;
import com.qust.service.CustomerService;
import java.util.Iterator;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月08日 09时42分
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer customerTotal() {
        return customerMapper.customerTotal();
    }

    @Override
    public List<Customer> customerList(QueryVo vo) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        vo.setStart((vo.getPage()-1)*vo.getRows());
        List<Customer> customers = customerMapper.customerList(vo);
        List<Long> rid = roleMapper.getRoleByEid(employee.getId());
        Long r = rid.get(0);
        if(r==3){
            Iterator<Customer> iterator = customers.iterator();
            while (iterator.hasNext()){
                Customer customer = iterator.next();
                if(customer.getCusEmployee()==null){
                    iterator.remove();
                }
                else if(!customer.getCusEmployee().equals(employee.getId())){
                    iterator.remove();
                }
            }
        }
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerMapper.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

    @Override
    public void distribution(Customer customer) {
        customerMapper.distribution(customer);
    }
}
