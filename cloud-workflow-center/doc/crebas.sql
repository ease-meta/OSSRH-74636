/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/1/10 0:28:04                            */
/*==============================================================*/
drop table if exists  dc_act_re_deployment;

drop table if exists  dc_act_sys_resource;

drop table if exists dc_act_ge_bytearray;

drop table if exists dc_act_assignee;

drop table if exists dc_act_hi_actinst;

drop table if exists dc_act_hi_comment;

drop table if exists dc_act_hi_procinst;

drop table if exists dc_act_hi_reprocdef;

drop table if exists dc_act_re_procdef;

drop table if exists dc_act_ru_execution;

drop table if exists dc_act_ru_identitylink;

drop table if exists dc_act_ru_task;

drop table if exists dc_act_ru_variable;

drop table if exists dc_act_reprocdef_flow;

drop table if exists dc_act_reprocdef_flowlines;

drop table if exists dc_act_sys_log;

drop table if exists dc_act_sys_permission;

drop table if exists dc_act_sys_role;

drop table if exists dc_act_sys_role_permission;

drop table if exists dc_act_sys_user;

drop table if exists dc_act_sys_user_role;

drop table if exists `dc_act_sys_job`;

drop table if exists `dc_act_act_re_deployment`;

DROP TABLE  if exists `dc_act_seq_no_para`;

DROP TABLE  if exists `dc_act_token`;

CREATE TABLE `dc_act_token` (
  `UUID` varchar(64) NOT NULL,
  `TOKEN` varchar(255)  NOT NULL,
  `TIME` datetime NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `dc_act_sys_resource` (
  `ID` varchar(320) NOT NULL,
  `RESOURCE_NAME` varchar(32) NOT NULL COMMENT '资源名称',
  `MAPPING` varchar(64) NOT NULL COMMENT '路径映射',
  `METHOD` varchar(6) NOT NULL COMMENT '请求方式',
  `AUTH_TYPE` varchar(2) NOT NULL COMMENT '权限认证类型',
  `PERM` varchar(68) NOT NULL COMMENT '权限标识',
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`MAPPING`,`METHOD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='资源表';


CREATE TABLE  dc_act_seq_no_para
(
  SEQ_TYPE   VARCHAR(30 )  ,
  MIN_SEQ    VARCHAR(30 ) NOT NULL,
  MAX_SEQ    VARCHAR(30 ) NOT NULL,
  CURR_SEQ   VARCHAR(30 ) NOT NULL,
  COUNT_SEQ  VARCHAR(30) NOT NULL,
  PRIMARY KEY (`SEQ_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `dc_act_re_deployment` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME` timestamp(3) NULL DEFAULT NULL,
  `ENGINE_VERSION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE `dc_act_ge_bytearray` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV` int(11) DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES` longblob,
  `GENERATED` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `dc_act_sys_job` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) NOT NULL COMMENT '描述任务',
  `cron` varchar(255) NOT NULL COMMENT '任务表达式',
  `status` tinyint(1) NOT NULL COMMENT '状态:0未启动false/1启动true',
  `clazz_path` varchar(255) NOT NULL COMMENT '任务执行方法',
  `job_desc` varchar(255) DEFAULT NULL COMMENT '其他描述',
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `dc_act_sys_job` (`job_name`, `cron`, `status`, `clazz_path`, `job_desc`, `create_by`, `create_date`, `update_by`, `update_date`, `ip`) VALUES ('测试定时demo1', '0/5 * * * * ?', 0, 'com.dcits.branch.workflow.framework.scheduled.JobBpmnXmlConverter', '测试定时demo1', '雷建', '2019-1-12 10:50:08', '类', '2019-1-12 10:50:21', '127.0.0.1');


CREATE TABLE `dc_act_sys_log` (
  `ID` int(64) NOT NULL AUTO_INCREMENT COMMENT '系统日志表主键',
  `USER_NAME` varchar(32) DEFAULT NULL COMMENT '执行者',
  `IP` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '请求类型',
  `URL` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `TEXT` text COMMENT '请求内容',
  `PARAM` text COMMENT '参数',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='系统日志';


CREATE TABLE `dc_act_re_procdef` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键,流程实例的外键',
  `REV` int(11) DEFAULT NULL COMMENT '版本号',
  `CATEGORY` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义类型',
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程中文描述',
  `KEY` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '流程文件定义的ID',
  `VERSION` int(11) NOT NULL COMMENT '流程定义的版本号',
  `DEPLOYMENT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义文件',
  `DGRM_RESOURCE_NAME` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义描述信息',
  `HAS_START_FORM_KEY` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE` varchar(1) DEFAULT '1' COMMENT '流程状态0禁用1启用',
  `TENANT_ID` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
   RESOURCE_TIME datetime COLLATE utf8_bin DEFAULT NULL COMMENT '流程修改时间',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY`,`VERSION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程定义';

CREATE TABLE `dc_act_hi_reprocdef` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键,流程实例的外键',
  `REV` int(11) DEFAULT NULL COMMENT '版本号',
  `CATEGORY` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义类型',
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程中文描述',
  `KEY` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '流程文件定义的ID',
  `VERSION` int(11) NOT NULL COMMENT '流程定义的版本号',
  `DEPLOYMENT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义文件',
  `DGRM_RESOURCE_NAME` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '流程定义描述信息',
  `HAS_START_FORM_KEY` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE` varchar(1) DEFAULT '1' COMMENT '流程状态0禁用1启用',
  `TENANT_ID` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
   RESOURCE_TIME datetime COLLATE utf8_bin DEFAULT NULL COMMENT '流程修改时间',
   PRIMARY KEY (`ID`),
  UNIQUE KEY `ACT_UNIQ_HIPROCDEF` (`KEY`,`VERSION`,`TENANT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程历史定义，表结构同act_re_procdef';

CREATE TABLE `dc_act_assignee` (
  `ID` int(64) NOT NULL AUTO_INCREMENT,
  `KEY` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '流程定义时XML的ID',
  `SID` varchar(32) NOT NULL COMMENT '节点id',
  `ASSIGNEE` varchar(64) DEFAULT NULL COMMENT '办理人',
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT '候选组',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色',
  `ASSIGNEE_TYPE` varchar(6) NOT NULL COMMENT '办理人类型1办理人2候选人3组',
  `ACTIVTI_NAME` varchar(128) DEFAULT NULL COMMENT '节点名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义审批人';



CREATE TABLE `dc_act_reprocdef_flow` (
  `KEY` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '流程定义时XML的ID',
  `SID` varchar(30) NOT NULL COMMENT '节点ID',
  `TASK_DEF_KEY` varchar(30) COMMENT '流程定义的任务ID',
  `SID_TYPE` varchar(20) NOT NULL DEFAULT '01' COMMENT '开始事件、结束事件、',
  `SID_TITLE` varchar(50) DEFAULT NULL COMMENT '流程描述',
  `INIT_NUM` decimal(4) DEFAULT NULL COMMENT '初始流程节数',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '版本号',
  `STATE` varchar(1) DEFAULT '1' COMMENT '可用开关 0不可用代表false',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATOR` varchar(20) DEFAULT NULL COMMENT '创建人',
   PRIMARY KEY (`KEY`,`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程定义节点信息';



CREATE TABLE `dc_act_reprocdef_flowlines` (
  `KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `SID` varchar(32) NOT NULL COMMENT '流程ID',
  `TASK_DEF_KEY` varchar(32) COMMENT '流程定义的任务ID',
  `ID` int(32) NOT NULL COMMENT '连接线ID',
  `SID_NAME` varchar(50) DEFAULT NULL COMMENT '连接线描述',
  `SID_TYPE` varchar(60) DEFAULT NULL COMMENT '节点类型',
  `FROM_NODE` varchar(20) DEFAULT NULL COMMENT '源节点',
  `TO_NODE` varchar(20) DEFAULT NULL COMMENT '目标节点',
  `EXPR` varchar(300) DEFAULT NULL COMMENT '布尔类型表达式，用于决策节点后的连接线',
  `EXPR_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `STATE` varchar(1) DEFAULT '1' COMMENT '可用开关 0不可用代表false',
  `FINAL_TO_NODE` varchar(20) DEFAULT NULL COMMENT '布尔类型表达式动态目标节点0为false',
  UNIQUE KEY  (`KEY`,`SID`,`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='流程连接线定义';


CREATE TABLE `dc_act_hi_procinst` (
	`PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin NOT NULL COMMENT '作为task表等表的外键',
	`BUSINESS_KEY` VARCHAR (255) COLLATE utf8_bin  NOT NULL COMMENT '业务关键字',
	`PROC_DEF_KEY` varchar(64) COLLATE utf8_bin NOT NULL,
	`PROC_DEF_ID` VARCHAR (64) COLLATE utf8_bin NOT NULL COMMENT 'act_re_procdef的ID',
	`START_TIME` datetime NOT NULL COMMENT '流程起始时间',
	`END_TIME` datetime DEFAULT NULL COMMENT '流程结束时间',
	`DURATION` BIGINT (20) DEFAULT NULL COMMENT '流程耗时',
	`START_USER_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`START_ACT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '起始节点号',
	`END_ACT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '结束节点号',
	`SUPER_PROCESS_INSTANCE_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`DELETE_REASON` VARCHAR (4000) COLLATE utf8_bin DEFAULT NULL COMMENT '删除原因eg完成',
	`TENANT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT '',
	`NAME` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例描述信息',
	`USER_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '业务人员ID',
	`SUSPENSION_STATE` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '流程状态1销毁',
	PRIMARY KEY (`PROC_INST_ID`),
	UNIQUE KEY `PROC_INST_ID` (`PROC_INST_ID`),
	KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME`),
	UNIQUE KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '流程实例ID';


CREATE TABLE `dc_act_ru_execution` (
	`ID` VARCHAR (64) COLLATE utf8_bin NOT NULL,
	`REV` INT (11) DEFAULT NULL COMMENT '数据版本',
	`PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例ID,一个流程实例可能有多个流程实例',
	`BUSINESS_KEY` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例定义时的业务关键字',
	`PARENT_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '执行流的ID或者是执行流所属的父ID，代表当前执行流所属的流程实例',
	`PROC_DEF_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程数据定义的ID',
	`SUPER_EXEC` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '父流程实例的ID，流程嵌套流程',
	`ROOT_PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`ACT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '当前执行流的任务节点ID',
	`IS_ACTIVE` TINYINT (4) DEFAULT NULL COMMENT '当前执行流是否活跃0否',
	`IS_CONCURRENT` TINYINT (4) DEFAULT NULL COMMENT '当前执行流是否并行0否',
	`IS_SCOPE` TINYINT (4) DEFAULT NULL,
	`IS_EVENT_SCOPE` TINYINT (4) DEFAULT NULL,
	`IS_MI_ROOT` TINYINT (4) DEFAULT NULL,
	`SUSPENSION_STATE` INT (11) DEFAULT NULL,
	`CACHED_ENT_STATE` INT (11) DEFAULT NULL,
	`TENANT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT '',
	`NAME` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '当前执行流的任务节点描述',
	`START_TIME` datetime DEFAULT NULL COMMENT '开始事件',
	`START_USER_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '发起人ID',
	`LOCK_TIME` datetime NULL DEFAULT NULL,
	`IS_COUNT_ENABLED` TINYINT (4) DEFAULT NULL,
	`EVT_SUBSCR_COUNT` INT (11) DEFAULT NULL,
	`TASK_COUNT` INT (11) DEFAULT NULL,
	`JOB_COUNT` INT (11) DEFAULT NULL,
	`TIMER_JOB_COUNT` INT (11) DEFAULT NULL,
	`SUSP_JOB_COUNT` INT (11) DEFAULT NULL,
	`DEADLETTER_JOB_COUNT` INT (11) DEFAULT NULL,
	`VAR_COUNT` INT (11) DEFAULT NULL,
	`ID_LINK_COUNT` INT (11) DEFAULT NULL,
	PRIMARY KEY (`ID`),
	KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY`),
	KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID`),
	KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID`),
	KEY `ACT_FK_EXE_PARENT` (`PARENT_ID`),
	KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC`),
	KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '流程实例（执行流表）';


CREATE TABLE `dc_act_ru_task` (
	`ID` VARCHAR (64) COLLATE utf8_bin NOT NULL,
	`REV` INT (11) DEFAULT NULL,
	`EXECUTION_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '任务所处的执行流ID-act_ru_execution',
	`PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '对应的流程实例ID',
	`PROC_DEF_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '对应流程定义的数据ID',
	`NAME` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '任务名称',
	`PARENT_TASK_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`DESCRIPTION` VARCHAR (4000) COLLATE utf8_bin DEFAULT NULL,
	`TASK_DEF_KEY` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '任务定义的ID在任务定义时存在',
	`OWNER` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '任务拥有人',
	`DEALING_USER_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
	`ASSIGNEE` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '被指派的任务执行人',
	`DELEGATION` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`PRIORITY` INT (11) DEFAULT NULL,
	`CREATE_DATE` datetime NULL DEFAULT NULL,
	`DUE_DATE` datetime DEFAULT NULL,
	`CATEGORY` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`SUSPENSION_STATE` INT (11) DEFAULT NULL,
	`TENANT_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT '',
	`FORM_KEY` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`CLAIM_TIME` datetime DEFAULT NULL,
	PRIMARY KEY (`ID`),
	KEY `ACT_IDX_TASK_CREATE` (`CREATE_DATE`),
	KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID`),
	KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID`),
	KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '流程任务表';


CREATE TABLE `dc_act_ru_variable` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV` int(11) DEFAULT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_VALUE` double DEFAULT NULL,
  `LONG_VALUE` bigint(20) DEFAULT NULL,
  `TEXT_VALUE` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_VALUE` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT = '任务参数';


CREATE TABLE `dc_act_ru_identitylink` (
	`ID` VARCHAR (64) COLLATE utf8_bin NOT NULL,
	`REV` INT (11) DEFAULT NULL,
	`ROLE_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`TYPE` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`USER_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`TASK_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`PROC_DEF_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`ID`),
	KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID`),
	KEY `ACT_IDX_IDENT_LNK_ROLE` (`ROLE_ID`),
	KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID`),
	KEY `ACT_FK_TSKASS_TASK` (`TASK_ID`),
	KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '流程实例与身份关系表';


CREATE TABLE `dc_act_hi_actinst` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEALING_USER_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `DURATION` bigint(20) DEFAULT NULL,
  `DELETE_REASON` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID`,`ACT_ID`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID`,`ACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='历史行为表信息';


CREATE TABLE `dc_act_hi_comment` (
	`ID` VARCHAR (64) COLLATE utf8_bin NOT NULL,
	`TYPE` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`TIME` datetime NOT NULL,
	`USER_ID` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`TASK_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`PROC_INST_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`ACTION` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL,
	`MESSAGE` VARCHAR (4000) COLLATE utf8_bin DEFAULT NULL,
	`URL` VARCHAR (4000) COLLATE utf8_bin DEFAULT NULL,
	`CONTENT_ID` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL,
	`FULL_MSG` text COLLATE utf8_bin,
	PRIMARY KEY (`ID`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '审批意见表和流程中的附件信息表';


CREATE TABLE `dc_act_sys_permission` (
  `PERMISSION_ID` varchar(32) NOT NULL,
  `PERMISSION_NAME` varchar(128) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
 `DEL_FLAG` varchar(1) NOT NULL DEFAULT '1' COMMENT '0不可用',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '权限信息表';


CREATE TABLE `dc_act_sys_role` (
  `ROLE_ID` varchar(32) NOT NULL,
  `ROLE_NAME` varchar(128) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '1不可用',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '角色信息表';


CREATE TABLE `dc_act_sys_user_permission` (
  `USER_ID` varchar(32) NOT NULL,
  `PERMISSION_ID` varchar(32) NOT NULL,
  `DEL_FLAG` varchar(1) NOT NULL DEFAULT '1' COMMENT '0不可用',
  PRIMARY KEY (`USER_ID`,`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='权限用户信息对照表';


CREATE TABLE `dc_act_sys_user` (
  `USER_ID` varchar(36) NOT NULL,
  `USERNAME` varchar(64) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `AGE` int(4) DEFAULT NULL,
  `EMAIL` varchar(128) DEFAULT NULL,
  `PHOTO` varchar(255) DEFAULT NULL,
  `REAL_NAME` varchar(18) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `UPDATE_BY` varchar(32) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(1) NOT NULL DEFAULT '1' COMMENT '0不可用',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='用户信息表';


CREATE TABLE `dc_act_sys_user_role` (
  `USER_ID` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  `DEL_FLAG` varchar(1) NOT NULL DEFAULT '1' COMMENT '0不可用',
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='角色用户信息对照表';

INSERT INTO `dc_act_seq_no_para` VALUES ('CountSalary', '1', '999999', '2', '1');
INSERT INTO `dc_act_seq_no_para` VALUES ('leave', '1', '999999', '6', '1');
INSERT INTO `dc_act_seq_no_para` VALUES ('task', '1', '999999', '6', '1');


/*
alter table act_ru_execution add constraint ACT_FK_EXE_PARENT foreign key (PARENT_ID)
      references act_ru_execution (ID);

alter table act_ru_execution add constraint ACT_FK_EXE_PROCDEF foreign key (PROC_DEF_ID)
      references act_re_procdef (ID);

alter table act_ru_execution add constraint ACT_FK_EXE_PROCINST foreign key (PROC_INST_ID)
      references act_hi_procinst (PROC_INST_ID);

alter table act_ru_execution add constraint ACT_FK_EXE_SUPER foreign key (SUPER_EXEC)
      references act_ru_execution (ID);

alter table act_ru_identitylink add constraint ACT_FK_ATHRZ_PROCEDEF foreign key (PROC_DEF_ID)
      references act_re_procdef (ID);

alter table act_ru_identitylink add constraint ACT_FK_IDL_PROCINST foreign key (PROC_INST_ID)
      references act_ru_execution (ID);

alter table act_ru_identitylink add constraint ACT_FK_TSKASS_TASK foreign key (TASK_ID)
      references act_ru_task (ID);

alter table act_ru_task add constraint ACT_FK_TASK_EXE foreign key (EXECUTION_ID)
      references act_ru_execution (ID);

alter table act_ru_task add constraint ACT_FK_TASK_PROCDEF foreign key (PROC_DEF_ID)
      references act_re_procdef (ID);

alter table act_ru_task add constraint ACT_FK_TASK_PROCINST foreign key (PROC_INST_ID)
      references act_hi_procinst (PROC_INST_ID);

alter table act_ru_variable add constraint ACT_FK_VAR_EXE foreign key (EXECUTION_ID)
      references act_ru_execution (ID);

alter table act_ru_variable add constraint ACT_FK_VAR_PROCINST foreign key (PROC_INST_ID)
      references act_ru_execution (ID);*/









