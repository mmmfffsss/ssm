package com.deyuan.service.Impl;

import com.deyuan.dao.SysLogDao;
import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/11/3
 */
@Service
public class ISyslogServiceImpl implements ISyslogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);

    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
