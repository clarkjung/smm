package de.hpi.smm.meetup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import de.hpi.smm.meetup.features.Compactness;
import de.hpi.smm.meetup.features.Klout;
import de.hpi.smm.meetup.features.tools.Counter;
import de.hpi.smm.meetup.features.tools.FileHandler;
import de.hpi.smm.meetup.machinelearning.MachineLearner;
import de.hpi.smm.meetup.machinelearning.entity.FileEntity;
import de.hpi.smm.meetup.machinelearning.entity.IndexEntity;
import de.hpi.smm.meetup.machinelearning.entity.TermEntity;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Main 
{
    public static void main( String[] args ) throws IOException, JSONException, ClassNotFoundException
    {
//    	// Compactness
//        String text = "International Business Machines (IBM) has three ways to profit from corporate and government spending on technology — services, hardware, and software. And thanks to Big Data, Big Blue is poised to use all three to add millions to its bottom line. As he explained in an October 26 interview, Michael Schroeck, Global Information Management Leader, IBM Global Business Services, believes that IBM can help organizations using all three of those while tapping into a $64 billion market.";
//        Compactness compactness = new Compactness(text);
//        System.out.println("Compactness by Characters: " + compactness.getCompactnessByChars() + "%");
//        System.out.println("Compactness by Words: " + compactness.getCompactnessByWords() + "%");
//        
//        // KloutScore
//        Klout klout = new Klout("barackobama");
//        double kloutScore = klout.getKloutScore();
//        System.out.println("Klout score of the twitter user @" + klout.getTwitterId() + " is " + kloutScore);
//        
//        // pos
//        MaxentTagger tagger = new MaxentTagger("taggers/left3words-wsj-0-18.tagger");
//        String sample = "she likes it";
//        String tagged = tagger.tagString(sample);
//        System.out.println(tagged);
    
    	String oldFilePath = "Y:/Projects/AlchemyAPI/smm/data/train/formal/";
    	String newFilePath = "Y:/Projects/Alche0myAPI/smm/data/test/formal/";
    	//String targetWordsPath = "Y:/Projects/AlchemyAPI/smm/target_words/";
    	String trainFormalPathMac = "/Users/jaeyoonjung/git/smm/data/train/formal/";
    	String targetWordsPath = "/Users/jaeyoonjung/git/smm/target_words/";
    	
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
    	FileHandler.renameAllFiles("/Users/jaeyoonjung/git/smm/data/train/devTestInformal/", "devTest_informal");
    	 
//    	String trainFolderPath = "/Users/jaeyoonjung/git/smm/data/train/";
//    	String testFolderPath = "/Users/jaeyoonjung/git/smm/data/train/";
//    	MachineLearner machineLearner = new MachineLearner(trainFolderPath, trainFolderPath);
//    	machineLearner.train();
//    	machineLearner.test();
//    	double accuracyForFormal = machineLearner.getAccuracyForFormal();
//    	double accuracyForInformal = machineLearner.getAccuracyForInformal();
//    	System.out.println(accuracyForFormal + ", " + accuracyForInformal + ", " + machineLearner.getGeneralAccuracy());
    	
//    	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
//    	hashMap.put("test", 1);
//    	hashMap.put("test2", 3);
//    	for (String str : hashMap.keySet()){
//    		System.out.println(str);
//    	}

    	
    }
    	
}
