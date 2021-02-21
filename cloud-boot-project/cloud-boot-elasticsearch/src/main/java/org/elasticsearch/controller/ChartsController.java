package org.elasticsearch.controller;

import org.elasticsearch.service.impl.ChartsServiceImpl;
import org.elasticsearch.util.ResultModel;
import org.elasticsearch.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class ChartsController {
    private  static  final  int SUCCESS = 0;
    @Autowired
    ChartsServiceImpl chartsService;
    @RequestMapping(value = "/group/date",method = RequestMethod.GET)
    public ResultModel deleteMonitors(){
        Map map = chartsService.dateCountGroupBy();
        return ResultUtil.success(SUCCESS,"请求成功!",map.size(),map);
    }
}
