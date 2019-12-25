#基础镜像
FROM primetoninc/jdk:1.8

ENV APP_HOME /home/apps/ensemble-cif/

ADD ensemble-cif-service.jar $APP_HOME/ensemble-cif-service.jar

ADD config/* $APP_HOME/config/

ADD lib/* $APP_HOME/lib/

ADD *.sh $APP_HOME/


EXPOSE 9010
#ENTRYPOINT ["java","-jar","/home/apps/ensemble-cif/ensemble-cif-service.jar"]
# 通过脚本启动
ENTRYPOINT ["/home/apps/ensemble-cif/startService.sh"]
