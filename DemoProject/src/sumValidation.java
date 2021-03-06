// https://testng.org/doc/download.html
// https://repo1.maven.org/maven2/com/beust/jcommander/1.78/
//https://github.com/google/guice/wiki/Guice422
// verify if sum of all course prices matches with purchase amount
import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class sumValidation {

	@Test
	public void sumOfcourses()
	{
		int sum = 0;
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		for (int i=0; i<count; i++) 
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;
			
		}
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);
	}
	
}
