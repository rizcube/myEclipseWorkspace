// Library API - completed in Postman now implementing it here
package files;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class dynamicJson {
	
	@Test
	public void addbook() 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response =given().body(payload.AddBook()).
		when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		JsonPath js = ReUseableMethods.rawToJson(response);
		//JsonPath js1 = new JsonPath(response);
		//System.out.println(js1.getString("ID"));
		String id =  js.getString("ID");
		System.out.println(id);
		
	}	
}
