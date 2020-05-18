import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class serializeTest {

	public static void main(String args[]) {
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
			
	String response = given().queryParam("key", "qaclick123").body("{\n" + 
			"  \"location\": {\n" + 
			"    \"lat\": -38.383494,\n" + 
			"    \"lng\": 33.427362\n" + 
			"  },\n" + 
			"  \"accuracy\": 50,\n" + 
			"  \"name\": \"rizcube Castle\",\n" + 
			"  \"phone_number\": \"(+91) 983 893 3937\",\n" + 
			"  \"address\": \"Spiritual Landing place\",\n" + 
			"  \"types\": [\n" + 
			"    \"shoe park\",\n" + 
			"    \"shop\"\n" + 
			"  ],\n" + 
			"  \"website\": \"http://google.com\",\n" + 
			"  \"language\": \"French-IN\"\n" + 
			"}")
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200)
	.extract().response().asString();
	
	System.out.println(response);
	
	}
}
