package com.qust.service;

import com.qust.domain.Email;
import java.util.Date;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月05日 10时54分
 */
public interface EmailService {

    public List<Email> EmailMapper(Email email);

    public void sendEmail(Email email);

    public void readEmail(Long id);
}
