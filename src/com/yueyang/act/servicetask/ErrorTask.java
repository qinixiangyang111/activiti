package com.yueyang.act.servicetask;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
/**
 * 无法执行的工作
 * @author xyb
 *
 */
public class ErrorTask {
public static void main(String[] args) {
	
	
	//创建流程引擎
			ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
			//存储服务
			RepositoryService rService=engine.getRepositoryService();
		//运行时服务
			RuntimeService runtimeService=engine.getRuntimeService();
			//任务服务
			TaskService taskService=engine.getTaskService();
			//管理服务组件
			ManagementService managementService = engine.getManagementService();
			Deployment deploy = rService.createDeployment().addClasspathResource("error_task.bpmn").deploy();
			//流程实例
			
			ProcessDefinition definition = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(definition.getId());
			System.err.println(processInstance.getId());
			
			
			Job job = managementService.createJobQuery().singleResult();
			//重试一次
			managementService.setJobRetries(job.getId(), 1);
			managementService.executeJob(job.getId());
			
}
}
