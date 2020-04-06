package com.yueyang.act;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

public class SaveGroup {
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = engine.getIdentityService();
		for (int i = 0; i < 10; i++) {
			Group group = identityService.newGroup(String.valueOf(i));
			group.setName("Group"+i);
			group.setType("Type_A");
			identityService.saveGroup(group);
		}
		engine.close();
		System.exit(0);
	
		
	
	}

}
