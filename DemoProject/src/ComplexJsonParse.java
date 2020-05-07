import files.payload;
import io.restassured.path.json.JsonPath;
/*
Print No of courses returned by API
Print Purchase Amount
Print Title of the first course
Print All course titles and their respective prices
Print number of copies sold by RPA course
Verify if Sum of all course prices matches with purchase amount.
*/

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		// print total purchase amount.
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		// print title of the first course
		String titleFirstCourse = js.getString("courses[2].title");
		System.out.println(titleFirstCourse);
		System.out.println("-------------------------");
		// Print All course titles and their respective Prices
		for (int i = 0; i<count; i++) 
		{
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
		}	
		System.out.println("Print number of copies sold by RPA course");
		System.out.println("**--------*********-----********-------****");
		
		for (int i =0; i<count; i++) 
		{
			String courseTitles = js.getString("courses["+i+"].title");
			System.out.println(courseTitles);
			if (courseTitles.equalsIgnoreCase("RPA")) 
			{
				int copies = js.get("courses["+i+"].copies");
				System.out.println("copies are "+copies);
				break;
			}
		}
		
		// verify if sum of all course prices matches with purchase amount
		
		
	}
}
