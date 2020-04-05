package com.yueyang.act;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class FirstAct {
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		//得到流程服务组件
		RepositoryService rService=engine.getRepositoryService();
		RuntimeService runtimeService=engine.getRuntimeService();
		TaskService taskService=engine.getTaskService();
		rService.createDeployment().addClasspathResource("first.bpmn").deploy();
		//流程实例
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProcess");

	
	 
    // 普通员工完成请假的任务
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    System.out.println("当前流程节点：" + task.getName());
    taskService.complete(task.getId());
    
    // 经理审核任务
    task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    System.out.println("当前流程节点：" + task.getName());
    taskService.complete(task.getId());
    
    task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    System.out.println("流程结束后：" + task);
		
		engine.close();
		System.exit(0);
	}

}
