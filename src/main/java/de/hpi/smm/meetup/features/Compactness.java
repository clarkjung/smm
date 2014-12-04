package de.hpi.smm.meetup.features;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		
		NumberFormat numberFormat = new DecimalFormat("###.##");
		
		int numOfWords = Counter.countWords(string);
		int numOfKeyWords = compactnessCall.countKeyWords();
		double compactness = (double)numOfKeyWords*100/numOfWords;
		
		return Double.parseDouble(numberFormat.format(compactness));
	}
	
	public double getCompactnessByChars(){
		
		NumberFormat numberFormat = new DecimalFormat("###.##");
		
		int numOfChars = Counter.countChars(string);
		int numOfKeyChars = compactnessCall.countChars();
		double compactness = (double)numOfKeyChars*100/numOfChars;
		
		return Double.parseDouble(numberFormat.format(compactness));
	}
	
}
