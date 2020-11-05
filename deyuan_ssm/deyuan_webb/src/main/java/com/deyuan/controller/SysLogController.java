package com.deyuan.controller;

import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/11/3
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll")
    public ModelAndView findAllSysLog(){
        ModelAndView modelAndView=new ModelAndView();
        List<SysLog> all = syslogService.findAll();
        modelAndView.addObject("sysLogs",all);
        modelAndView.setViewName("syslog-list");
        return modelAndView;

    }

}
