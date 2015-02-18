package com.w3m.api.ridemyspot.entity;

import java.util.ArrayList;

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
	String type;
	@Persistent
	String adress;
	@Persistent
	ArrayList<String> favorite = new ArrayList<String>();
	@Persistent
	ArrayList<String> vote = new ArrayList<String>();
	
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
	
	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}
	
	public String getAdress(){
		return adress;
	}
	
	public void setAdress(String adress){
		this.adress = adress;
	}

	public ArrayList<String> getFavorite(){
		return favorite;
	}
	
	public void setFavorite(ArrayList<String> favorite){
		if(this.favorite != null)
			this.favorite.clear();
		this.favorite.addAll(favorite);
	}
	
	public void addFavorite(String favorite){
		this.favorite.add(favorite);
	}
	
	public void removeFavorite(String favorite){
		this.favorite.remove(favorite);
	}

	public ArrayList<String> getVote(){
		return vote;
	}
	
	public void setVote(ArrayList<String> vote){
		if(this.vote != null)
			this.vote.clear();
		this.vote.addAll(vote);
	}
	
	public void addVote(String vote){
		this.vote.add(vote);
	}
	
	public void removeVote(String vote){
		this.vote.remove(vote);
	}
}
