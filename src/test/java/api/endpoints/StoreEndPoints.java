package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform create, read, update, delete requests from the user API

public class StoreEndPoints {
	
	public static Response placeOrderStore(Store store) {
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.body(store)
							 .when()
							 	.post(Routes.store_post_url);
		
		return response;
	}
	
	public static Response getPlacedOrderStore(int Id) {
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("Id", Id)
							.when()
								.get(Routes.store_get_url);
		
		return response;
	}
	
	public static Response deletePlacedOrderStore(int Id) {
		Response response = given()
								.accept(ContentType.JSON)
								.pathParam("Id", Id)
							.when()
								.get(Routes.store_delete_url);
		
		return response;
	}
	
	public static Response getInventoryDetailStore() {
		Response response = given()
								.accept(ContentType.JSON)
							.when()
								.get(Routes.store_getInventory_url);
		
		return response;
	}
}
