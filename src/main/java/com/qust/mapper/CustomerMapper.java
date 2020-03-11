package com.qust.mapper;

import com.qust.domain.Customer;
import com.qust.domain.QueryVo;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月08日 09时13分
 */
public interface CustomerMapper {

    public Integer customerTotal();

    public List<Customer> customerList(QueryVo vo);

    public void saveCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void distribution(Customer customer);

    public List<Customer> visitTime(Customer customer);
}
