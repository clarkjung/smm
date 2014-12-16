package de.hpi.smm.meetup.machinelearning.entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import de.hpi.smm.meetup.features.tools.Counter;

public class TestFileEntity extends FileEntity {
	
	String guessedAs;
	double logSumFormal, logSumInformal;
	int countFormal, countInformal;
	int trainFormalMapAllFreq, trainInformalMapAllFreq;

	public TestFileEntity(String fullFilePath) throws IOException {
		super(fullFilePath);
		this.guessedAs = "";
		this.logSumFormal = 0.0;
		this.logSumInformal = 0.0;
		this.countFormal = 0;
		this.countInformal = 0;
		this.trainFormalMapAllFreq = 0;
		this.trainInformalMapAllFreq = 0;
	}
	
	public String getGuess(){
		return guessedAs;
	}
	
	public double getLogSumFormal(){
		return logSumFormal;
	}
	
	public double getLogSumInformal(){
		return logSumInformal;
	}
	
	public int getTrainFormalMapAllFreq(){
		return trainFormalMapAllFreq;
	}
	
	public int getTrainInformalMapAllFreq(){
		return trainInformalMapAllFreq;
	}
	
	public int getCountFormal(){
		return countFormal;
	}
	
	public int getCountInformal(){
		return countInformal;
	}
	
	private void guess(){
		if (logSumFormal/countFormal >= logSumInformal/countInformal) guessedAs = "formal";
		else guessedAs = "informal";
	}
	
	public void analyzeText(HashMap<String, Integer> trainFormalMap, HashMap<String, Integer> trainInformalMap) throws IOException{
		
//		System.out.println("logSumFormal: " + getLogSumFormal());
//		System.out.println("logSumInformal: " + getLogSumInformal());
		
		trainFormalMapAllFreq = countAllFreq(trainFormalMap);
		trainInformalMapAllFreq = countAllFreq(trainInformalMap);
		
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
		
		while ((sCurrentLine = br.readLine()) != null) {
				
				wordAnalyze(sCurrentLine, trainFormalMap, trainInformalMap);
				lineAnalyze(sCurrentLine, trainFormalMap, trainInformalMap);
		}
		br.close();
		
		guess();
	}
	
	private int countAllFreq(HashMap<String, Integer> hashMap){
		int sum = 0;
		for (int freq : hashMap.values()){
			sum += freq;
		}
		return sum;
	}
	
	private void lineAnalyze(String sCurrentLine, HashMap<String, Integer> trainFormalMap, HashMap<String, Integer> trainInformalMap){
		
		
		//trainFormalMap
		for (String term : trainFormalMap.keySet()){
			if (Counter.countWords(term) != 1 && sCurrentLine.toLowerCase().contains(term.toLowerCase())){
				countFormal++;
				int getTermFreq = trainFormalMap.get(term);
				double mathLog = Math.log(getTermFreq/(double)trainFormalMapAllFreq);
				logSumFormal += mathLog;
			}
		}
		
		//trainInformalMap
		for (String term : trainInformalMap.keySet()){
			if (Counter.countWords(term) != 1 && sCurrentLine.toLowerCase().contains(term.toLowerCase())){
				countInformal++;
				int getTermFreq = trainInformalMap.get(term);
				logSumInformal += Math.log(getTermFreq/(double)trainInformalMapAllFreq);
			}
		}
		
	}
	
	private void wordAnalyze(String sCurrentLine, HashMap<String, Integer> trainFormalMap, HashMap<String, Integer> trainInformalMap){
		
		StringTokenizer st = new StringTokenizer(sCurrentLine);
		while (st.hasMoreElements()){
			String str = st.nextElement().toString();
			
			//trainFormalMap
			for (String term : trainFormalMap.keySet()){
				if (Counter.countWords(term) == 1 && term.toLowerCase().equals(str.toLowerCase())){
					countFormal++;
					int getTermFreq = trainFormalMap.get(term);
					double mathLog = Math.log(getTermFreq/(double)trainFormalMapAllFreq);
					logSumFormal += mathLog;
				}
			}
			
			//trainInformalMap
			for (String term : trainInformalMap.keySet()){
				if (Counter.countWords(term) == 1 && term.toLowerCase().equals(str.toLowerCase())){
					countInformal++;
					logSumInformal += Math.log(trainInformalMap.get(term)/(double)trainInformalMapAllFreq);
				}
			}
		}
	}

}
