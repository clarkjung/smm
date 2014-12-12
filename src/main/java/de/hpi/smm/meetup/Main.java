package de.hpi.smm.meetup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import de.hpi.smm.meetup.crossvalidation.CrossValidation;
import de.hpi.smm.meetup.features.Compactness;
import de.hpi.smm.meetup.features.Klout;
import de.hpi.smm.meetup.features.tools.Counter;
import de.hpi.smm.meetup.features.tools.FileHandler;
import de.hpi.smm.meetup.machinelearning.MachineLearner;
import de.hpi.smm.meetup.machinelearning.entity.FileEntity;
import de.hpi.smm.meetup.machinelearning.entity.IndexEntity;
import de.hpi.smm.meetup.machinelearning.entity.TermEntity;


public class Main 
{
    public static void main( String[] args ) throws IOException, JSONException, ClassNotFoundException
    {


//        
//        // pos
//        MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");
//        String sample = "she likes it";
//        String tagged = tagger.tagString(sample);
//        System.out.println(tagged);
    	
//    	MachineLearner machineLearner = new MachineLearner(trainFormalPathMac);
//    	machineLearner.train("train_formal_2.txt");
    	
//    	IndexEntity indexEntity = new IndexEntity();
//    	boolean check = indexEntity.contains("rectify");
//    	System.out.println(check);
//    	check = indexEntity.contains("jir");
//    	System.out.println(check);
    	
//    	FileEntity fileEntity = new FileEntity(1, false, "/Users/jaeyoonjung/git/smm/data/train/informal/" + "test_informal_1.txt");
//    	fileEntity.train();
    	//fileEntity.printIndex();
    	
    	//FileHandler.moveSelectedFiles(FileHandler.getRandomSelectFiles("/Users/jaeyoonjung/git/smm/data/train/devTestInformal/", 50), "/Users/jaeyoonjung/git/smm/data/train/devTestInformal/", "/Users/jaeyoonjung/git/smm/data/train/informal/");
    	//FileHandler.renameAllFiles("/Users/jaeyoonjung/git/smm/data/train/devTestInformal/", "devTest_informal");
    	 
//    	String trainFolderPath = "/Users/jaeyoonjung/git/smm/data/train/";
//    	String testFolderPath = "/Users/jaeyoonjung/git/smm/data/train/";
//    	MachineLearner machineLearner = new MachineLearner(trainFolderPath, trainFolderPath);
//    	machineLearner.train();
    	//machineLearner.test();
    	//double accuracyForFormal = machineLearner.getAccuracyForFormal();
    	//double accuracyForInformal = machineLearner.getAccuracyForInformal();
    	//System.out.println(accuracyForFormal + ", " + accuracyForInformal + ", " + machineLearner.getGeneralAccuracy());
    	
    }
    	
}
