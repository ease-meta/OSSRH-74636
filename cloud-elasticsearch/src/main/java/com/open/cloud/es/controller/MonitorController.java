package com.open.cloud.es.controller;

import com.open.cloud.es.entity.Monitor;
import com.open.cloud.es.service.impl.MonitorServiceImpl;
import com.open.cloud.es.util.ResultModel;
import com.open.cloud.es.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MonitorController {
    private  static  final  int SUCCESS = 0;
    @Autowired
    MonitorServiceImpl monitorService;

    @RequestMapping(value = "/monitor",method = RequestMethod.GET)
    public ResultModel getMonitors(int page, int limit){
        int from = (page-1)*limit;
        int size = limit;
        List<Monitor> monitors =  monitorService.getMonitorAll(from,limit);
        long count = monitorService.getMonitorCount();
        return ResultUtil.success(SUCCESS,"",count,monitors);
    }
    @RequestMapping(value = "/update/reload/monitor",method = RequestMethod.GET)
    public ResultModel updateReloadMonitors(int page, int limit , @RequestParam("ids[]") List<String> ids){
        for(String id :ids){
          while (!monitorService.getMonitor(id).getRetCode().equals("000000")){
              log.debug("更新检查："+id);
          }
        }
        int from = (page-1)*limit;
        int size = limit;
        List<Monitor> monitors =  monitorService.getMonitorAll(from,limit);
        long count = monitorService.getMonitorCount();
        return ResultUtil.success(SUCCESS,"",count,monitors);
    }
    @RequestMapping(value = "/delete/reload/monitor",method = RequestMethod.GET)
    public ResultModel deleteReloadMonitors(int page, int limit , @RequestParam("ids[]") List<String> ids){
        for(String id :ids){
            while (monitorService.getMonitor(id)!=null){
                log.debug("删除成功检查："+id);
            }
        }
        int from = (page-1)*limit;
        int size = limit;
        List<Monitor> monitors =  monitorService.getMonitorAll(from,limit);
        long count = monitorService.getMonitorCount();
        return ResultUtil.success(SUCCESS,"",count,monitors);
    }
    @RequestMapping(value = "/monitor",method = RequestMethod.PUT)
    public ResultModel updeteMonitors(@RequestBody List<String> ids){
        Map<String,Object> map = new HashMap<>();
        map.put("retCode","000000");
        map.put("handle","已处理");
        map.put("@version","2");
        return ResultUtil.success(SUCCESS,"处理成功！",0,monitorService.updateMonitor(ids,map));
    }

    @RequestMapping(value = "/monitor",method = RequestMethod.DELETE)
    public ResultModel deleteMonitors(@RequestBody List<String> ids){
        return ResultUtil.success(SUCCESS,"删除成功！",0,monitorService.deleteMonitors(ids));
    }
}
