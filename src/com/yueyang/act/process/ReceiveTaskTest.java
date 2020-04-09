package com.yueyang.act.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;

public class ReceiveTaskTest {

	
public static void main(String[] args) {
	//创建流程引擎
	ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
	//存储服务
	RepositoryService rService=engine.getRepositoryService();
//运行时服务
	RuntimeService runtimeService=engine.getRuntimeService();
	//任务服务
	TaskService taskService=engine.getTaskService();
	Deployment deploy = rService.createDeployment().addClasspathResource("receiveTask.bpmn").deploy();
	ProcessDefinition pd = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
	
	//启动流程
	ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
	
	
 Execution execution = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions().singleResult();
	
	System.err.println(pi.getId()+":，当前节点"+execution.getActivityId());
	//让她往前走
	runtimeService.trigger(execution.getId());
	
	
 execution = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions().singleResult();
 System.err.println(pi.getId()+":，当前节点"+execution.getActivityId());
	
}
}
