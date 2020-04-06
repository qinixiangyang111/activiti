package com.yueyang.act.deployment;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

public class BpnModel {
	
	
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = engine.getRepositoryService();
		   // 存储服务
     
        
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addBpmnModel("My Process", createProcessModel());
        
        builder.deploy();
	
	}
	
	   private static BpmnModel createProcessModel() {
	        // 创建BPMN模型对象
	        BpmnModel model = new BpmnModel();
	        // 创建一个流程定义
	        org.activiti.bpmn.model.Process process = new org.activiti.bpmn.model.Process();
	        model.addProcess(process);
	        process.setId("myProcess");
	        process.setName("My Process");
	        // 开始事件
	        StartEvent startEvent = new StartEvent();
	        startEvent.setId("startEvent");
	        process.addFlowElement(startEvent);
	        // 用户任务
	        UserTask userTask = new UserTask();
	        userTask.setName("User Task");
	        userTask.setId("userTask");
	        process.addFlowElement(userTask);
	        // 结束事件
	        EndEvent endEvent = new EndEvent();
	        endEvent.setId("endEvent");
	        process.addFlowElement(endEvent);
	        // 添加流程顺序
	        process.addFlowElement(new SequenceFlow("startEvent", "userTask"));
	        process.addFlowElement(new SequenceFlow("userTask", "endEvent"));
	        return model;
	    }


}
