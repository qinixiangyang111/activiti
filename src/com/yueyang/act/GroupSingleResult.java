package com.yueyang.act;



import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;

public class GroupSingleResult {
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = engine.getIdentityService();
		
	Group group = identityService.createGroupQuery().groupName("Group5").singleResult();

		
			System.out.println(group.getId()+group.getName()+group.getType());
			
			//多字段查询
//			List<Group> list = identityService.createGroupQuery().groupNameLike("Group").groupType("Type_A").list();
//			for (Group gg : list) {
//				System.err.println(gg.getId()+gg.getName()+gg.getType());
//			}
//		
			
			List<Group> list = identityService.createNativeGroupQuery().sql("select * from  act_id_group where name_ =#{name}").parameter("name", "Group5").list();
		
			for (Group gg : list) {
				System.err.println(gg.getId()+gg.getName()+gg.getType());
		}
			engine.close();
		System.exit(0);
	}

}
