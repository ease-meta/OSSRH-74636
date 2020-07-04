#!/bin/sh
logserver=`ps -ef |grep eureka-3.0.1.jar | grep -v grep | awk '{print $2}'`
kill -9 $logserver
