package de.hpi.smm.meetup;

import java.io.IOException;

import org.json.JSONException;

import de.hpi.smm.meetup.features.Compactness;
import de.hpi.smm.meetup.features.Klout;


public class Main 
{
    public static void main( String[] args ) throws IOException, JSONException
    {
    	// Compactness
        String text = "International Business Machines (IBM) has three ways to profit from corporate and government spending on technology — services, hardware, and software. And thanks to Big Data, Big Blue is poised to use all three to add millions to its bottom line. As he explained in an October 26 interview, Michael Schroeck, Global Information Management Leader, IBM Global Business Services, believes that IBM can help organizations using all three of those while tapping into a $64 billion market.";
        Compactness compactness = new Compactness(text);
        System.out.println("Compactness by Characters: " + compactness.getCompactnessByChars() + "%");
        System.out.println("Compactness by Words: " + compactness.getCompactnessByWords() + "%");
        
        // KloutScore
        Klout klout = new Klout("barackobama");
        double kloutScore = klout.getKloutScore();
        System.out.println("Klout score of the twitter user @" + klout.getTwitterId() + " is " + kloutScore);
    }
    	
}
