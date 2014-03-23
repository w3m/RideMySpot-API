package com.w3m.api.ridemyspot.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Users {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long id;
	@Persistent
	String name;
	@Persistent
	int type;
	@Persistent
	String adress;
	
	public Long getId() {
		return id;
	}
	  
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}
	
	public String getAdress(){
		return adress;
	}
	
	public void setAdress(String adress){
		this.adress = adress;
	}
	
}
