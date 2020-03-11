package com.qust.web;

import com.qust.domain.PageListRes;
import com.qust.domain.QueryVo;
import com.qust.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月13日 12时04分
 */
@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/log")
    public String log(){
        return "views/Log.html";
    }

    @RequestMapping("/logList")
    @ResponseBody
    public PageListRes logList(QueryVo vo){
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(logService.allLog());
        pageListRes.setRows(logService.Log(vo));
        return pageListRes;
    }
}
