package com.qust.mapper;

import com.qust.domain.Email;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月05日 10时56分
 */
public interface EmailMapper {

    public List<Email> myEamil(Email id);

    public void sendEamil(Email email);

    public void readEmail(@Param("id") Long id);
}
