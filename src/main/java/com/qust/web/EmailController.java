package com.qust.web;

import com.qust.domain.AjaxRes;
import com.qust.domain.Email;
import com.qust.domain.Employee;
import com.qust.domain.QueryVo;
import com.qust.service.EmailService;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月09日 12时23分
 */
@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/email")
    public String email(){
        return "views/email.html";
    }

    @RequestMapping("/myEmail")
    @ResponseBody
    public List<Email> emailList(Email email){
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        email.setrId(employee.getId());
        if(email.getIsRead()==null){
            email.setIsRead((byte)0);
        }
        List<Email> emails =emailService.EmailMapper(email);
        return emails;
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public AjaxRes sendEmail(Email email){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            emailService.sendEmail(email);
            ajaxRes.setSuccess(true);
            ajaxRes.setMes("发送成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("发送失败");
        }
        return ajaxRes;
    }

    @RequestMapping("/readEmail")
    @ResponseBody
    public AjaxRes readEmail(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try{
            emailService.readEmail(id);
            ajaxRes.setMes("操作成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMes("操作失败");
        }
        return ajaxRes;
    }
}
