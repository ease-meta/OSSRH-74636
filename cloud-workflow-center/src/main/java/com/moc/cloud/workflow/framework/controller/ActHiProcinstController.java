package com.moc.cloud.workflow.framework.controller;


import com.moc.cloud.workflow.common.enums.AuthTypeEnum;
import com.moc.cloud.workflow.common.exception.BusinessException;
import com.moc.cloud.workflow.common.responses.Responses;
import com.moc.cloud.workflow.framework.annotation.Resources;
import com.moc.cloud.workflow.framework.config.BaseController;
import com.moc.cloud.workflow.framework.config.TaskActionRequest;
import com.moc.cloud.workflow.framework.entity.ActAssignee;
import com.moc.cloud.workflow.framework.entity.ActHiActinst;
import com.moc.cloud.workflow.framework.entity.ActHiComment;
import com.moc.cloud.workflow.framework.entity.ActHiProcinst;
import com.moc.cloud.workflow.framework.entity.ActReProcdef;
import com.moc.cloud.workflow.framework.entity.ActRuTask;
import com.moc.cloud.workflow.framework.entity.ProcessInstanceCreateRequest;
import com.moc.cloud.workflow.framework.entity.ReprocdefFlow;
import com.moc.cloud.workflow.framework.entity.ReprocdefFlowLines;
import com.moc.cloud.workflow.framework.entity.RestVariable;
import com.moc.cloud.workflow.framework.idgenerator.KeyGenerateFactory;
import com.moc.cloud.workflow.framework.serviceImpl.ActAssigneeServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActHiActinstServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActHiCommentServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActHiProcinstServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActReProcdefServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActRuExecutionServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ActRuTaskServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ReprocdefFlowLinesServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.ReprocdefFlowServiceImpl;
import com.moc.cloud.workflow.framework.serviceImpl.SeqNoParaServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程实例ID 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Slf4j
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActHiProcinstController extends BaseController<ActHiProcinst> {

    @Autowired
    ActHiProcinstServiceImpl actHiProcinstService;


    @Autowired
    ActReProcdefServiceImpl actReProcdefService;

    @Autowired
    ReprocdefFlowServiceImpl reprocdefFlowService;

    @Autowired
    ReprocdefFlowLinesServiceImpl reprocdefFlowLinesService;

    @Autowired
    ActRuExecutionServiceImpl actRuExecutionService;

    @Autowired
    SeqNoParaServiceImpl seqNoParaService;

    @Autowired
    ActRuTaskServiceImpl actRuTaskService;

    @Autowired
    ActAssigneeServiceImpl actAssigneeService;

    @Autowired
    ActHiActinstServiceImpl actHiActinstService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @Autowired
    ActHiCommentServiceImpl actHiCommentService;

    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    /**
     * 新建流程实例
     */

    @Resources(auth = AuthTypeEnum.OPEN, description = "新建流程实例")
    @RequestMapping(value = {"/runtime/process-instances"}, method = {RequestMethod.POST})
    public synchronized Responses<Void> createProcessInstance(@RequestBody @Validated ProcessInstanceCreateRequest request, HttpServletRequest httpRequest, HttpServletResponse response) {
        log.info(request.toString());
        TransactionStatus status = transactionManager.getTransaction(definition);
        String businessKey = request.getBusinessKey();
        String userId = request.getUserId();
        //校验businessKey不能重复
        if (!ObjectUtils.isEmpty(actHiProcinstService.selectByBusinessKey(businessKey))) {
            return responses(400, "参数不合法businessKey重复");
        }
        //使用activiti创建流程实例
        //创建流程实例传参问题
        Map<String, Object> variables = new HashMap<String, Object>();
        if (request.getVariables() != null) {
            log.info("流程定义的参数维护");
            for (RestVariable variable : request.getVariables()) {
                if (variable.getName() == null) {
                    throw new BusinessException("Variable name is required.");
                }
                variables.put(variable.getName(), variable.getValue());
            }
            log.info("流程定义参数大小:{}", variables.size());
        }
        try {
            //1.查询流程定义
            ActReProcdef actReProcdef = new ActReProcdef();
            actReProcdef.setKey(request.getProcessDefinitionKey());
            actReProcdef = actReProcdefService.selectOne(actReProcdef);

            if (!actReProcdef.getSuspensionState()) {
                return responses(400, "流程" + actReProcdef.getId() + "状态不可用");
            }
            //2.查询流程起始
            List<ReprocdefFlowLines> reprocdefFlowLinesList = reprocdefFlowLinesService.seletFlowsLinesByKey(request.getProcessDefinitionKey());
            if (CollectionUtils.isEmpty(reprocdefFlowLinesList)) {
                return responses(400, "流程节点不可用");
            }

            Map<String, ReprocdefFlow> reprocdefFlowMap = reprocdefFlowService.seletFlowsByKeyForMap(request.getProcessDefinitionKey());
            if (CollectionUtils.isEmpty(reprocdefFlowMap)) {
                return responses(400, "流程节点不可用");
            }
            log.info("流程节点信息map值{}:", reprocdefFlowMap);
            LocalDateTime localDateTime = LocalDateTime.now();
            String procInstId = KeyGenerateFactory.getInstance().getKey();
            //3.保存流程实例且创建执行流
            ActHiProcinst actHiProcinst = new ActHiProcinst();
            actHiProcinst.setProcDefId(actReProcdef.getId());
            actHiProcinst.setProcDefKey(actReProcdef.getKey());

            log.info("业务关键字信息{}", businessKey);
            actHiProcinst.setBusinessKey(businessKey);
            actHiProcinst.setProcInstId(procInstId);
            actHiProcinst.setStartTime(localDateTime);
            actHiProcinst.setStartUserId(userId);
            actHiProcinst.setUserId(userId);
            //获取起始节点
            String fromNode = reprocdefFlowLinesList.get(0).getFromNode();
            //获取下一节点任务
            String toNode = reprocdefFlowLinesList.get(0).getToNode();
            actHiProcinst.setStartActId(fromNode);
            actHiProcinst.setName(actReProcdef.getName());
            actHiProcinstService.insert(actHiProcinst);
            //4.任务的创建,任务都是已起始节点开始的，创建任务默认到第一级审批处理
            ActRuTask actRuTask = new ActRuTask();
            String id = seqNoParaService.getFlowNo("task", 14);
            String id1 = seqNoParaService.getFlowNo("task", 14);
            actRuTask.setId(id1);
            actRuTask.setRev(1);
            actRuTask.setExecutionId("");
            actRuTask.setProcInstId(procInstId);
            actRuTask.setProcDefId(actReProcdef.getId());
            actRuTask.setName(reprocdefFlowMap.get(toNode).getSidTitle());
            actRuTask.setParentTaskId("");
            actRuTask.setDescription("");
            actRuTask.setTaskDefKey(toNode);
            ActAssignee actAssignee = actAssigneeService.getByKeyAndSid(actReProcdef.getKey(), toNode);
            actRuTask.setOwner(userId);
            if (Objects.nonNull(actAssignee)) {
                //只能设置到角色如一线经办、复核或者授权。
                actRuTask.setAssignee(actAssignee.getAssignee());
                //设置当前节点的处理人或者角色
                actRuTask.setDealingUserId(actAssignee.getDealingUserId());
            }
            actRuTask.setDelegation("");
            actRuTask.setPriority(Thread.currentThread().getPriority());
            actRuTask.setSuspensionState(true);
            actRuTask.setCreateDate(localDateTime);
            actRuTaskService.insert(actRuTask);
            //添加到历史执行记录表
            ActHiActinst actHiActinst = new ActHiActinst();
            actHiActinst.setId(id);
            actHiActinst.setProcDefId(actReProcdef.getId());
            actHiActinst.setProcInstId(procInstId);
            actHiActinst.setExecutionId("");
            actHiActinst.setActId(fromNode);
            actHiActinst.setTaskId("");
            actHiActinst.setCallProcInstId("");
            actHiActinst.setActName(reprocdefFlowMap.get(fromNode).getSidTitle());
            actHiActinst.setActType(reprocdefFlowMap.get(fromNode).getSidType());
            actHiActinst.setStartTime(localDateTime);
            LocalDateTime endTime = LocalDateTime.now();
            actHiActinst.setEndTime(endTime);
            actHiActinst.setDeleteReason("启动节点");
            actHiActinst.setTenantId("");
            //毫秒数转为秒计算
            actHiActinst.setDuration(Duration.between(localDateTime, endTime).toMillis() / 1000);
            actHiActinstService.insertSelective(actHiActinst);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw new BusinessException("创建流程执行异常", e);
        }
        response.setStatus(HttpStatus.CREATED.value());
        return responses(HttpStatus.OK);
    }


    /**
     * 流程查询任务查询
     * 1、根据taskId查询
     * 2、根据审批人查询待审批任务dealingUserId
     * 3、根据业务关键字查询businessKey
     * 4、根据流程实例ID查询procInstId
     * 5、根据任务的拥有者查询owner
     *
     * @return http://127.0.0.1:8080/v1/runtime/tasks?businessKey=992456789&taskId=20190213000021
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "流程查询")
    @RequestMapping(value = {"/runtime/tasks"}, method = {RequestMethod.GET})
    @ApiImplicitParams({@ApiImplicitParam(name = "assignee", dataType = "string", value = "角色ID", paramType = "query"), @ApiImplicitParam(name = "taskId", dataType = "string", value = "任务ID", paramType = "query"), @ApiImplicitParam(name = "dealingUserId", dataType = "string", value = "当前任务处理人", paramType = "query"), @ApiImplicitParam(name = "procInstId", dataType = "string", value = "流程实例ID", paramType = "query"), @ApiImplicitParam(name = "owner", dataType = "string", value = "任务拥有者", paramType = "query"), @ApiImplicitParam(name = "businessKey", dataType = "string", value = "业务关键字", paramType = "query")})
    public Responses<List<ActRuTask>> getTask(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams, HttpServletRequest request) {
        log.info("前端传入的值是:{}", allRequestParams);
        Assert.notEmpty(allRequestParams, "请求查询参数不能为空");
        ActRuTask actRuTask = new ActRuTask();

        if (allRequestParams.containsKey("taskId")) {
            actRuTask.setId(allRequestParams.get("taskId"));
        }
        if (allRequestParams.containsKey("dealingUserId")) {
            actRuTask.setDealingUserId(allRequestParams.get("dealingUserId"));
        }
        if (allRequestParams.containsKey("procInstId")) {
            actRuTask.setProcInstId(allRequestParams.get("procInstId"));
        }
        if (allRequestParams.containsKey("owner")) {
            actRuTask.setOwner(allRequestParams.get("owner"));
        }

        //根据角色来查询
        if (allRequestParams.containsKey("assignee")) {
            actRuTask.setAssignee(allRequestParams.get("assignee"));
        }

        if (allRequestParams.containsKey("businessKey")) {
            ActHiProcinst actHiProcinst = actHiProcinstService.selectByBusinessKey(allRequestParams.get("businessKey"));
            if (Objects.isNull(actHiProcinst)) {
                return responses(HttpStatus.OK, null);
            }
            actRuTask.setProcInstId(actHiProcinst.getProcInstId());
        }

        List<ActRuTask> actRuTaskList = actRuTaskService.selectSelective(actRuTask);
        return responses(HttpStatus.OK, actRuTaskList);
    }


    /**
     * 根据业务关键字查询流程实例
     *
     * @param businessKey
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "业务关键字查询流程实例")
    @RequestMapping(value = {"/runtime/procinst/{businessKey}"}, method = {RequestMethod.GET})
    public Responses<List<ActHiProcinst>> getTaskPendingByBusinessKey(@PathVariable String businessKey, HttpServletRequest request) {
        ActHiProcinst actHiProcinst = new ActHiProcinst();
        actHiProcinst.setBusinessKey(businessKey);
        List<ActHiProcinst> actHiProcinstList = actHiProcinstService.selectSelective(actHiProcinst);
        return responses(HttpStatus.OK, actHiProcinstList);
    }

    /**
     * 根据实例ID查询任务信息
     *
     * @param procInstId
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "业务关键字查询流程实例")
    @RequestMapping(value = {"/runtime/task/{procInstId}"}, method = {RequestMethod.GET})
    public Responses<List<ActRuTask>> getTaskPendingByprocInstId(@PathVariable String procInstId, HttpServletRequest request) {
        ActRuTask actRuTask = new ActRuTask();
        actRuTask.setProcInstId(procInstId);
        List<ActRuTask> actRuTaskList = actRuTaskService.selectSelective(actRuTask);
        return responses(HttpStatus.OK, actRuTaskList);
    }

    /**
     * 流程任务回退
     *
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "流程任务回退")
    @RequestMapping(value = {"/runtime/tasks/{}"}, method = {RequestMethod.PATCH})
    public Responses<Void> retroversionTask(@RequestBody @Validated ActRuTask param, HttpServletRequest request) {
        //先去历史表查询当前ID的流程是是否完成
        //如果已完成，查询出上一个节点任务并且插到当前表
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            List<ActRuTask> actRuTasks = actRuTaskService.selectSelective(param);
            if (CollectionUtils.isEmpty(actRuTasks) || actRuTasks.size() > 1) {
                return responses(500, "回退的任务ID不存在或者根据当前查询条件获得多条任务信息");
            }
            String procInstId = actRuTasks.get(0).getProcInstId();
            String procDefId = actRuTasks.get(0).getProcDefId();
            ActHiActinst actHiActinst = new ActHiActinst();
            actHiActinst.setProcInstId(procInstId);
            List<ActHiActinst> actHiActinstList = actHiActinstService.selectSelective(actHiActinst);
            if (Objects.nonNull(actHiActinstList)) {
                int size = actHiActinstList.size();
                //说明有需要回退的任务1.删除历史表的数据一共两条数据、删除当前表当前任务；当前表新增历史表数据
                actHiActinstService.deleteByPrimaryKey(actHiActinstList.get(size - 1).getId());
                actRuTaskService.deleteByPrimaryKey(actRuTasks.get(0).getId());
                ActRuTask actRuTaskNeed = new ActRuTask();
                //下一个节点任务
                String id = seqNoParaService.getFlowNo("task", 14);
                actRuTaskNeed.setId(id);
                actRuTaskNeed.setRev(1);
                actRuTaskNeed.setExecutionId("");
                actRuTaskNeed.setProcInstId(actHiActinstList.get(size - 2).getProcInstId());
                actRuTaskNeed.setProcDefId(actHiActinstList.get(size - 2).getProcDefId());
                actRuTaskNeed.setName(actHiActinstList.get(size - 2).getActName());
                actRuTaskNeed.setParentTaskId("");
                actRuTaskNeed.setDescription("");
                actRuTaskNeed.setTaskDefKey(actHiActinstList.get(size - 2).getActId());
                ActAssignee actAssignee = actAssigneeService.getByKeyAndSid(StringUtils.substring(procDefId, 0, StringUtils.indexOf(procDefId, ":")), actHiActinstList.get(size - 2).getActId());
                actRuTaskNeed.setOwner(actRuTasks.get(0).getOwner());
                if (null != actAssignee) {
                    actRuTaskNeed.setAssignee(actAssignee.getAssignee());
                    //设置当前节点的处理人或者角色
                    actRuTaskNeed.setDealingUserId(actAssignee.getDealingUserId());
                }
                actRuTaskNeed.setDelegation("");
                actRuTaskNeed.setPriority(Thread.currentThread().getPriority());
                actRuTaskNeed.setSuspensionState(true);
                actRuTaskNeed.setCreateDate(LocalDateTime.now());
                actRuTaskService.insert(actRuTaskNeed);
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);

        }
        //再去当前表查询流程是否完成，如果有数则不做任何处理
        return responses(HttpStatus.OK);
    }

    /**
     * 流程任务更新
     *
     * @param actRuTask
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "流程任务更新")
    @RequestMapping(value = {"/runtime/tasks"}, method = {RequestMethod.PUT})
    public Responses<ActRuTask> updateTask(@RequestBody @Validated ActRuTask actRuTask, HttpServletRequest request) {
        actRuTaskService.updateByPrimaryKeySelective(actRuTask);
        ActRuTask actRuTask1 = actRuTaskService.selectByPrimaryKey(actRuTask.getId());
        return responses(HttpStatus.OK, actRuTask1);
    }

    /**
     * 流程审批逻辑1、	办理：按照流程定义顺序执行流程
     * org.activiti.rest.service.api.runtime.task.TaskResource
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "流程执行")
    @RequestMapping(value = {"/runtime/executeTaskAction"}, method = {RequestMethod.POST})
    public Responses<Void> executeTaskAction(@RequestBody TaskActionRequest actionRequest, HttpServletRequest request, HttpServletResponse response) {
        if (actionRequest == null) {
            throw new BusinessException("A request body was expected when executing a task action.");
        }
        String taskId = actionRequest.getTaskId();
        List<ActHiComment> actHiCommentList = actionRequest.getActHiComments();
        if (!CollectionUtils.isEmpty(actHiCommentList)) {
            log.info("{}", actHiCommentList.get(0));
        }
        ActRuTask actRuTask = actRuTaskService.selectByPrimaryKey(taskId);
        if (Objects.isNull(actRuTask)) {
            throw new BusinessException("任务ID[" + taskId + "]不存在或者已完成");
        }
        if (Objects.isNull(actionRequest.getDealingUserId())) {
            throw new BusinessException("流程任务处理人不能为空");
        }
        if (TaskActionRequest.ACTION_COMPLETE.equals(actionRequest.getAction())) {
            completeTask(actRuTask, actionRequest);
        } else if (TaskActionRequest.ACTION_CLAIM.equals(actionRequest.getAction())) {
            claimTask(actRuTask, actionRequest);
        } else if (TaskActionRequest.ACTION_RESOLVE.equals(actionRequest.getAction())) {
            resolveTask(actRuTask, actionRequest);
        } else {
            throw new BusinessException("Invalid action: '" + actionRequest.getAction() + "'.");
        }
        return responses(HttpStatus.OK);
    }

    /**
     * 5、	流程销毁：用户可以在有撤销权限的情况下直接撤销流程
     */
    protected boolean resolveTask(ActRuTask task, TaskActionRequest actionRequest) {
        log.info("节点任务销毁操作resolveTask");
        TransactionStatus status = transactionManager.getTransaction(definition);
        LocalDateTime endtime = LocalDateTime.now();
        try {

            ActHiProcinst actHiProcinst = new ActHiProcinst();
            //1.更新流程实例已经完成
            actRuTaskService.deleteByPrimaryKey(task.getId());
            actHiProcinst.setProcInstId(task.getProcInstId());
            actHiProcinst.setEndTime(endtime);
            actHiProcinst.setDeleteReason("任务销毁或者挂起");
            actHiProcinst.setEndActId(task.getTaskDefKey());
            actHiProcinst.setSuspensionState(true);
            actHiProcinstService.complete(actHiProcinst);
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            log.error("异常堆栈信息:{}", e);
            transactionManager.rollback(status);
            throw new BusinessException("流程执行异常", e);
        }
    }

    /**
     * 6、	流程结束：用户可以在有直接结束流程的情况下直接结束流程
     */

    @Resources(auth = AuthTypeEnum.OPEN, description = "办理至当前流程的任一节点")
    @RequestMapping(value = {"/runtime/completeTaskNoComment"}, method = {RequestMethod.POST})
    public Responses<Void> completeTaskNoComment(@RequestBody TaskActionRequest actionRequest, HttpServletRequest request, HttpServletResponse response) {
        if (actionRequest == null) {
            throw new BusinessException("A request body was expected when executing a task action.");
        }
        String taskId = actionRequest.getTaskId();
        ActRuTask actRuTask = actRuTaskService.selectByPrimaryKey(taskId);
        if (Objects.isNull(actRuTask)) {
            throw new BusinessException("任务ID[" + taskId + "]不存在或者已完成");
        }
        if (TaskActionRequest.ACTION_COMPLETE.equals(actionRequest.getAction())) {
            completeTask(actRuTask, actionRequest);
        } else if (TaskActionRequest.ACTION_CLAIM.equals(actionRequest.getAction())) {
            claimTask(actRuTask, actionRequest);
        } else {
            throw new BusinessException("Invalid action: '" + actionRequest.getAction() + "'.");
        }
        return responses(HttpStatus.OK);
    }

    /**
     * 完成节点流
     * 注意事务控制
     *
     * @param task
     * @param actionRequest
     */
    protected boolean completeTask(ActRuTask task, TaskActionRequest actionRequest) {
        log.info("节点任务完成操作completeTask");
        TransactionStatus status = transactionManager.getTransaction(definition);
        LocalDateTime endtime = LocalDateTime.now();
        try {
            Map variablesToSet = null;
            if (actionRequest.getVariables() != null) {
                variablesToSet = new HashMap();
                for (RestVariable var : actionRequest.getVariables()) {
                    if (var.getName() == null) {
                        throw new BusinessException("Variable name is required");
                    }
                    variablesToSet.put(var.getName(), var.getValue());
                }
            }
            ActReProcdef actReProcdef = actReProcdefService.listByID(task.getProcDefId());
            //查询当前节点任务dc_act_ru_task 主方法已完成
            //获取节点流程顺序dc_act_reprocdef_flow、dc_act_reprocdef_flowlines
            //2.查询流程起始
            List<ReprocdefFlowLines> reprocdefFlowLinesList = reprocdefFlowLinesService.seletFlowsLinesByKey(actReProcdef.getKey());
            if (reprocdefFlowLinesList == null || reprocdefFlowLinesList.size() == 0) {
                return false;
            }
            log.info("执行流的节点信息{}", reprocdefFlowLinesList);

            Map<String, ReprocdefFlow> reprocdefFlowMap = reprocdefFlowService.seletFlowsByKeyForMap(actReProcdef.getKey());
            if (reprocdefFlowMap == null || reprocdefFlowMap.size() == 0) {
                return false;
            }
            log.info("节点信息map值{}", reprocdefFlowMap);

            //从dc_act_reprocdef_flowlines表查询下一节点信息
            List<ReprocdefFlowLines> nextReprocdefFlowLines = reprocdefFlowLinesList.stream().filter(Objects::nonNull).filter(reprocdefFlowLines -> {
                return task.getTaskDefKey().equals(reprocdefFlowLines.getFromNode());
            }).collect(Collectors.toList());

            log.info("节点信息list值{}", nextReprocdefFlowLines);
            String nextReprocdefFlowLineTemp = nextReprocdefFlowLines.get(0).getToNode();
            if (nextReprocdefFlowLines.size() == 1 && "ExclusiveGateway".equals(reprocdefFlowMap.get(nextReprocdefFlowLineTemp).getSidType())) {
                nextReprocdefFlowLines = reprocdefFlowLinesList.stream().filter(Objects::nonNull).filter(reprocdefFlowLines -> {
                    return nextReprocdefFlowLineTemp.equals(reprocdefFlowLines.getFromNode());
                }).collect(Collectors.toList());
            }
            log.info("当前节点的任务信息{}", task.getTaskDefKey());
            ActHiProcinst actHiProcinst = new ActHiProcinst();
            if (CollectionUtils.isEmpty(nextReprocdefFlowLines)) {
                //任务完成，没有下一个节点信息的时候
                log.info("下一节点信息为空，不进行任何处理，流程实例执行完成");
                //1.更新流程实例已经完成
                actRuTaskService.deleteByPrimaryKey(task.getId());
                actHiProcinst.setProcInstId(task.getProcInstId());
                actHiProcinst.setEndTime(endtime);
                actHiProcinst.setDeleteReason("任务完成");
                actHiProcinst.setEndActId(task.getTaskDefKey());
                actHiProcinstService.complete(actHiProcinst);
                //2.删除dc_act_ru_task数据，将任务表的数据act_hi_actinst
                ActHiActinst actHiActinst = new ActHiActinst();
                actHiActinst.setId(task.getId());
                actHiActinst.setProcDefId(task.getProcDefId());
                actHiActinst.setProcInstId(task.getProcInstId());
                actHiActinst.setExecutionId("");
                actHiActinst.setActId(task.getTaskDefKey());
                actHiActinst.setTaskId(task.getId());
                actHiActinst.setActName(reprocdefFlowMap.get(task.getTaskDefKey()).getSidTitle());
                actHiActinst.setActType(reprocdefFlowMap.get(task.getTaskDefKey()).getSidType());
                actHiActinst.setDealingUserId(actionRequest.getDealingUserId());
                actHiActinst.setStartTime(task.getCreateDate());
                actHiActinst.setEndTime(endtime);
                actHiActinst.setDuration(Duration.between(task.getCreateDate(), endtime).toMillis() / 1000);
                actHiActinstService.insertSelective(actHiActinst);
                transactionManager.commit(status);
                return true;
            } else if (nextReprocdefFlowLines.size() == 1 && !"ExclusiveGateway".equals(reprocdefFlowMap.get(nextReprocdefFlowLines.get(0).getToNode()).getSidType())) {
                //只有一个任务节点时候，任务未完成
                log.info("当前任务节点只有一个下游任务节点时候，任务未完成，执行完成当前流程节点开始下一个流程节点");
                String nextTodNode = nextReprocdefFlowLines.get(0).getToNode();
                //1.删除dc_act_ru_task数据
                actRuTaskService.deleteByPrimaryKey(task.getId());
                //将任务表的数据act_hi_actinst同时新增下一个节点的任务信息
                ActHiActinst actHiActinst = new ActHiActinst();
                actHiActinst.setId(task.getId());
                actHiActinst.setProcDefId(task.getProcDefId());
                actHiActinst.setProcInstId(task.getProcInstId());
                actHiActinst.setExecutionId("");
                actHiActinst.setActId(task.getTaskDefKey());
                actHiActinst.setTaskId(task.getId());
                actHiActinst.setActName(reprocdefFlowMap.get(task.getTaskDefKey()).getSidTitle());
                actHiActinst.setActType(reprocdefFlowMap.get(task.getTaskDefKey()).getSidType());
                actHiActinst.setDealingUserId(actionRequest.getDealingUserId());
                actHiActinst.setStartTime(task.getCreateDate());
                actHiActinst.setEndTime(endtime);
                actHiActinst.setDuration(Duration.between(task.getCreateDate(), endtime).toMillis() / 1000);
                actHiActinstService.insertSelective(actHiActinst);
                if ("EndEvent".equalsIgnoreCase(reprocdefFlowMap.get(nextTodNode).getSidType())) {
                    log.info("当前节点对的下游节点是EndEvent类型的，流程直接结束");
                    //1.更新流程实例已经完成
                    actHiProcinst.setProcInstId(task.getProcInstId());
                    actHiProcinst.setEndTime(endtime);
                    actHiProcinst.setDeleteReason("任务完成");
                    actHiProcinst.setEndActId(nextTodNode);
                    actHiProcinstService.complete(actHiProcinst);
                } else {
                    ActRuTask actRuTask = new ActRuTask();
                    //下一个节点任务
                    String id = seqNoParaService.getFlowNo("task", 14);
                    actRuTask.setId(id);
                    actRuTask.setRev(1);
                    actRuTask.setExecutionId("");
                    actRuTask.setProcInstId(task.getProcInstId());
                    actRuTask.setProcDefId(actReProcdef.getId());
                    actRuTask.setName(reprocdefFlowMap.get(nextReprocdefFlowLines.get(0).getToNode()).getSidTitle());
                    actRuTask.setParentTaskId("");
                    actRuTask.setDescription("");
                    actRuTask.setTaskDefKey(nextReprocdefFlowLines.get(0).getToNode());
                    ActAssignee actAssignee = actAssigneeService.getByKeyAndSid(actReProcdef.getKey(), nextReprocdefFlowLines.get(0).getToNode());
                    actRuTask.setOwner(task.getOwner());
                    if (null != actAssignee) {
                        actRuTask.setAssignee(actAssignee.getAssignee());
                        //设置当前节点的处理人或者角色
                        actRuTask.setDealingUserId(actAssignee.getDealingUserId());
                    }
                    actRuTask.setDelegation("");
                    actRuTask.setPriority(Thread.currentThread().getPriority());
                    actRuTask.setSuspensionState(true);
                    actRuTask.setCreateDate(LocalDateTime.now());
                    actRuTaskService.insert(actRuTask);
                }
                transactionManager.commit(status);
                return true;
            } else {
                //多决策节点或者网关节点
                log.info("当前任务节点有多个下游任务节点时候，任务未完成，执行决策表达式");
                String nextTodNode = nextReprocdefFlowLines.get(0).getToNode();
                String conditionExpression = nextReprocdefFlowLines.get(0).getExpr();
                String exprValue = nextReprocdefFlowLines.get(0).getExprValue();
                log.info("需要执行的节点信息{},决策表达式{},枚举值{}", nextTodNode, conditionExpression, exprValue);
                if (StringUtils.isNotEmpty(conditionExpression)) {
                    if (Objects.isNull(exprValue)) {
                        throw new BusinessException("当存在决策节点表达式时，决策枚举值不能为空");
                    }
                    String expr = StringUtils.substring(conditionExpression, 2, conditionExpression.length() - 1).replace("!", "");
                    log.info("表达式参数值{}", expr);
                    RestVariable restVariable = actionRequest.getVariables().stream().filter(v -> expr.equals(v.getName())).findFirst().orElse(null);
                    if (Objects.isNull(restVariable)) {
                        throw new BusinessException("当存在决策节点表达式时，决策枚举值" + expr + "不能为空");
                    }
                    int size = nextReprocdefFlowLines.size();
                    log.info("决策节点分支数{}", size);
                    String needNextFlowLines = null;
                    for (int i = 0; i < size; i++) {
                        if (restVariable.getValue().equals(nextReprocdefFlowLines.get(i).getExprValue())) {
                            log.info("下一个节点信息是{}", nextReprocdefFlowLines.get(i).getToNode());
                            needNextFlowLines = nextReprocdefFlowLines.get(i).getToNode();
                        }
                    }
                    Assert.notNull(needNextFlowLines, "决策后的节点信息不能为空");
                    ActRuTask actRuTask = new ActRuTask();
                    //下一个节点任务
                    String id = seqNoParaService.getFlowNo("task", 14);
                    actRuTask.setId(id);
                    actRuTask.setRev(1);
                    actRuTask.setExecutionId("");
                    actRuTask.setProcInstId(task.getProcInstId());
                    actRuTask.setProcDefId(actReProcdef.getId());
                    actRuTask.setName(reprocdefFlowMap.get(needNextFlowLines).getSidTitle());
                    actRuTask.setParentTaskId("");
                    actRuTask.setDescription("");
                    actRuTask.setTaskDefKey(needNextFlowLines);
                    ActAssignee actAssignee = actAssigneeService.getByKeyAndSid(actReProcdef.getId(), needNextFlowLines);
                    actRuTask.setOwner(task.getOwner());
                    if (null != actAssignee) {
                        actRuTask.setAssignee(actAssignee.getAssignee());
                        //设置当前节点的处理人或者角色
                        actRuTask.setDealingUserId(actAssignee.getDealingUserId());
                    }
                    actRuTask.setDelegation("");
                    actRuTask.setPriority(Thread.currentThread().getPriority());
                    actRuTask.setSuspensionState(true);
                    actRuTask.setCreateDate(LocalDateTime.now());
                    actRuTaskService.insert(actRuTask);
                }
                //将任务表的数据act_hi_actinst同时新增下一个节点的任务信息
                ActHiActinst actHiActinst = new ActHiActinst();
                actHiActinst.setId(task.getId());
                actHiActinst.setProcDefId(task.getProcDefId());
                actHiActinst.setProcInstId(task.getProcInstId());
                actHiActinst.setExecutionId("");
                actHiActinst.setActId(task.getTaskDefKey());
                actHiActinst.setTaskId(task.getId());
                actHiActinst.setActName(reprocdefFlowMap.get(task.getTaskDefKey()).getSidTitle());
                actHiActinst.setActType(reprocdefFlowMap.get(task.getTaskDefKey()).getSidType());
                actHiActinst.setDealingUserId(actionRequest.getDealingUserId());
                actHiActinst.setStartTime(task.getCreateDate());
                actHiActinst.setEndTime(endtime);
                actHiActinst.setDuration(Duration.between(task.getCreateDate(), endtime).toMillis() / 1000);
                actHiActinstService.insertSelective(actHiActinst);
                actRuTaskService.deleteByPrimaryKey(task.getId());
                transactionManager.commit(status);
                return true;
            }
        } catch (Exception e) {
            log.error("异常堆栈信息:{}", e);
            transactionManager.rollback(status);
            throw new BusinessException("流程执行异常", e);
        }
    }


    /**
     * 指定由某人执行
     *
     * @param task
     * @param actionRequest
     */
    protected void claimTask(ActRuTask task, TaskActionRequest actionRequest) {
        log.info("任务申明指定由某人完成");
        ActRuTask actRuTask = new ActRuTask();
        actRuTask.setId(task.getId());
        actRuTask.setClaimTime(LocalDateTime.now());
        Assert.notNull(actionRequest.getAssignee(), "申明信息不能为空");
        actRuTask.setDealingUserId(actionRequest.getAssignee());
        actRuTask.setAssignee(actionRequest.getAssignee());
        this.actRuTaskService.updateByPrimaryKeySelective(actRuTask);
    }

}

