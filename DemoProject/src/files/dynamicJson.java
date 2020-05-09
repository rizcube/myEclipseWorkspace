// Library API - completed in Postman now implementing it here
package files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class dynamicJson {
	
	@Test(dataProvider = "BooksData")
	public void addbook(String isbn, String aisle) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response =given().body(payload.AddBook(isbn, aisle)).
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

@DataProvider(name = "BooksData")  // using data provider annotation
public Object[][] getData() 
{
	// array is the collection of elements Example! new object [] {2,3,4,5}
	// multidimensional is collection of arrays.
	return new Object[][] {{"Book1","0001"}, {"Book2","0002"}, {"Book3","0003"}, {"Book4","0004"}};
}






}


// dynamically build json payload with external data inputs
// Parameterize the API test with multiple data sets
// How to send static Json files(payload) directly into Post Method of Rest Assured
// Feed Json payload from using excel usign HashMap




