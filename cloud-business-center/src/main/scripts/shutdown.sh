#!/bin/sh

pid=`ps ax | grep -i 'branch-workflow' |grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "No branch-workflow running."
        exit -1;
fi

echo "The branch-workflow(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to branch-workflow(${pid}) OK"
