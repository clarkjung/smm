package de.hpi.smm.meetup;

import java.io.IOException;

import de.hpi.smm.meetup.crossvalidation.CrossValidation;
import de.hpi.smm.meetup.machinelearning.MachineLearner;

public class FormalityMain {

	public static void main(String[] args) throws IOException {
    	String oldFilePath = "data/train/formal/";
    	String newFilePath = "data/test/formal/";
    	//String targetWordsPath = "target_words/";
    	String trainFormalPathMac = "data/train/formal/";
    	String trainInformalPathMac = "data/train/informal/";
    	String targetWordsPath = "target_words/";
    	String trainFormalPathWin = "data/train/formal/";
    	
    	CrossValidation cv = new CrossValidation(trainFormalPathMac, trainInformalPathMac);
    	MachineLearner machineLearner = new MachineLearner();
    	machineLearner.trainAndTest(cv);
    	System.out.println(machineLearner.getCVAccuracy());
	}
}
