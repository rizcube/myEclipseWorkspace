// Library API - completed in Postman now implementing it here
// Feed Json payload from using excel using HashMap

package files;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class staticJson_practice {
	
	@Test
	public void addbook() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().body("{\n" + 
				"\"name\":\"Learn Appium Automation with Java_RA\",\n" + 
				"\"isbn\":\"EEAAA\",\n" + 
				"\"aisle\":\"5577\",\n" + 
				"\"author\":\"RALI\"\n" + 
				"\n" + 
				"}")
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println("add book request response is ");
		System.out.println(response);
		JsonPath js1 = new JsonPath(response);
		System.out.println(js1.getString("ID"));
		//JsonPath js1 = new JsonPath(response);
		String id = js1.getString("ID");
		System.out.println(id);
		//System.out.println(js1.getString("ID"));
			
		System.out.println("I am not getting here");	
		
			//RestAssured.baseURI = "http://216.10.245.166";
					
		/*
			.when().get("/Library/GetBook.php")
			.then().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js2 = new JsonPath(getbook);
			System.out.println("I am the ID extracted from through getbook request");
			System.out.println(js2.getString("ID"));
		*/
		}
		
}
/*
public void addbook() throws IOException 
{
	RestAssured.baseURI = "http://216.10.245.166";
	String response =given().body(GenerateStringFromResource("//Users//rizcube//eclipse-workspace//DemoProject//Addbookdetails.json")).
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
*/