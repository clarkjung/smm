package de.hpi.smm.meetup.machinelearning.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TrainFileEntity extends FileEntity{
	
	IndexEntity indexEntity;

	public TrainFileEntity(String fullFilePath) throws IOException {
		super(fullFilePath);
		indexEntity = new IndexEntity();	
	}
	
	public void analyzeText() throws IOException{
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
		
		while ((sCurrentLine = br.readLine()) != null) {
			
			lineAnalyze(sCurrentLine);
			wordAnalyze(sCurrentLine);
		}
		
		br.close();
	}
	
	public void printIndex(){
		for (ArrayList<TermEntity> selectedIndex : indexEntity.getIndex()){
			for (TermEntity termEntity: selectedIndex){
				System.out.println(termEntity.getTerm() + ": " + termEntity.getFrequency());
			}
		}
	}
	
	private void lineAnalyze(String sCurrentLine){
		for (TermEntity termEntity : indexEntity.getIndexPhrase()){
			if (sCurrentLine.toLowerCase().contains(termEntity.getTerm().toLowerCase())) termEntity.increase();
		}
	}
	
	private void wordAnalyze(String sCurrentLine){
		
		StringTokenizer st = new StringTokenizer(sCurrentLine);
		while (st.hasMoreElements()){
			String str = st.nextElement().toString();
			for (TermEntity termEntity : indexEntity.getIndexWord()){
				if (str.toLowerCase().equals(termEntity.getTerm().toLowerCase())) termEntity.increase();
			}
		}
	}
	
	public IndexEntity getIndexEntity(){
		return indexEntity;
	}

}
