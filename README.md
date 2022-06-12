# open-cloud-platform

#### 案例
将 yudao-module-system-api 模块的 auth 包，重构成 oauth2 包 YunaiV Yesterday 16:44 bdf579a2
* 2022年6月3号 整合https://gitee.com/zhijiantianya/ruoyi-vue-pro 工作流
* npm install --registry=https://registry.npm.taobao.org
* 2020年4月19号 整合人人开源的代码生成
* http://localhost:28082/hystrix
* http://localhost:28082/hystrix.stream
  CommonDbSchemaManager

#### 介绍

| 技术栈             | 版本号        |
| ------------------ | ------------- |
| SpringBoot         | 2.2.6.RELEASE |
| springCloud        | Hoxton.SR4    |
| dubbo              | 2.7.6         |
| springCloudAlibaba | 2.2.1.RELEASE |
| sofaBootEnterprise | 3.3.2         |
| springCloudHuawei  | 1.2.0         |
| seata              |               |
| com.alibaba.cloud  |               |
|                    |               |

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
5. feat：新功能（feature）
    * fix：修补bug
    * docs：文档（documentation）
    * style： 格式（不影响代码运行的变动）
    * refactor：重构（即不是新增功能，也不是修改bug的代码变动）
    * test：增加测试
    * chore：构建过程或辅助工具的变动
    * tag: 针对与每次版本的提交
    * revert: 撤销，版本回退
    * perf: 性能优化

* Commit message规范在rrd-fe落地使用情况 针对团队目前使用的情况，我们讨论后拟定了commit message每一部分的填写规则。

1. type type为必填项，用于指定commit的类型，约定了feat、fix两个主要type，以及docs、style、build、refactor、revert五个特殊type，其余type暂不使用。

# 主要type

feat:     增加新功能 fix:      修复bug

# 特殊type

docs:     只改动了文档相关的内容 style:    不影响代码含义的改动，例如去掉空格、改变缩进、增删分号 build:    构造工具的或者外部依赖的改动，例如webpack，npm refactor: 代码重构时使用
revert:   执行git revert打印的message

# 暂不使用type

test:     添加测试或者修改现有测试 perf:     提高性能的改动 ci:       与CI（持续集成服务）有关的改动 chore:    不修改src或者test的其余修改，例如构建过程或辅助工具的变动

当一次改动包括主要type与特殊type时，统一采用主要type。

2. scope scope也为必填项，用于描述改动的范围，格式为项目名/模块名，例如：node-pc/common
   rrd-h5/activity，而we-sdk不需指定模块名。如果一次commit修改多个模块，建议拆分成多次commit，以便更好追踪和维护。

3. body body填写详细描述，主要描述改动之前的情况及修改动机，对于小的修改不作要求，但是重大需求、更新等必须添加body来作说明。

4. break changes break changes指明是否产生了破坏性修改，涉及break changes的改动必须指明该项，类似版本升级、接口参数减少、接口删除、迁移等。

5. affect issues affect issues指明是否影响了某个问题。例如我们使用jira时，我们在commit
   message中可以填写其影响的JIRA_ID，若要开启该功能需要先打通jira与gitlab。参考文档：docs.gitlab.com/ee/user/pro…

填写方式例如：

re #JIRA_ID fix #JIRA_ID

示例 完整的commit message示例

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