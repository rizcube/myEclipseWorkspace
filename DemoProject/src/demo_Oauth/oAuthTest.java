package demo_Oauth;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
/* 
https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth 
&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com
&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=abcd
*/

//  https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2FzgEEcBinz9o-OJKnjU89AJ6twIdOQOySlka-OVj03eLukqJ-LzRL0OPfZBwvW3rXO8vsI9kdCIwOWJnPO6KUMMk&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#

public class oAuthTest {
	public static void main(String[] args) {		
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParam("code", "4%2FzgEEcBinz9o-OJKnjU89AJ6twIdOQOySlka-OVj03eLukqJ-LzRL0OPfZBwvW3rXO8vsI9kdCIwOWJnPO6KUMMk")
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
	System.out.println(js.getString("expires_in"));
	System.out.println(js.getString("scope"));
	
	String response = given().queryParam("access_token", accessToken)
	.when()
	.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
		
	}
}