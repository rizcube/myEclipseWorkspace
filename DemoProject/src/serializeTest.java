import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import okhttp3.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class serializeTest {

	public static void main(String args[]) {
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
			
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress("Spiritual Landing place");
	p.setLanguage("French-IN");
	p.setPhone_number("(+91) 983 893 3937");
	p.setName("rizcube Castle");
	p.setWebsite("http://google.com");
	
	List<String> myList = new ArrayList<String>(); 
	myList.add("shoe park");
	myList.add("shop");
	p.setTypes(myList);
	
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	
	p.setLocation(l);
	
	io.restassured.response.Response res = given().log().all().queryParam("key", "qaclick123").body(p)
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).extract().response();
	
	String responseString = res.asString();
	System.out.println(responseString);
	
	
	
	}
}
