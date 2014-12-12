package de.hpi.smm.meetup;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import de.hpi.smm.meetup.features.Compactness;

public class CompactnessMain {

	public static void main(String[] args) throws IOException {
        String text = "International Business Machines (IBM) has three ways to profit from corporate and government spending on technology — services, hardware, and software. And thanks to Big Data, Big Blue is poised to use all three to add millions to its bottom line. As he explained in an October 26 interview, Michael Schroeck, Global Information Management Leader, IBM Global Business Services, believes that IBM can help organizations using all three of those while tapping into a $64 billion market.";
        NumberFormat numberFormat = new DecimalFormat("###.##");
        
        Compactness compactness = new Compactness(text);
        System.out.println(
        		"Compactness by Characters: " + numberFormat.format(compactness.getCompactnessByChars()) + "%");
        System.out.println(
        		"Compactness by Words: " + numberFormat.format(compactness.getCompactnessByWords()) + "%");
	}
}
