package org.elasticsearch.service;

import org.elasticsearch.entity.Monitor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MonitorService
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 18:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MonitorService {
     List<Monitor> getMonitorAll(int from,int size);

     long getMonitorCount();

     Boolean deleteMonitors(List<String> ids);

     Monitor getMonitor(String id);

     Boolean updateMonitor(List<String> id, Map map);
}
