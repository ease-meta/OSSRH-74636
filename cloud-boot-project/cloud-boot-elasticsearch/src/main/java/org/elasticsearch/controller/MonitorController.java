package org.elasticsearch.controller;

import org.elasticsearch.entity.Monitor;
import org.elasticsearch.service.impl.MonitorServiceImpl;
import org.elasticsearch.util.ResultModel;
import org.elasticsearch.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MonitorController
 * @Description: 异常信息查询，修改，删除
 * @Author: liyuq
 * @CreateDate: 2019/9/4 18:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
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
