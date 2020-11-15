#!/bin/bash --login
if [ ! -n "$1" ] ;then
    echo "Usage: start.sh [dev|sit|fat|dc01|dc02|qdc01|qdc02]"
    exit 1
else
    SPRING_PROFILES="-Dspring.profiles.active=$1"
fi
cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
LOGS_DIR=$DEPLOY_DIR/logs
GC_OPTS="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:${LOGS_DIR}/gc.log"
HEAP_DUMP_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOGS_DIR}/heapdump"
JAVA_OPTS+=" $GC_OPTS $HEAP_DUMP_OPTS "
JVM_OPTS="-XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn64m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC"
CONF_DIR=$DEPLOY_DIR/conf
SERVER_NAME=`cat $CONF_DIR/bootstrap.yml | grep -w "servicename:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`
DEBUG_ADDEESS=`cat $CONF_DIR/bootstrap.yml | grep -w "debugport:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`

if [ -z "$SERVER_NAME" ]; then
    ECHO_LOG "ERROR: The 'servicename' configuration is null in the bootstrap.yml file!" 3
    exit 1
fi

JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
	if [ -z "$DEBUG_ADDEESS" ]; then
		ECHO_LOG "ERROR: The 'debugport' configuration is null in the bootstrap.yml file!" 3
		exit 1
	fi
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$DEBUG_ADDEESS,server=y,suspend=n "
fi

PIDS=`ps -ef | grep java | grep $SERVER_NAME |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME alaredy started!"
    exit 1
fi

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi


if [ -n "$TRACE_CLOUD_DIR" -a -d "$TRACE_CLOUD_DIR"  -a -n "$TRACE_CLOUD_TRACE_KAFKA_ADDRESS" ]; then
    ######################cloud trace##########################
    TRACE_CLOUD_TRACE_KAFKA_TOPIC=myzipkin
    TRACE_CLOUD_TRACE_SENDER_TYPE=kafka
    TRACE_CLOUD_TRACE_PROBABILITY=1.0
    TRACE_CLOUD_TRACE_ENABLED=true
    #Parent dir of cloud-trace-deps-*
    TRACE_DIR=$TRACE_CLOUD_DIR
    TRACE_DIR=`ls -rd $TRACE_DIR/lib* | head -1`
    TRACE_LIB_DIR=$TRACE_DIR
    TRACE_LIB_JARS=`ls $TRACE_LIB_DIR|grep -v rabbit|grep .jar|awk '{print "'$TRACE_LIB_DIR'/"$0}'|tr "\n" ":"`
    echo "Using TRACE_LIB_JARS: $TRACE_LIB_JARS"
    if [ -n "$TRACE_CLOUD_TRACE_KAFKA_ADDRESS" ]; then
    TRACE_OPTS="-Dcloud.trace.kafka-address=$TRACE_CLOUD_TRACE_KAFKA_ADDRESS"
    TRACE_OPTS="$TRACE_OPTS -Dspring.kafka.bootstrap-servers=$TRACE_CLOUD_TRACE_KAFKA_ADDRESS"
    fi
    if [ -n "$TRACE_CLOUD_TRACE_KAFKA_TOPIC" ]; then
    TRACE_OPTS="$TRACE_OPTS -Dcloud.trace.kafka-topic=$TRACE_CLOUD_TRACE_KAFKA_TOPIC"
    fi
    if [ -n "$TRACE_CLOUD_TRACE_SENDER_TYPE" ]; then
    TRACE_OPTS="$TRACE_OPTS -Dcloud.trace.sender.type=$TRACE_CLOUD_TRACE_SENDER_TYPE"
    fi
    if [ -n "$TRACE_CLOUD_TRACE_PROBABILITY" ]; then
    TRACE_OPTS="$TRACE_OPTS -Dcloud.trace.probability=$TRACE_CLOUD_TRACE_PROBABILITY"
    fi
    if [ -n "$TRACE_CLOUD_TRACE_ENABLED" ]; then
    TRACE_OPTS="$TRACE_OPTS -Dcloud.trace.enabled=$TRACE_CLOUD_TRACE_ENABLED"
    fi
    echo "Using TRACE_OPTS: $TRACE_OPTS"
    JAVA_OPTS="$JAVA_OPTS $TRACE_OPTS"
    LIB_JARS="$LIB_JARS:$TRACE_LIB_JARS"
    ################################################
fi
echo -e "Starting $SERVER_NAME ...\c"
$JAVA_HOME/bin/java -DgalaxyApplicationName=$SERVER_NAME $SPRING_PROFILES $JAVA_DEBUG_OPTS $JVM_OPTS $JAVA_OPTS -classpath $CONF_DIR:$LIB_JARS com.dcits.aries.auth.AuthApplication &>$STDOUT_FILE &
#./bin/auth-crontab.sh

PIDS=`ps -f | grep java | grep "$SERVER_NAME" | awk '{print $2}'`
echo "."
echo "$SERVER_NAME is started!"
echo "Using CLASSPATH: $CLASSPATH"
echo "Using JAVA_HONE: $JAVA_HOME"
echo "Using JAVA_VERSION: `$JAVA_HOME/bin/java -version 2>&1 |awk '{a[NR]=$x} END{for(i=1;i<=NR;i++){printf(a[i]" ")}}'|awk '{print $10,$11,$3,$12}'`"
echo "Using JAVA_OPTS: $JAVA_OPTS"
echo "Using JVM_OPTS: $JVM_OPTS"
echo "Using DEPLOY_DIR: $DEPLOY_DIR"
echo "Using LOGS_DIR: $LOGS_DIR"
echo "Using CONF_DIR: $CONF_DIR"
echo "Using STDOUT: $STDOUT_FILE"
echo "PID: $PIDS"
