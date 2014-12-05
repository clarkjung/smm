package de.hpi.smm.meetup.machinelearning.entity;

public class TermEntity {

	String term;
	int frequency;
	
	public TermEntity(String term){
		this.term = term;
		frequency = 1;
	}
	
	public void increase(){
		frequency++;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public String getTerm(){
		return term;
	}
	
}
