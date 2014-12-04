package de.hpi.smm.meetup.features.call;

import java.io.IOException;
import java.util.Iterator;

import com.likethecolor.alchemy.api.Client;
import com.likethecolor.alchemy.api.call.AbstractCall;
import com.likethecolor.alchemy.api.call.RankedKeywordsCall;
import com.likethecolor.alchemy.api.call.type.CallTypeText;
import com.likethecolor.alchemy.api.entity.KeywordAlchemyEntity;
import com.likethecolor.alchemy.api.entity.Response;

import de.hpi.smm.meetup.features.tools.Counter;

public class CompactnessCall {
	
	static String apiKey = "5b74e2ad0f39a5916ee0597c2210032674d6f8c5";
	Client client;
	String string;
	Response<KeywordAlchemyEntity> keywordResponse;
	
	public CompactnessCall(String string) throws IOException{
		this.string = string;
		client = new Client(apiKey);
		AbstractCall<KeywordAlchemyEntity> keywordCall = new RankedKeywordsCall(new CallTypeText(string));
		keywordResponse = client.call(keywordCall);
	}
	
	public int countKeyWordSets(){
		return keywordResponse.size();
	}
	
	public int countKeyWords(){
		int counter = 0;
	    KeywordAlchemyEntity entity;
	    Iterator<KeywordAlchemyEntity> iter = keywordResponse.iterator();
	    while(iter.hasNext()) {
	    	entity = iter.next();
	    	counter += Counter.countWords(entity.getKeyword());
		}
	    return counter;
	}
	
	public int countChars(){
		int counter = 0;
	    KeywordAlchemyEntity entity;
	    Iterator<KeywordAlchemyEntity> iter = keywordResponse.iterator();
	    while(iter.hasNext()) {
	    	entity = iter.next();
	    	counter += Counter.countChars(entity.getKeyword());
		}
	    return counter;
	}

}