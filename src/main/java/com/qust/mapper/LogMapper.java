package com.qust.mapper;

import com.qust.domain.Log;
import com.qust.domain.QueryVo;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月13日 11时50分
 */
public interface LogMapper {

    public List<Log> Log(QueryVo vo);

    public Integer allLog();
}
