@echo off
java  -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8092,server=y,suspend=n -jar cloud-test-executable.jar
pause
