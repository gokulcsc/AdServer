package org.AdServer.Services;
/*
 * This is a POJO class for setting and retrieving contents of the ad
 */
import java.util.Date;
public class AdContent {
	private String partnerId;
	private int duration;
	private String adContent;
	private Date created;
	
	public AdContent(){
	}
	
	public AdContent(String partnerId, int duration, String adContent, Date created){
		super();
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
		this.created = created;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated() {
		this.created = new Date();
	}
	
	public String getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getAdContent() {
		return adContent;
	}
	
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

}

