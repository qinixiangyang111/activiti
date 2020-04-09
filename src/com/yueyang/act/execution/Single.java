package com.yueyang.act.execution;

import javax.sound.midi.Soundbank;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class Single {
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		//存储服务
		RepositoryService rService=engine.getRepositoryService();
	//运行时服务
		RuntimeService runtimeService=engine.getRuntimeService();
		//任务服务
		TaskService taskService=engine.getTaskService();
		Deployment deploy = rService.createDeployment().addClasspathResource("Single.bpmn").deploy();
		
		//流程实例
	
		ProcessDefinition definition = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(definition.getId());

	
System.out.println(processInstance.getId());
		
		engine.close();
		System.exit(0);
	}

	

}
