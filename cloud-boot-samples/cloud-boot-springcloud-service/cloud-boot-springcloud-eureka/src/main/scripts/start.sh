#!/bin/bash

env_config=$1
if [[ -z $env_config ]];then
    echo "第一个启动参数不能为空"
    exit
fi
#Jenkins会在构建完成后不使用processTreeKiller杀掉了所有子进程
export BUILD_ID=dontKillMe
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
BASE_DIR=`pwd`
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

ENV_CONFIG=$1
echo "使用环境为：$1"
#==========================================================================================
# JVM Configuration
# -Xmx256m:设置JVM最大可用内存为256m,根据项目实际情况而定，建议最小和最大设置成一样。
# -Xms256m:设置JVM初始内存。此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存
# -Xmn512m:设置年轻代大小为512m。整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小。
#          持久代一般固定大小为64m,所以增大年轻代,将会减小年老代大小。此值对系统性能影响较大,Sun官方推荐配置为整个堆的3/8
# -XX:MetaspaceSize=64m:存储class的内存大小,该值越大触发Metaspace GC的时机就越晚
# -XX:MaxMetaspaceSize=320m:限制Metaspace增长的上限，防止因为某些情况导致Metaspace无限的使用本地内存，影响到其他程序
# -XX:-OmitStackTraceInFastThrow:解决重复异常不打印堆栈信息问题
#==========================================================================================
# 项目日志输出绝对路径
echo "应用部署路径为：$BASE_PATH"
APPLICATION_NAME=${BASE_DIR##*/}
LOG_DIR=/home/app/logs/${APPLICATION_NAME}/logs
LOG_FILE="stdout.log"
LOG_PATH="${LOG_DIR}/${LOG_FILE}"

# 如果logs文件夹不存在,则创建文件夹
if [[ ! -d "${LOG_DIR}" ]]; then
  mkdir -p "${LOG_DIR}"
fi

JAVA_VERSION=`java -fullversion 2>&1 | awk -F[\"\.] '{print $2$3$4}' |awk -F"_" '{print $1}'`
echo $JAVA_VERSION

JAVA_OPT="-Djava.awt.headless=true -Djava.net.preferIPv4Stack=true"
JAVA_OPT="${JAVA_OPT} -server -Xms128m -Xmx128m -Xmn128m -Xss512k -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -XX:LargePageSizeInBytes=128m "
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow"
JAVA_MEM_OPTS="${JAVA_OPT} -XX:+DisableExplicitGC -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=16m -XX:G1ReservePercent=25 -XX:InitiatingHeapOccupancyPercent=30 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:SurvivorRatio=8 -XX:G1ReservePercent=15 "
JAVA_DEBUG_OPTS=""
if [ "$2" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_JMX_OPTS=""
if [ "$2" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

GC_OPT="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:${LOG_DIR}/gc.log"
HEAP_DUMP_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/heapdump"

#检查程序是否在运行
pid=`ps -ef|grep $APPLICATION_JAR|grep -v grep|awk '{print $2}' `
#如果不存在执行启动
if [ -z "${pid}" ]; then
nohup java ${JAVA_MEM_OPTS} ${JAVA_DEBUG_OPTS} ${JAVA_JMX_OPTS} ${GC_OPT} ${HEAP_DUMP_OPTS} -jar  -Dspring.profiles.active=$ENV_CONFIG  ${BASE_PATH}/${APPLICATION_JAR} > ${LOG_PATH} 2>&1 &
# 进程ID
PID=$(ps -ef | grep "${APPLICATION_JAR}" | grep -v grep | awk '{ print $2 }')

echo "Using DEPLOY_DIR: $BASE_DIR"
echo "Using LOGS_DIR: $LOG_DIR"

echo "========================${APPLICATION_JAR} is running , pid: ${PID}==========="
# 打印项目日志
#tail -f ${LOG_PATH}
else
  echo "${APPLICATION_JAR} is already running. pid=${pid} ."
fi
