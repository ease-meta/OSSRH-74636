@echo off

if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
set "JAVA=%JAVA_HOME%\bin\java.exe"

setlocal

set BASE_DIR=%~dp0
set BASE_DIR=%BASE_DIR:~0,-1%
for %%d in (%BASE_DIR%) do set BASE_DIR=%%~dpd

echo %BASE_DIR%

set DEFAULT_SEARCH_LOCATIONS="classpath:/,classpath:/config/,file:./,file:./config/"
set CUSTOM_SEARCH_LOCATIONS=file:%BASE_DIR%config/

echo %CUSTOM_SEARCH_LOCATIONS%

if  "%2" == "debug" set "JAVA_OPT=%JAVA_OPT% -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "

if  "%2" == "jmx"   set "JAVA_OPT=%JAVA_OPT%  -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "

set "JAVA_OPT=%JAVA_OPT% -server -Xms512m -Xmx512m -Xmn512m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
set "JAVA_OPT=%JAVA_OPT% -XX:ErrorFile=%BASE_DIR%\logs\hs_err_pid%p.log -Xloggc:%BASE_DIR%\logs\gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=102400 -XX:HeapDumpPath=%BASE_DIR%\logs -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError"

set "JAVA_OPT=%JAVA_OPT% -Xbootclasspath/a:%BASE_DIR%\lib"
set "JAVA_OPT=%JAVA_OPT% -Dapp.home=%BASE_DIR%"
set "JAVA_OPT=%JAVA_OPT% -jar %BASE_DIR%\boot\branch-workflow.jar"
set "JAVA_OPT=%JAVA_OPT% --spring.config.location="%CUSTOM_SEARCH_LOCATIONS%""

call "%JAVA%" %JAVA_OPT% %*

pause
