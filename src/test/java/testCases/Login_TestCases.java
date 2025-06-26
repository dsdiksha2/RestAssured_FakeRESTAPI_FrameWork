package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.LoginPOJO;
import routes_Endpoints.routes;



public class Login_TestCases extends BaseClass{
	
	
	
	@Test@Ignore
	public void testInvalidUserLogin() {
		
		LoginPOJO invalidlogin = Payload.loginPayload();
		
		given()
			.contentType(ContentType.JSON)
			.body(invalidlogin)
		.when()
			.post(routes.AUTH_LOGIN)
		.then()
			.log().body()
			.statusCode(401) // Expecting 401 for unauthorized access
			.body(equalTo("username or password is incorrect"));  //Validate error message in response body
		
	}

	@Test
	public void testValidUserLogin() {
		
		String un = config.getProperty("username");
		String pwd = config.getProperty("password");
		
		//Cannot pass string directly hence creating object and passing the value
		LoginPOJO validlogin = new LoginPOJO(un, pwd);
		
		
		given()
			.contentType(ContentType.JSON)
			.body(validlogin)
		.when()
			.post(routes.AUTH_LOGIN)
		.then()
			.log().body()
			.statusCode(200) // Expecting 401 for unauthorized access
			.body("token",notNullValue());  // Validate the response token should be null
		
	}
	
	

}
