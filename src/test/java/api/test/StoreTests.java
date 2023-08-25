package api.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {
	
	Faker faker;
	Store storePayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		storePayload = new Store();
		
		// timestamp format -> yyyy-MM-dd'T'HH:mm:ssZZZZ
		
		Date date = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");
		 String time = formatTime.format(date);
		
		storePayload.setId(faker.hashCode());
		storePayload.setPetId(faker.number().hashCode());
		storePayload.setQuantity(2);
		storePayload.setShipDate(time);
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
		
		// logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	@Test (priority = 1)
	void testPlaceOrderStore() {
		logger.info("******************* START testPlaceOrderStore() METHOD **********************");
		Response response = StoreEndPoints.placeOrderStore(storePayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************** END testPlaceOrderStore() METHOD *******************************");
	}
	
	
	@Test (priority = 2)
	void testGetOrderStore() {
		logger.info("******************* START testGetOrderStore() METHOD *******************************");
		Response response = StoreEndPoints.getPlacedOrderStore(storePayload.getId());
		System.out.println("ID: -------------> "+storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************* END testGetOrderStore() METHOD *******************************");
	}
	
	@Test (priority = 3)
	void testDeleteOrderStore() {
		logger.info("******************* START testDeleteOrderStore() METHOD *******************************");
		Response response = StoreEndPoints.deletePlacedOrderStore(storePayload.getId());
		System.out.println("ID: -------------> "+storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************* END testDeleteOrderStore() METHOD *******************************");
	}
	
	@Test (priority = 4)
	void testInventoryDetailStore() {
		logger.info("******************* START testInventoryDetailStore() METHOD *******************************");
		Response response = StoreEndPoints.getInventoryDetailStore();
		System.out.println("======== INVENTORY DETAIL ==========");
		response.then().log().all();
		JSONObject json = new JSONObject(response.asString());
		System.out.println("{{PetStatus-Updated}} ====> " + json.get("{{PetStatus-Updated}}"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(json.get("totvs"), 6);
		Assert.assertEquals(json.get("sold"), 2);
		Assert.assertEquals(json.get("{{PetStatus-Updated}}"), 1);
		logger.info("******************* END testInventoryDetailStore() METHOD *******************************");
	}
}
