/*
 * REST resource class
 * http://localhost:8081/AdServer/rest/ad   GET=>Retrieves all the ads
 * http://localhost:8081/AdServer/rest/ad/{id}  GET=>Retrieves a specific ad if it exists
 * http://localhost:8081/AdServer/rest/ad   POST=>Publishes a new id if it doesn't exist
 */
package org.AdServer.Services;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;
import org.AdServer.Services.AdContent;

//http://localhost:8081/AdServer/rest/ad 
//http://localhost:8081/AdServer/rest/ad/<adnumber> 
//http://localhost:8081/AdServer/rest/ad  

@Path("/ad") 
public class AdResource {
	private AdRepository adRepository = new AdRepository();
	
	/*
	 * Retrieves all the ads
	 */
	@GET  
	@Produces(MediaType.APPLICATION_JSON)  
	public Map<String,String> getAllAds(){	 
		return adRepository.getAllAds();  
	}  
	
	/*
	 * Retrieves a specific ad
	 */
	@GET  
	@Path("{id: \\d+}")  
	@Produces(MediaType.TEXT_PLAIN)  
	public String getAdsById(@PathParam("id") String id){  
		if(adRepository.adExists(id)){
			return id +" "+adRepository.getAd(id);
		}
		else{
			return "Error.No id Found";
		}
	}  
	
	/*
	 * Publishes ad
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String createAd(InputStream incomingAdData){
		
		try {
			AdContent adContent = adRepository.parseJson(incomingAdData);
			return adRepository.publishAd(adContent.getPartnerId(),adContent.getDuration(),adContent.getAdContent(),new Date());
		} 
		catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		return "One ad is published";
	}
	 		 
}  

		

