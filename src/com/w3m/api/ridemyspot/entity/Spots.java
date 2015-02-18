package com.w3m.api.ridemyspot.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Spots {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long id;
	@Persistent
	Long idUser;
	@Persistent
	String name;
	@Persistent
	String description;
	@Persistent
	int type;
	@Persistent
	int score;
	@Persistent
	float totalNote;
	@Persistent
	int nbNote;
	@Persistent
	double latitude;
	@Persistent
	double longitude;
	@NotPersistent
	boolean favorite = false;
	@NotPersistent
	boolean hasScore = false;

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
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}
	
	public int getScore(){
		return score;
	}

	public void setScore(int score){
		this.score = score;
	}
	
	public void voteScore(boolean score){
		if(score){
			this.score++;
		} else {
			this.score--;
		}
	}
	
	public float getTotalNote(){
		return totalNote;
	}
	
	public void setTotalNote(float totalNote){
		this.totalNote = totalNote;
	}

	public int getNbNote(){
		return nbNote;
	}
	
	public void setNbNote(int nbNote){
		this.nbNote = nbNote;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public void addNote(float note){
		totalNote += note;
		nbNote += 1;
	}
	
	public void setFavorite(boolean favorite){
		this.favorite = favorite;
	}
	
	public boolean isFavorite(){
		return favorite;
	}
	
	public void setHasScore(boolean hasScore){
		this.hasScore = hasScore;
	}
	
	public boolean isHasScore(){
		return hasScore;
	}
	
}
