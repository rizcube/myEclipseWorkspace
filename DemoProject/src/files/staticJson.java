// Use this if you are using external json file this could be in the case of bank accounts 

package files;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class staticJson {	
	@Test
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
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	
}
