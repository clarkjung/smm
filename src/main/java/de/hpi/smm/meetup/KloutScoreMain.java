package de.hpi.smm.meetup;

import org.json.JSONException;

import de.hpi.smm.meetup.features.Klout;

public class KloutScoreMain {

	public static void main(String[] args) throws JSONException {
	      Klout klout = new Klout("barackobama");
	      double kloutScore = klout.getKloutScore();
	      System.out.println("Klout score of the twitter user @" + klout.getTwitterId() + " is " + kloutScore);
	}
}
