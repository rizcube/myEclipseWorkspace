import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate if Add Place API is working as expected.
		// given - all input details
		// when  - Submit the API
		// Then  - validate the response
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		given().log().all().queryParam("key", "qaclick").header("Content-Type","application/json")
		.body("{\n" + 
				"  \"location\": {\n" + 
				"    \"lat\": -38.383494,\n" + 
				"    \"lng\": 33.427362\n" + 
				"  },\n" + 
				"  \"accuracy\": 50,\n" + 
				"  \"name\": \"rizcube house\",\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\n" + 
				"  \"address\": \"Spiritual Landing place\",\n" + 
				"  \"types\": [\n" + 
				"    \"shoe park\",\n" + 
				"    \"shop\"\n" + 
				"  ],\n" + 
				"  \"website\": \"http://google.com\",\n" + 
				"  \"language\": \"French-IN\"\n" + 
				"}").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)");
		
	}
}
