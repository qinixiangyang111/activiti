package com.yueyang.act.auth;

import java.util.List;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

public class OwnerTest {

	
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		
		TaskService taskService = engine.getTaskService();
		//任务应该和流程在一起
		String taskId = UUID.randomUUID().toString();
		Task newTask = taskService.newTask(taskId);
		newTask.setName("测试任务");
		taskService.saveTask(newTask);
		
		//创建用户
		IdentityService identityService = engine.getIdentityService();
		String userId = UUID.randomUUID().toString();
		User user = identityService.newUser(userId);
	
		user.setFirstName("aauu");
		user.setPassword("123");
		identityService.saveUser(user);
		
		//设置任务的候选用户组
		taskService.setOwner(taskId, userId);
		taskService.setAssignee(taskId, userId);
		
		
		List<Task> list = taskService.createTaskQuery().taskAssignee(userId).list();
		System.out.println(list.toString());
	
	}
}
