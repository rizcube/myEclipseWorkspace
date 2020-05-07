import files.payload;
import io.restassured.path.json.JsonPath;

// verify if sum of all course prices matches with purchase amount
public class sumValidation_RA {
	public static void main(String args[]) {
		// 
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int sum = 0;
		
		for (int i=0; i<count; i++) {
			String courseTitles = js.getString("courses["+i+"].title");
			int coursePrices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			
			int coursesSum = coursePrices * copies;
			sum = coursesSum + sum;
			
			System.out.println(sum);
			//System.out.println(courseTitles);
			//System.out.print(copies);
			//System.out.print(coursePrices);
			
			int amount = js.getInt("dashboard.purchaseAmount");
			System.out.println(amount);
			
			if (sum == amount) 
			{
				System.out.println("Verified");
			}
		};	
	}
}
