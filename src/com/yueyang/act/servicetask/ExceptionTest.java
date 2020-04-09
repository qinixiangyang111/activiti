package com.yueyang.act.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ExceptionTest implements  JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) {
		// TODO Auto-generated method stub
		System.err.println("这是处理类");
		throw  new RuntimeException("总会失败");
	}

}
