package com.yueyang.act.execution;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class Start {
	
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		//存储服务
		RepositoryService rService=engine.getRepositoryService();
	//运行时服务
		RuntimeService runtimeService=engine.getRuntimeService();
		//任务服务
		TaskService taskService=engine.getTaskService();
		Deployment deploy = rService.createDeployment().addClasspathResource("start.bpmn").deploy();
		
		//流程实例
	
		ProcessDefinition definition = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(definition.getId());
		
		//流程定义的id,
		runtimeService.startProcessInstanceById(definition.getId(),"acbd");
		//流程文件的id
		//runtimeService.startProcessInstanceByKey("myProcess");
		//用户传输的信息启动流程
		//runtimeService.startProcessInstanceByMessage(arg0)
		
		
		

	
System.out.println(processInstance.getId());
		
		engine.close();
		System.exit(0);
	}


}
