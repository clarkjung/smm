package de.hpi.smm.meetup.machinelearning.entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.hpi.smm.meetup.features.tools.Counter;

public class IndexEntity {
	
	ArrayList<TermEntity> indexWord;
	ArrayList<TermEntity> indexPhrase;
	ArrayList<ArrayList<TermEntity>> index;
	static String targetWordsListPath = "target_words/target_words_list.txt";
	
	public IndexEntity() throws IOException{
		this.indexWord = new ArrayList<TermEntity>();
		this.indexPhrase = new ArrayList<TermEntity>();
		this.index = new ArrayList<ArrayList<TermEntity>>();
		index.add(indexWord);
		index.add(indexPhrase);
		initializeIndex();
	}
	
	private void initializeIndex() throws IOException{
		
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(targetWordsListPath));
		
		while ((sCurrentLine = br.readLine()) != null) {
			addTerm(sCurrentLine);
		}
		br.close();
	}
	
	public void addTerm(String term){
		
		ArrayList<TermEntity> selectedIndex;
		
		if (Counter.countWords(term) == 1) {
			selectedIndex = indexWord;
		}else{
			selectedIndex = indexPhrase;
		}
		
		TermEntity termEntity = null;
		
		for (TermEntity termEntityItr : selectedIndex) {
			if (termEntityItr.getTerm().equals(term)){
				termEntity = termEntityItr;
				break;
			}
		}
		
		if (termEntity == null){
			termEntity = new TermEntity(term);
			selectedIndex.add(termEntity);
		}else{
			termEntity.increase();
		}
		
	}
	
	public ArrayList<ArrayList<TermEntity>> getIndex(){
		return index;
	}
	
	public ArrayList<TermEntity> getIndexWord(){
		return indexWord;
	}
	
	public ArrayList<TermEntity> getIndexPhrase(){
		return indexPhrase;
	}
	
	public int countAllTerms(){
		return indexWord.size() + indexPhrase.size();
	}
	
	public int countAllFrequency(){
		
		int count = 0;
		
		for ( ArrayList<TermEntity> selectedIndex : index){
		
			for (TermEntity termEntity : selectedIndex){
				count += termEntity.getFrequency();
			}
		}
		
		return count;
	}
	
	public void doSmoothing(){
		
		for ( ArrayList<TermEntity> selectedIndex : index){
		
			for (TermEntity termEntity: selectedIndex){
				termEntity.increase();
			}
		}
		
	}
	
	public boolean contains(String term){
		for ( ArrayList<TermEntity> selectedIndex : index){
			
			for (TermEntity termEntity : selectedIndex){
				if (termEntity.getTerm().equals(term)) return true;
			}
		}
		return false;
	}
	
	public boolean containsInIndexWord(String term){
		for (TermEntity termEntity : indexWord){
			if (termEntity.getTerm().equals(term)) return true;
		}
		return false;
	}
	
	public boolean containsInIndexPhrase(String term){
		for (TermEntity termEntity : indexPhrase){
			if (termEntity.getTerm().equals(term)) return true;
		}
		return false;
	}
	

	
}
