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
		
	}
}
