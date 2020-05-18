// https://www.selenium.dev/downloads/
package demo_Oauth;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.api;
import pojo.webAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
/* 
https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth 
&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=abcd
*/

//  https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2FzwEFteggChiT3xaFscM2xHy365qlfuSk51BkZRA0sWpWBJNBNG-nHnovXwpXzWds9XhLNqaLl8tYgOFraOuQmDg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#

public class oAuthTest {
	
	
	public static void main(String[] args) throws InterruptedException {		
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2FzwGvdOF1csnfV-dz9gZ5qZIM2upgOn2W3c4AZK43d1WGzu7Q__FzUlSQIfNN7oOIacV57Il0xikh9m8ieSZZV14&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println("/i am new code");
		System.out.println(code);
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code")
		.when()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();		
		System.out.println(accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
	
		System.out.println(accessTokenResponse);	
		String accessToken = js.getString("access_token");
		GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getExpertise());
		
		//query1: find SOAPUI webservices testing and price
		gc.getCourses().getApi().get(1).getCourseTitle();
		
		System.out.println("Dynamically getting course price");
		List<api> apiCourses = gc.getCourses().getApi();
		System.out.println(apiCourses.size());
		for (int i =0; i< apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"));
			{
				//System.out.println(apiCourses.get(i).getCourseTitle());
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		// Query 2: get all the course titles of api course
					System.out.println("Get all course titles");
					System.out.println(apiCourses.size());
					
				for (int j=0; j< apiCourses.size(); j++) {
					
					System.out.println(apiCourses.get(j).getCourseTitle());	
		
		}
	
		System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle());	
		System.out.println("Query 3 Get all courses under webAutomationCourses");
		
		ArrayList<String> a = new ArrayList<String>();
		
		List<webAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		
		System.out.println(webAutomationCourses.size());
		
		for (int i = 0; i< webAutomationCourses.size(); i++) {
		//	System.out.println(webAutomationCourses.get(i).getCourseTitle());
		a.add(webAutomationCourses.get(i).getCourseTitle());
		}
		System.out.println(a);
		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
		System.out.println(expectedList);
	}
}