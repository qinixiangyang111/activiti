package com.yueyang.act;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

public class GroupListPage {
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = engine.getIdentityService();
		
		List<Group> list = identityService.createGroupQuery().orderByGroupId().desc().orderByGroupName().asc().listPage(2, 5);
		for (Group group : list) {
			System.out.println(group.getId()+group.getName()+group.getType());
		}
		engine.close();
		System.exit(0);
		
		engine.close();
		System.exit(0);
	}

}
