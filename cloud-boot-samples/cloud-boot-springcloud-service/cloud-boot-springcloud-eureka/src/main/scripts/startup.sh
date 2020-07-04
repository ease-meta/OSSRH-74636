#!/bin/bash

error_exit ()
{
    echo "ERROR: $1 !!"
    exit 1
}

JAVA_HOME="/home/tax/jdk1.8.0_191"
export JAVA_HOME
export PATH=$PATH:JAVA_HOME/bin
echo JAVA_HOME:${JAVA_HOME}
${JAVA_HOME}/bin/java -version

export JAVA="$JAVA_HOME/bin/java"
export BASE_DIR=`cd $(dirname $0)/..; pwd`
export DEFAULT_SEARCH_LOCATIONS="classpath:/,classpath:/config/,file:./,file:./config/"
export CUSTOM_SEARCH_LOCATIONS=file:${BASE_DIR}/config/

export MODE=""
while getopts ":m:" opt
do
    case $opt in
        m)
        MODE=$OPTARG
        ;;
        ?)
        echo "Unknown parameter"
        exit 1;;
    esac
done

if [[ "${MODE}" == "debug" ]]; then
    JAVA_OPT="%JAVA_OPT% -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi
if [[ "${MODE}" == "jmx" ]]; then
    JAVA_OPT="%JAVA_OPT%  -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

#===========================================================================================
# JVM Configuration
#===========================================================================================
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
JAVA_OPT="${JAVA_OPT} -XX:AutoBoxCacheMax=20000 -XX:+AlwaysPreTouch -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:NewRatio=1"

JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"
JAVA_OPT="${JAVA_OPT} -XX:-UseLargePages"

JAVA_OPT="${JAVA_OPT} -Djava.ext.dirs=${JAVA_HOME}/jre/lib/ext:${JAVA_HOME}/lib/ext:${BASE_DIR}/lib"
JAVA_OPT="${JAVA_OPT} -Xloggc:${BASE_DIR}/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

JAVA_OPT="${JAVA_OPT} -Dapp.home=${BASE_DIR}"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/boot/branch-workflow.jar"
JAVA_OPT="${JAVA_OPT} ${JAVA_OPT_EXT}"
JAVA_OPT="${JAVA_OPT} --spring.config.location=${CUSTOM_SEARCH_LOCATIONS}"

if [ ! -d "${BASE_DIR}/logs" ]; then
  mkdir ${BASE_DIR}/logs
fi

echo "$JAVA ${JAVA_OPT}"

$JAVA ${JAVA_OPT}