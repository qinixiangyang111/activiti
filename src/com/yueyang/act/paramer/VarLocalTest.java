package com.yueyang.act.paramer;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class VarLocalTest {
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		//得到流程服务组件
		RepositoryService rService=engine.getRepositoryService();
		RuntimeService runtimeService=engine.getRuntimeService();
		TaskService taskService=engine.getTaskService();
		Deployment deploy = rService.createDeployment().addClasspathResource("var_local.bpmn").deploy();
		
 ProcessDefinition processDefinition = rService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		System.out.println(deploy.getKey()+deploy.getName()+processDefinition.getId());
		//流程实例  流程启动后第一步
		ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());

	
	 
    // 普通员工完成请假的任务
	//查询当前任务到哪一步了
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
   
   taskService.setVariableLocal(task.getId(),"day",3);
   //作用域仅存在当前的任务中
   System.out.println("当前流程节点：" + task.getName()+"    "+taskService.getVariableLocal(task.getId(), "day"));
   taskService.complete(task.getId());
   
    task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    System.out.println("当前流程节点：" + task.getName()+"    "+taskService.getVariableLocal(task.getId(), "day"));
		
		engine.close();
		System.exit(0);
	}
	

}
