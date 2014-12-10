package de.hpi.smm.meetup.crossvalidation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class CrossValidation {
	
	ArrayList<ArrayList<String>> subsets;
	String formalPath, informalPath;
	int fold;
	int allFileListSize;
	
	public CrossValidation(String formalPath, String informalPath){
		this.formalPath = formalPath;
		this.informalPath = informalPath;
		this.fold = 10;
		this.allFileListSize = 0;
	}
	
	public CrossValidation(String formalPath, String informalPath, int fold){
		this(formalPath, informalPath);
		this.fold = fold;
	}
	
	public void printCV(){
		if(subsets == null){
			System.out.println("subsets not instaniated");
			return;
		}
		int size = subsets.size();
		System.out.println(size);
		System.out.println("all files size: " + allFileListSize);
		int sum = 0;
		for ( int i = 0 ; i < size; i ++ ){
			int size2 = subsets.get(i).size();
			System.out.println(size2);
			sum += size2;
		}
		System.out.println("sum: " + sum);
	}
	
	public int getFold(){
		return fold;
	}
	
	public ArrayList<ArrayList<String>> randomlyCreateSubsets(boolean isFormal){
		
		String folderPath;
		if(isFormal) folderPath = formalPath;
		else folderPath = informalPath;
				
		subsets = new ArrayList<ArrayList<String>>();
		ArrayList<String> allFileList = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = folderPath + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) {
					allFileList.add(fullFilePath);
				}
			}
		}
		
		Collections.shuffle(allFileList);
		allFileListSize = allFileList.size();
		
		int fromIndex = 0;
		ArrayList<Integer> assignList = assignSizeForSubset();
		for ( int i = 0 ; i < fold ; i ++ ){
			int toIndex = fromIndex + assignList.get(i);
			ArrayList<String> list = new ArrayList<String>(allFileList.subList(fromIndex, toIndex));
			subsets.add(list);
			fromIndex += assignList.get(i);
		}
		
		return subsets;
	}
	
	private ArrayList<Integer> assignSizeForSubset(){
		int baseNum = allFileListSize/fold;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for ( int i = 0 ; i < fold ; i ++ ){
			list.add(baseNum);
		}
		
		int gap = allFileListSize - baseNum*fold;
		for ( int i = 0 ; i < gap ; i++ ){
			int value = list.get(i);
			list.set(i, value + 1);
		}
		return list;
	}

}
