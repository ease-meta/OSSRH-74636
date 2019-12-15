@echo off & setlocal enabledelayedexpansion

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i


cd ..\bin

if ""%1"" == ""jmx"" goto jmx

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ..\rules;..\conf;%LIB_JARS%  com.dcits.galaxy.AdapterApplication
goto end


:jmx
java -Dsun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath ..\conf;%LIB_JARS% com.dcits.galaxy.AdapterApplication
pause