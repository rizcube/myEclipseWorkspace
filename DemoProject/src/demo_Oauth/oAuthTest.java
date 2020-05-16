// https://www.selenium.dev/downloads/
package demo_Oauth;
import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
/* 
https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth 
&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=abcd
*/

//  https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2FzwEFteggChiT3xaFscM2xHy365qlfuSk51BkZRA0sWpWBJNBNG-nHnovXwpXzWds9XhLNqaLl8tYgOFraOuQmDg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#

public class oAuthTest {
	
	
	public static void main(String[] args) throws InterruptedException {		
		// initialize the chrome browser
		/*System.setProperty("webdriver.chrome.driver", "/usr/local/bin");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/signin/oauth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=abcd&o2v=2&as=JWUmfoXrFOUpQylg4jlJTQ&flowName=GeneralOAuthFlow");
		driver.findElementByCssSelector("input[type='email']").sendKeys("rizcube");
		driver.findElementByCssSelector("input[type='email']").sendKeys(Keys.ENTER);
		// tagname[attribute ='value']
		Thread.sleep(3000);
		driver.findElementByCssSelector("input[type='password']").sendKeys("1EeshalAnabia100");
		driver.findElementByCssSelector("input[type='password']").sendKeys(Keys.ENTER);
		Thread.sleep(40000);
		String url = driver.getCurrentUrl();
		*/
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2FzwFbgBC9Mdhe0yNNeU-4rzHTI_gPsW2amqP0LXEYNADKXtBuUF93EDmgw4IqQZlKEACptHSIuPh5ZjEeTKOI4fo&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		
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
		
		
	}
}