package com.qust.service.impl;

import com.qust.domain.Customer;
import com.qust.domain.Email;
import com.qust.domain.Employee;
import com.qust.domain.Systemlog;
import com.qust.mapper.CustomerMapper;
import com.qust.mapper.EmailMapper;
import com.qust.service.EmailService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月05日 10时55分
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Email> EmailMapper(Email id) {
        List<Email> emails = emailMapper.myEamil(id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Customer customer = new Customer();
        customer.setCusEmployee(id.getrId());
        List<Customer> list = customerMapper.visitTime(customer);
        for(Customer customer1 : list){
            if(df.format(customer1.getCusVisitTime()).equals(df.format(new Date()))){
                Email email = new Email();
                email.setsName("系统");
                email.setTitle("拜访提醒");
                email.setText("今天计划拜访"+customer1.getCusName());
                email.setCreateTime(new Date());
                emails.add(email);
            }
        }
        return emails;
    }

    @Override
    public void sendEmail(Email email) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        email.setsName(employee.getUsername());
        email.setIsRead((byte)0);
        email.setCreateTime(new Date(System.currentTimeMillis()));
        emailMapper.sendEamil(email);
    }

    @Override
    public void readEmail(Long id) {
        emailMapper.readEmail(id);
    }
}
