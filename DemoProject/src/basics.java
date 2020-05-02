import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUseableMethods;
import files.payload;
public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if Add Place API is working as expected.
		// given - all input details
		// when  - Submit the API
		// Then  - validate the response		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String response = given().log().all().queryParam("key", "qaclick").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();  // extract the response here and save in a variable
		
		// Add place > update Place with address 
		// Get Place to validate if New address is present in response
		System.out.println(response);
		// parse the json in order to extract place
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		System.out.println(placeId);
		
		
		// update Place
		
		String newAddress = "Summer Walk, Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n" + 
				"\"place_id\":\""+placeId+"\",\n" + 
				"\"address\":\""+newAddress+"\",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place  - validate if new address is present in the response
		 String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
		 JsonPath js1 = ReUseableMethods.rawToJson(getPlaceResponse);
		 String actualAddress = js1.getString("address");
		 System.out.println(actualAddress);
		 Assert.assertEquals(actualAddress, newAddress);
		 
	}
}
