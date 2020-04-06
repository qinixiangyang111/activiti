package com.yueyang.act.paramer;

import java.io.Serializable;

import lombok.Data;



@Data
public class Person implements Serializable {

	private  Integer  id;
	
	private String name;
	
	
	public  void setId(Integer id){
		this.id=id;
	}
	
	public  void setName(String name){
		this.name=name;
	}
	
}
