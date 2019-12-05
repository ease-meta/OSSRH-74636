package com.open.cloud.es.service;

import com.open.cloud.es.entity.Monitor;

import java.util.List;
import java.util.Map;

public interface MonitorService {
     List<Monitor> getMonitorAll(int from, int size);

     long getMonitorCount();

     Boolean deleteMonitors(List<String> ids);

     Monitor getMonitor(String id);

     Boolean updateMonitor(List<String> id, Map map);
}
