package de.hpi.smm.meetup.machinelearning.entity;

import java.util.ArrayList;

public class IndexEntity {
	
	ArrayList<TermEntity> index;
	
	public IndexEntity(){
		index = new ArrayList<TermEntity>();
	}
	
	public void addTerm(String term){
		
		TermEntity termEntity = null;
		
		for (TermEntity termEntityItr : index) {
			if (termEntityItr.getTerm().equals(term)){
				termEntity = termEntityItr;
				break;
			}
		}
		
		if (termEntity == null){
			termEntity = new TermEntity(term);
			index.add(termEntity);
		}else{
			termEntity.increase();
		}
	}
	
	public ArrayList<TermEntity> getIndex(){
		return index;
	}
	

	
}
