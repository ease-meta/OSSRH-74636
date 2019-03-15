package com.open.cloud.workflow.framework.controller;

import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.exception.BusinessException;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.ActAssignee;
import com.open.cloud.workflow.framework.entity.ActReProcdef;
import com.open.cloud.workflow.framework.entity.ReprocdefFlow;
import com.open.cloud.workflow.framework.entity.ReprocdefFlowLines;
import com.open.cloud.workflow.framework.entity.SeqNoPara;
import com.open.cloud.workflow.framework.serviceImpl.ActAssigneeServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.ActHiProcinstServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.ActReProcdefServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.ReprocdefFlowLinesServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.ReprocdefFlowServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.SeqNoParaServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程定义 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Slf4j
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActReProcdefController extends BaseController<ActReProcdef> {
	@Autowired
	ActReProcdefServiceImpl actReProcdefService;

	@Autowired
	SeqNoParaServiceImpl seqNoParaService;

	@Autowired
	ReprocdefFlowLinesServiceImpl reprocdefFlowLinesService;

	@Autowired
	ReprocdefFlowServiceImpl reprocdefFlowService;

	@Autowired
	ActAssigneeServiceImpl actAssigneeService;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	ActHiProcinstServiceImpl actHiProcinstService;

	@Resource
	private PlatformTransactionManager transactionManager;

	@Value(value = "${spring.config.location}/processes/")
	private String path;

	private final TransactionDefinition definition = new DefaultTransactionDefinition(
			TransactionDefinition.PROPAGATION_REQUIRES_NEW);

	/**
	 * 新建部署流程定义文件
	 *
	 * @return
	 * @throws java.io.IOException
	 * @throws java.io.FileNotFoundException
	 * @throws javax.xml.stream.XMLStreamException
	 */
	@SuppressWarnings("unchecked")
	@Resources(auth = AuthTypeEnum.OPEN, description = "新建部署流程定义文件")
	@ApiImplicitParams({@ApiImplicitParam(name = "fileName", dataType = "string", value = "流程部署文件名(需要完整文件名)", paramType = "query") })
	@PostMapping(value = { "/processes" })
	public synchronized Responses<Void> deply(
			@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
		if (StringUtils.isEmpty(path)) {
			return responses(500, "文流程定义文件路径配置异常");
		}
		log.info("流程定义文件解析{}", path);
		path = StringUtils.remove(path, "file:");
		Collection<File> list = FileUtils.listFiles(new File(path), null, true);
		String needfile = "";
		if (allRequestParams.containsKey("fileName")) {
			needfile = allRequestParams.get("fileName");
		}
		TransactionStatus status = transactionManager.getTransaction(definition);
		InputStream inputStream = null;
		try {
			for (File file : list) {
				String fileName = file.getName();
				if (StringUtils.isNotEmpty(needfile) && !file.getName().endsWith(needfile)) {
					continue;
				}

				if (!file.exists() || !file.getName().endsWith("bpmn")) {
					continue;
				}

				log.info("当前解析的流程定义文件:{}", fileName);
				inputStream = new FileInputStream(file);
				// activiti原始方式部署流程
				DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name(fileName)
						.addInputStream(fileName, inputStream);
				Deployment deployment = deploymentBuilder.deploy();
				String deploymentId = deployment.getId();
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
						.deploymentId(deploymentId).singleResult();
				log.info("流程定义文件 [{}]，流程ID [{}]", processDefinition.getName(), processDefinition.getId());
				BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
				// 一个部署文件只能定义一个流程
				Process process = bpmnModel.getProcesses().stream().filter(Objects::nonNull).findFirst().get();
				//如果已当前流程定义的流程实例存在，则不不可以进行重建
				if(!CollectionUtils.isEmpty(actHiProcinstService.selectByprocDefKey(processDefinition.getKey()))){
					throw  new BusinessException("有存在的任务未完成，当前流程不可重复部署");
				}
				// 1.数据库流程类型新增字段，每个流程的流水号类型一直
				SeqNoPara seqNoPara = new SeqNoPara();
				seqNoPara.setSeqType(processDefinition.getKey());
				seqNoPara.setMinSeq("1");
				seqNoPara.setMaxSeq("999999");
				seqNoPara.setCurrSeq("1");
				seqNoPara.setCountSeq("1");
				seqNoParaService.save(seqNoPara);
				// 2.保存流程定义类型
				ActReProcdef actReProcdef = new ActReProcdef();
				actReProcdef.setId(processDefinition.getId());
				actReProcdef.setRev(processDefinition.getVersion());
				actReProcdef.setCategory(processDefinition.getCategory());
				actReProcdef.setName(processDefinition.getName());
				actReProcdef.setKey(processDefinition.getKey());
				actReProcdef.setVersion(processDefinition.getVersion());
				actReProcdef.setDeploymentId(processDefinition.getDeploymentId());
				actReProcdef.setResourceName(processDefinition.getResourceName());
				actReProcdef.setDgrmResourceName(processDefinition.getDiagramResourceName());
				actReProcdef.setDescription(processDefinition.getDescription());
				actReProcdef.setSuspensionState(true);
				actReProcdef.setResourceTime(LocalDateTime.now());
				actReProcdefService.save(actReProcdef);
				// 3.保存流程定义的数据到表act_ge_bytearray
				// 4.解析流程定义的任务关联人或者组
				List<ReprocdefFlow> reprocdefFlowList = new LinkedList<>();
				List<ReprocdefFlowLines> reprocdefFlowLinesLinkedList = new LinkedList<>();
				List<FlowElement> flowElementList = process.getFlowElements().stream().filter(Objects::nonNull)
						.collect(Collectors.toList());
				int i = 0;
				for (FlowElement flowElement : flowElementList) {
					ReprocdefFlow reprocdefFlow = new ReprocdefFlow();
					ReprocdefFlowLines reprocdefFlowLines = new ReprocdefFlowLines();
					reprocdefFlow.setKey(processDefinition.getKey());
					reprocdefFlow.setSid(flowElement.getId());
					reprocdefFlow.setSidTitle(flowElement.getName());
					reprocdefFlow.setVersion(String.valueOf(processDefinition.getVersion()));
					reprocdefFlow.setInitNum(0);
					reprocdefFlow.setState(true);
					reprocdefFlow.setCreatetime(LocalDateTime.now());
					reprocdefFlow.setCreator("OS");

					reprocdefFlow.setSidType(flowElement.getClass().getSimpleName());
					if (flowElement instanceof UserTask) {

					}
					if (flowElement instanceof SequenceFlow) {
						reprocdefFlowLines.setKey(processDefinition.getKey());
						reprocdefFlowLines.setSid(flowElement.getId());
						reprocdefFlowLines.setId(++i);
						reprocdefFlowLines.setSidName(flowElement.getName());
						reprocdefFlowLines.setSidType("SequenceFlow");
						reprocdefFlowLines.setFromNode(((SequenceFlow) flowElement).getSourceFlowElement().getId());
						reprocdefFlowLines.setToNode(((SequenceFlow) flowElement).getTargetFlowElement().getId());
						reprocdefFlowLines.setExpr(((SequenceFlow) flowElement).getConditionExpression());
						reprocdefFlowLines.setExprValue(flowElement.getName());
						reprocdefFlowLines.setState(true);
						reprocdefFlowLinesLinkedList.add(reprocdefFlowLines);
					} else {
						reprocdefFlowList.add(reprocdefFlow);

					}
				}

				if (reprocdefFlowList.size() > 0) {
					reprocdefFlowService.deleteByPrimaryKey(process.getId());
					reprocdefFlowService.insertBatch(reprocdefFlowList);
				}
				if (reprocdefFlowLinesLinkedList.size() > 0) {
					reprocdefFlowLinesService.deleteByPrimaryKey(process.getId());
					reprocdefFlowLinesService.insertBatch(reprocdefFlowLinesLinkedList);
				}
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			log.error("数据库操作异常", e);
			throw new BusinessException("数据库操作异常", e);
		} finally {
			if (!Objects.isNull(inputStream)) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("fatal exception, {}",e);
				}
			}
		}
		return responses(HttpStatus.OK);
	}

	/**
	 * 停用或者启用流程
	 *
	 * @return
	 */
	@Resources(auth = AuthTypeEnum.OPEN, description = "停用或者启用流程")
	@RequestMapping(value = { "/procdef" }, method = { RequestMethod.PATCH })
	public Responses<Void> update(@RequestParam(value = "suspensionState", required = true) boolean suspensionState,
								  @RequestParam(value = "key", required = true) String key, HttpServletRequest request,
								  HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.info("前端系统传入的status值{}", suspensionState);
			log.info("前端系统传入的procdefkey值{}", key);
		}

		ActReProcdef actReProcdef = new ActReProcdef();
		actReProcdef.setKey(key);
		actReProcdef.setSuspensionState(suspensionState);
		int num = actReProcdefService.updateByKey(actReProcdef);
		if (num == 0) {
			return responses(404, "流程定义不存在");
		}
		if (!suspensionState) {
			repositoryService.suspendProcessDefinitionByKey(key);
		} else {
			repositoryService.activateProcessDefinitionByKey(key);
		}
		return responses(HttpStatus.OK);
	}

	/**
	 * @param request
	 * @param response
	 * @return http://127.0.0.1:8080/v1/procdef?key=123
	 */
	@Resources(auth = AuthTypeEnum.OPEN, description = "查询流程")
	@RequestMapping(value = { "/procdef" }, method = { RequestMethod.GET })
	public Responses<List<ActReProcdef>> get(HttpServletRequest request, HttpServletResponse response) {
		List<ActReProcdef> actReProcdefList = actReProcdefService.listAll();
		if (actReProcdefList != null && actReProcdefList.size() > 0) {
			return responses(HttpStatus.OK,
					actReProcdefList.stream()
							.collect(Collectors.collectingAndThen(
									Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getKey()))),
									ArrayList::new)));
		} else {
			return responses(HttpStatus.OK, actReProcdefList);
		}

	}

}
