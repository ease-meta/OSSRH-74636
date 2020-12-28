#!/bin/bash

#======================================================================
# 通过项目名称查找到PID
# 然后kill -9 pid
#======================================================================

# bin目录绝对路径
BIN_PATH=$(cd `dirname $0`; pwd)
# 进入bin目录
cd `dirname $0`
# 返回到上一级项目根目录路径
cd ..
# 打印项目根目录绝对路径
# `pwd` 执行系统命令并获得结果
BASE_PATH=`pwd`
# 获取根目录下以.jar结尾的文件，该文件为主jar
files=$(ls $BASE_PATH)
for filename in $files
do
 # 匹配以.jar结尾的文件
 if [[ $filename =~ \.jar$ ]];then
    APPLICATION_JAR=$filename
 fi
done
# 如果主jar为空，则退出
if [[ -z $APPLICATION_JAR ]];
then
    echo "APPLICATION_JAR is empty,Please check the package structure!!!"
    exit;
fi
PID=`ps -ef|grep $APPLICATION_JAR|grep -v grep|awk '{print $2}' `
#如果不存在执行启动
if [ -z "${PID}" ]; then
    echo ${APPLICATION_JAR} is already stopped
else
    echo kill  ${PID}
    kill ${PID}
    echo ${APPLICATION_JAR} stopped successfully
fi
