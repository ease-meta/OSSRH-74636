# open-cloud-platform

#### 介绍
基于SpringBoot2.1.2的后台系统。

技术选择spring、mybatis、shiro、ehcache、redis、jpa等。集成swagger2框架支持在线快速测试，同时采用swagger2markup和asciidoctor生成Restful API文档，通过自定义注解@Resource和shiro实现接口的鉴权管理。

后续计划加入spring cloud alibaba实现分布式服务注册和限流，采用nacos实现配置文件的实时更新，数据库操作由mybatis替换为jpa操作。

前端采用easyweb，感谢[https://github.com/github/pages-gem](http://)。

分布式网关、日志采集及查看
#### 软件架构
软件架构说明
1. zookeeper集群
2. redis集群
3. nacos集群
4. es集群
5. jenkins自动化部署
6. 服务监控
    cloud-config-center:8083
    cloud-register-center
        cloud-eureka-server:8761
    cloud-monitor-center:8084
    cloud-workflow-center:8080
    cloud-base-java:无

#### 安装教程
. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)