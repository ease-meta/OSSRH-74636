# open-cloud-platform
#### 案例
* 2020年4月19号 整合人人开源的代码生成
* http://localhost:28082/hystrix
* http://localhost:28082/hystrix.stream
#### 介绍

​		个人学习的案例。整体融合了SpringBoot、SpringCloud、Dubbo、springCloudAlibaba、Sofa、 分布式事务解决方案（AT、TCC、SAGA）、分布式序列服务、集成了kafka和RocketMq、基于SpringSecurity、shiro的权限认证、Redis权限管理、Linux系统资源监控、应用系统的资源监控、  分布式链路跟踪、分布式序列服务及分布式锁的实现。

| 技术栈             | 版本号        |      
| ------------------ | ------------- | 
| SpringBoot         | 2.2.6.RELEASE |      
| springCloud        | Hoxton.SR4    |      
| dubbo              | 2.7.6         |      
| springCloudAlibaba | 2.2.1.RELEASE |      
| sofaBootEnterprise | 3.3.2         |      
| springCloudHuawei  | 1.2.0         |      

#### 软件架构

#### 安装教程

#### 使用说明

#### 环境说明

| 描述信息 | 应用信息 | 端口 | 地址 |
| --- | ---| --- | --- |
| cloud-auth | cloud-auth | 9001  |  |
| EurekaServer1 | EurekaServer | 8001  |  |
| EurekaServer2 | EurekaServer | 8002  |  |
| EurekaServer3 | EurekaServer | 8003  |  |
| gateway | spring-cloud-gateway | 9002  |  |
| zuul-gateway | zuul-gateway | 9001  |  |
| zipkin | zipkin | 9411  |122.51.108.224  |
| nacos | nacos | 8848  |122.51.108.224  |
| NacosApplication | NacosApplication | 6001  |122.51.108.224  |
| eureka | eureka | 8001  | 122.51.108.224 |
| redis | redis | 7369  | 122.51.108.224 |
| mysql | mysql | 3306  | 122.51.108.224 |
| MongoDB |  MongoDB | 27017  | 122.51.108.224 |
| rocketMQ | rocketMQ | 8989  |  |



#### 参与贡献

1. 
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


### 版本号规范

先行版本号（Pre-Release）意味该版本不稳定，可能存在兼容问题格式为x.y.z.[a-c][整数]。

常见的修饰符

Alpha:内部版

DEV:开发版本

Beta:测试版

Demo:演示版

Enhance:增强版

Full Version：完整版（正式版）

Final：最终版（正式版）

SR：修正版

Trial：试用版

RC：即将作为正式版发布

Spring命名规则

1.Release 版本则代表稳定的版本

2.GA版本则代表可用的稳定版

3.M版中则代表里程碑版具有一些全新的功能或是具有里程碑意义的版本