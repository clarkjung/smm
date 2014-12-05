package de.hpi.smm.meetup.machinelearning.entity;

public class FileEntity {

	int id;
	int length;
	boolean isFormal;
	IndexEntity indexEntity;
	
	public FileEntity(int id, boolean isFormal){
		this.id = id;
		this.isFormal = isFormal;
		this.length = 0;
		indexEntity = new IndexEntity();	
	}
	
	public int getId(){
		return id;
	}
	
	public int getLength(){
		return length;
	}
	
	public boolean isFormal(){
		return isFormal;
	}
	
	
	
}
