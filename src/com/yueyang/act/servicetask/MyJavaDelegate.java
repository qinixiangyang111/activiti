package com.yueyang.act.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class MyJavaDelegate implements  JavaDelegate{

	@Override
	public void execute(DelegateExecution arg0) {
		// TODO Auto-generated method stub
		System.out.println("这是处理类");
	}

}
