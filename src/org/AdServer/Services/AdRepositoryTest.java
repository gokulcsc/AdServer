/*
 * Contains JUNIT test cases for AdServer
 */
package org.AdServer.Services;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class AdRepositoryTest {
	private AdRepository adRepository; 
	private AdContent adContent;
	
	@Before
	public void setUp() {
		adRepository = new AdRepository();
		adContent = new AdContent();
	}
	
	@Test
	public void publishAdTest(){
		adContent.setAdContent("This promotes italian tourism");
		adContent.setDuration(120);
		adContent.setPartnerId("1");
		adContent.setCreated();
		String actual = adRepository.publishAd(adContent.getPartnerId(),adContent.getDuration(), adContent.getAdContent(),adContent.getCreated());
		String expected = "One ad is published";
		assertEquals(expected,actual);
	}
	
	/*
	 * Test when the same partner publishes the ad
	 */
	@Test
	public void publishDuplicateAdTest(){
		adContent.setAdContent("This promotes italian tourism");
		adContent.setDuration(120);
		adContent.setPartnerId("1");
		adContent.setCreated();
		String actual = adRepository.publishAd(adContent.getPartnerId(),adContent.getDuration(), adContent.getAdContent(),adContent.getCreated());
		String expected = "Error!! Duplicate partner id exists!";
		assertEquals(expected,actual);
	}

}
