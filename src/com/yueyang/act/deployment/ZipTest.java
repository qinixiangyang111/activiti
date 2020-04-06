package com.yueyang.act.deployment;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

public class ZipTest {
	
	public static void main(String[] args)  throws Exception{
		//创建流程引擎
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = engine.getRepositoryService();
		DeploymentBuilder builder = repositoryService.createDeployment();
	FileInputStream inputStream = new FileInputStream(new File("resource/test_datas.zip"));
	ZipInputStream zipInputStream = new ZipInputStream(inputStream);
	builder.addZipInputStream(zipInputStream);
	builder.deploy();
		
	}

}
