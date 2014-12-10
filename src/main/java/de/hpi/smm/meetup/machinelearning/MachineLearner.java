package de.hpi.smm.meetup.machinelearning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import de.hpi.smm.meetup.crossvalidation.CrossValidation;
import de.hpi.smm.meetup.machinelearning.entity.FileEntity;
import de.hpi.smm.meetup.machinelearning.entity.TermEntity;
import de.hpi.smm.meetup.machinelearning.entity.TestFileEntity;
import de.hpi.smm.meetup.machinelearning.entity.TrainFileEntity;

public class MachineLearner {
	
	String trainFolderPath;
	String testFolderPath;
	String exportPath;
	double formalAccuracy, informalAccuracy, generalAccuracy;
	ArrayList<TrainFileEntity> trainFormalList, trainInformalList;
	ArrayList<TestFileEntity> testFormalList, testInformalList;
	HashMap<String, Integer> trainFormalMap, trainInformalMap;
	
	private MachineLearner(){
		this.formalAccuracy = 0;
		this.informalAccuracy = 0;
		this.generalAccuracy = 0;
		this.exportPath = null;
		this.trainFormalList = new ArrayList<TrainFileEntity>();
		this.trainInformalList = new ArrayList<TrainFileEntity>();
		this.testFormalList = new ArrayList<TestFileEntity>();
		this.testInformalList = new ArrayList<TestFileEntity>();
		this.trainFormalMap = new HashMap<String, Integer>();
		this.trainInformalMap = new HashMap<String, Integer>();
	}
	
	public MachineLearner(String trainFolderPath, String testFolderPath){
		this();
		this.trainFolderPath = trainFolderPath;
		this.testFolderPath = testFolderPath;
	}
	
	public MachineLearner(String trainFolderPath, String testFolderPath, String exportPath){
		this(trainFolderPath, testFolderPath);
		this.exportPath = exportPath;
	}
	
	public double getAccuracyForFormal(){
		return formalAccuracy;
	}
	
	public double getAccuracyForInformal(){
		return informalAccuracy;
	}
	
	public double getGeneralAccuracy(){
		return generalAccuracy;
	}
	
	private void reset(){
		this.formalAccuracy = 0;
		this.informalAccuracy = 0;
		this.generalAccuracy = 0;
		this.trainFormalList = new ArrayList<TrainFileEntity>();
		this.trainInformalList = new ArrayList<TrainFileEntity>();
		this.testFormalList = new ArrayList<TestFileEntity>();
		this.testInformalList = new ArrayList<TestFileEntity>();
		this.trainFormalMap = new HashMap<String, Integer>();
		this.trainInformalMap = new HashMap<String, Integer>();
	}
	
	public void train(CrossValidation cv){
		ArrayList<ArrayList<String>> formalSubsets = cv.randomlyCreateSubsets(true);
		ArrayList<ArrayList<String>> informalSubsets = cv.randomlyCreateSubsets(false);
		int fold = cv.getFold();
	}
	
	public void train() throws IOException{
		
		if(trainFolderPath == null || testFolderPath == null){
			System.out.println("train folder or test folder not given. ");
			return;
		}
		
		trainSubFolder("formal/", trainFormalList);
		trainSubFolder("informal/", trainInformalList);
		
		buildHashMap(trainFormalList, trainFormalMap);
		buildHashMap(trainInformalList, trainInformalMap);
		
		smoothing(trainFormalMap);
		smoothing(trainInformalMap);
		
		//exportHashMaps(trainFormalMap, "TrainFormalMap.txt");
		//exportHashMaps(trainInformalMap, "TrainInformalMap.txt");
	}
	
	private void exportHashMaps(HashMap<String, Integer> hashMap, String fileName) throws IOException{
		String fullFilePath = exportPath + fileName;
		File file = new File(fullFilePath);
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(),true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		for (String str : hashMap.keySet()){
			bufferWritter.write(str + ":" + hashMap.get(str) + "\n");
		}
		bufferWritter.close();
	}
	
	private void smoothing(HashMap<String, Integer> hashMap){
		for (String str : hashMap.keySet()){
			int strValue = hashMap.get(str);
			hashMap.put(str, strValue+1);
		}
	}
	
	private void trainSubFolder(String subFolder, ArrayList<TrainFileEntity> list) throws IOException{
		int count = 0;
		
		File folder = new File(trainFolderPath + subFolder);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = trainFolderPath + subFolder + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) {
					count++;
					
					TrainFileEntity fileEntity = new TrainFileEntity(fullFilePath);
					fileEntity.analyzeText();
					list.add(fileEntity);
					System.out.println(trainFolderPath + subFolder + ": " + count + " files done");
				}
			}
		}
		
	}
	
	private void buildHashMap(ArrayList<TrainFileEntity> list, HashMap<String, Integer> hashMap){
		for (TrainFileEntity fileEntity : list){
			for (TermEntity termEntity : fileEntity.getIndexEntity().getIndexWord()){
				addTermEntityToHashMap(termEntity, hashMap);
			}
			for (TermEntity termEntity : fileEntity.getIndexEntity().getIndexPhrase()){
				addTermEntityToHashMap(termEntity, hashMap);
			}
		}
	}
	
	private void addTermEntityToHashMap(TermEntity termEntity, HashMap<String, Integer> hashMap){
		String term = termEntity.getTerm();
		int freq = termEntity.getFrequency();
		
		if (hashMap.containsKey(term)){
			hashMap.put(term, hashMap.get(term) + freq);
		}else{
			hashMap.put(term, freq);
		}
	}
	
	public void test() throws IOException{
		testSubFolder("devTestFormal/", testFormalList, trainFormalMap, trainInformalMap);
		testSubFolder("devTestInformal/", testInformalList, trainFormalMap, trainInformalMap);
		
		getAccuracy();
	}
	
	private void getAccuracy(){
		//formal
		int formalCount = 0;
		for (TestFileEntity testFileEntity : testFormalList){
			if (testFileEntity.getGuess().equals("formal")) formalCount++;
		}
		formalAccuracy = formalCount*100/(double)testFormalList.size();
		
		//informal
		int informalCount = 0;
		for (TestFileEntity testFileEntity : testInformalList){
			if (testFileEntity.getGuess().equals("informal")) informalCount++;
		}
		informalAccuracy = informalCount*100/(double)testInformalList.size();
		
		generalAccuracy = (formalCount + informalCount) * 100 / (double) (testFormalList.size() + testInformalList.size());
	}
	
	private void testSubFolder(String subFolder, ArrayList<TestFileEntity> list, HashMap<String, Integer> trainFormalMap, HashMap<String, Integer> trainInformalMap) throws IOException{
		int count = 0;
		
		File folder = new File(testFolderPath + subFolder);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = testFolderPath + subFolder + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) {
					count++;
					
					TestFileEntity fileEntity = new TestFileEntity(fullFilePath);
					fileEntity.analyzeText(trainFormalMap, trainInformalMap);
					list.add(fileEntity);
					System.out.println(testFolderPath + subFolder + ": " + count + " files done");
				}
			}
		}
	}
}
