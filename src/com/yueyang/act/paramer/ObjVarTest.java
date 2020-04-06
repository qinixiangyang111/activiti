package com.yueyang.act.paramer;

import java.util.UUID;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class ObjVarTest {

	
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		TaskService taskService = engine.getTaskService();
		String taskId = UUID.randomUUID().toString();
		Task task = taskService.newTask(taskId);
		task.setName("测试参数");
		taskService.saveTask(task);


		
		//保存对象

		Person person = new Person();
		person.setId(12);
		person.setName("xiaohua");
		taskService.setVariable(taskId, "person1", person);
		
		
		//获取参数
		Person person2 = taskService.getVariable(taskId, "person1",Person.class);
		System.out.println(person2);
		
		engine.close();
	
	}
}
