package com.w3m.api.ridemyspot.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Comments {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long id;
	@Persistent
	Long idUser;
	@Persistent
	Long idSpot;
	@Persistent
	String text;
	@Persistent
	float note;
	@NotPersistent
	String user;
	
	
	public Long getId() {
		return id;
	}
	  
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	  
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public Long getIdSpot() {
		return idSpot;
	}
	  
	public void setIdSpot(Long idSpot) {
		this.idSpot = idSpot;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public float getNote(){
		return this.note;
	}
	
	public void setNote(float note){
		this.note = note;
	}
	

	public String getUser() {
		return user;
	}
	  
	public void setUser(String user) {
		this.user = user;
	}
}
