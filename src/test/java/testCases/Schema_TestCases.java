package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import routes_Endpoints.routes;

public class Schema_TestCases extends BaseClass{
	
	//In schema we validate 'GET' response body schema, hence we use get request
	//Here we validate data type of the field hence cannot use assert method
	@Test
	public void testProductSchema()
	{
		int productId=config.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		
		.when()
			.get(routes.GET_PRODUCT_BY_ID)
		.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productSchema.json"));
	}


	@Test
	 public void testCartSchema() {
   	int cartId = config.getIntProperty("cartId");
       given()
           .pathParam("id", cartId)
           .when()
               .get(routes.GET_CART_BY_ID)
           .then()
           	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("cartSchema.json"));
   }
	
	@Test
	public void testUserSchema()
	{
		int userId=config.getIntProperty("userId");
		given()
			.pathParam("id",userId)
		.when()
			.get(routes.GET_USER_BY_ID)
		.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userSchema.json"));
		
	}
	
	 

	
	
	
	
}
