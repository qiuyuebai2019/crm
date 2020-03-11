package com.qust.service.impl;

import com.qust.domain.Log;
import com.qust.domain.QueryVo;
import com.qust.mapper.LogMapper;
import com.qust.service.LogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月13日 12时02分
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> Log(QueryVo vo) {
        vo.setStart((vo.getPage()-1)*vo.getRows());
        return logMapper.Log(vo);
    }

    @Override
    public Integer allLog() {
        return logMapper.allLog();
    }
}
