import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;
public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if Add Place API is working as expected.
		// given - all input details
		// when  - Submit the API
		// Then  - validate the response		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		given().log().all().queryParam("key", "qaclick").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)");
		
		// Add place > update Place with address 
		// Get Place to validate if New address is present in response
		
		
		
	}
}
