package de.hpi.smm.meetup.features.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {
	
	static public void moveFile(String oldFilePath, String newFilePath, String fileName){
		
		File afile =new File(oldFilePath + fileName);

		if(afile.renameTo(new File(newFilePath + afile.getName()))){
			System.out.println("File has been moved successful!");
		}else{
			System.out.println("File has been failed to move!: " + oldFilePath + fileName);
		}
	}
	
	static private void renameFile(String filePath, String oldFileName, String newFileName){
		File oldfile =new File(filePath + oldFileName);
		File newfile =new File(filePath + newFileName);
 
		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed: " + filePath + oldFileName);
		}
	}
	
	static public int getNumberOfFilesIn(String filePath){
		int count = 0;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = filePath + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) count++;
			}
		}
		
		return count;
	}
	
	static public void renameAllFiles(String filePath, String prefix){
		int count = 0;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = filePath + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) {
					count++;
					renameFile(filePath, fileNameOnly, prefix + "_" + count +".txt");
				}
			}
		}
	}
	
	static public List<String> getRandomSelectFiles(String filePath, int portionInPercentage){
		int count = 0;
		List<String> allFiles = new LinkedList<String>();
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileNameOnly = file.getName();
				String fullFilePath = filePath + fileNameOnly;
				
				if (fullFilePath.endsWith(".txt")) {
					count++;
					allFiles.add(fileNameOnly);
				}
			}
		}
		
		int numberOfFilesToBeSelected = count * portionInPercentage / 100;
		
		Collections.shuffle(allFiles);
		return allFiles.subList(0, numberOfFilesToBeSelected);
	}
	
	static public void moveSelectedFiles(List<String> selectedFiles, String oldPath, String newPath){
		for (String selectedFile : selectedFiles){
			moveFile(oldPath, newPath, selectedFile);
		}
	}
	
	static public void findDuplicates(String fullFilePath) throws IOException{
		String sCurrentLine;
		String sPreviousLine = "";
		BufferedReader br = new BufferedReader(new FileReader(fullFilePath));
		while ((sCurrentLine = br.readLine()) != null) {
			if(sPreviousLine.toLowerCase().equals(sCurrentLine.toLowerCase())) System.out.println(sPreviousLine);
			sPreviousLine = sCurrentLine;
		}
	}
	
	
	
	
}


