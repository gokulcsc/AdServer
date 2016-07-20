/*
 * Repository class for WebServices
 */
package org.AdServer.Services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class AdRepository {
	private static Map<String,String> adDetails = new HashMap<>();
	
	/* Responsible for publishing the ad
	 * 
	 */
	public String publishAd(String partnerId,int duration, String adContent, Date date){
		if(adDetails.containsKey(partnerId)){
			return "Error!! Duplicate partner id exists!";
		}
		else{
			adDetails.put(partnerId,"duration:"+duration+" "+"adContent:"+adContent+" "+"date:"+date);	
			return "One ad is published";
		}				 
	}
	
	/*
	 * Retrieves the content of all ads
	 */
	public Map<String,String> getAllAds(){
		return adDetails;
	}
	
	/*
	 * Retrieves a specific ad
	 */
	public String getAd(String id){
		return adDetails.get(id);
	}
	
	/*
	 * Check whether an ad exists
	 */
	public boolean adExists(String id){
		
		if(adDetails.containsKey(id)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * Parses JSON into map
	 */
	public AdContent parseJson(InputStream incomingAdData){
		StringBuilder builder = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingAdData));
		ObjectMapper mapper = new ObjectMapper();
		AdContent adContent = new AdContent();			
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				builder.append(line);
				Map<String, Object> jsonMap = mapper.readValue(line, Map.class);
				for(String a:jsonMap.keySet()){
					if(a.equals("adContent")){
						adContent.setAdContent((String)jsonMap.get(a));
					}
					else if(a.equals("duration")){
						adContent.setDuration((int)jsonMap.get(a));
					}
					else if(a.equals("partnerId")){
						adContent.setPartnerId((String)jsonMap.get(a));
				}
			}

			return adContent;
			}
		} 
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return adContent;
	}
}
	
