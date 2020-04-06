package com.yueyang.act.paramer;

import java.util.UUID;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class VarTest {

	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		TaskService taskService = engine.getTaskService();
		String taskId = UUID.randomUUID().toString();
		Task task = taskService.newTask(taskId);
		task.setName("测试参数");
		taskService.saveTask(task);
		
		//设置任务的参数 八种数据类型
		taskService.setVariable(task.getId(), "var1", "hello");
		
	
	}
}
