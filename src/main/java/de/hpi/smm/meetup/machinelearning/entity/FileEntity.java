package de.hpi.smm.meetup.machinelearning.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileEntity {

	String fullFilePath;
	
	public FileEntity(String fullFilePath) throws IOException{
		this.fullFilePath = fullFilePath;
	}
}
