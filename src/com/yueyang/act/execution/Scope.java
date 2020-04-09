package com.yueyang.act.execution;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class Scope {
	
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		//存储服务
		RepositoryService rService=engine.getRepositoryService();
	//运行时服务
		RuntimeService runtimeService=engine.getRuntimeService();
		//任务服务
		TaskService taskService=engine.getTaskService();
		Deployment deploy = rService.createDeployment().addClasspathResource("scope.bpmn").deploy();
		
		//流程实例
	
		ProcessDefinition definition = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(definition.getId());
		
		//流程定义的id,
		ProcessInstance pi = runtimeService.startProcessInstanceById(definition.getId());
	
		List<Task> list = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
		
		//System.err.println(list.toString());
		for(Task  task:list){
			
			//查询执行流
			Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
			
			if("taskA".equals(task.getName())){
				//当前本地实例变量
				runtimeService.setVariableLocal(execution.getId(), "taskVarA","varAAAA");
			}else{
				runtimeService.setVariable(execution.getId(), "taskVarB","varBBBBB");
			}
		}
		
		//两个执行流时输出参数
		
		for(Task task:list){
			
			taskService.complete(task.getId());
		}

		Task taskc = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.err.println( runtimeService.getVariable(pi.getId(), "taskVarA"));
		System.err.println( runtimeService.getVariable(pi.getId(), "taskVarB"));
		
		
System.err.println(pi.getId());
	

		
		engine.close();
		System.exit(0);
	}


}
