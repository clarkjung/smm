package de.hpi.smm.meetup.features;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Klout {

	String twitterId;
	String kloutId;
	double kloutScore;
	static String KloutAPIKey = "bse4cn98ht3xwjq63d9e2tte"; // Jaeyoon's Klout API Key
															

	public Klout(String twitterId) {
		this.twitterId = twitterId;
	}

	public double getKloutScore() throws JSONException {
		FindKloutId();
		FindKloutScore();
		return kloutScore;
	}

	private void FindKloutId() throws JSONException {
		String url = "http://api.klout.com/v2/identity.json/twitter?screenName="
				+ twitterId + "&key=" + KloutAPIKey;
		String jsonString = callURL(url);
		JSONObject obj = new JSONObject(jsonString);
		String id = obj.getString("id");
		this.kloutId = id;
	}

	private void FindKloutScore() throws JSONException {
		String url = "http://api.klout.com/v2/user.json/" + kloutId
				+ "/score?key=" + KloutAPIKey;
		String jsonString = callURL(url);
		JSONObject obj = new JSONObject(jsonString);
		double score = obj.getDouble("score");
		this.kloutScore = score;
	}

	private String callURL(String myURL) {
		//System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}
		return sb.toString();
	}
	
	public String getTwitterId(){
		return twitterId;
	}
	
	public String getKloutId(){
		return kloutId;
	}
	
	public String getKloutAPIKey(){
		return KloutAPIKey;
	}
	
	
}
