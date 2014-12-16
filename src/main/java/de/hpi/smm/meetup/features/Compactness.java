package de.hpi.smm.meetup.features;

import java.io.IOException;

import de.hpi.smm.meetup.features.call.CompactnessCall;
import de.hpi.smm.meetup.features.tools.Counter;

public class Compactness {
	
	String string;
	CompactnessCall compactnessCall;
	
	public Compactness(String string) throws IOException{
		this.string = string;
		compactnessCall = new CompactnessCall(string);
	}
	
	public double getCompactnessByWords(){
		int numOfWords = Counter.countWords(string);
		int numOfKeyWords = compactnessCall.countKeyWords();
		return (double)numOfKeyWords*100/numOfWords;
	}
	
	public double getCompactnessByChars(){
		int numOfChars = Counter.countChars(string);
		int numOfKeyChars = compactnessCall.countChars();
		return (double)numOfKeyChars*100/numOfChars;
	}
	
}
