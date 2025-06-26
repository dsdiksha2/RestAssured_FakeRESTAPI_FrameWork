package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.UserPOJO;
import routes_Endpoints.routes;



public class User_TestCases extends BaseClass{
	
	Response response;
	
	
	//1) Fetch all the users
		@Test @Ignore
		public void testGetAllUsers()
		{
			given()
			
			.when()
				.get(routes.GET_ALL_USERS)
			
			.then()
				.statusCode(200)
				.log().body()
				.contentType(ContentType.JSON)
				.body("size()", greaterThan(0));
		}

	//2)  Test to fetch a specific user by ID
		@Test@Ignore
		public void testGetUserById()
		{
			int userID =config.getIntProperty("userId");
			given()
				.pathParam("id", userID)
			.when()
				.get(routes.GET_USER_BY_ID)
			
			.then()
				.statusCode(200)
				.log().body()
				.contentType(ContentType.JSON)
				.body("size()", greaterThan(0));
			
		}
	
		
		//3) Test to fetch a limited number of users
			@Test @Ignore
			public void testGetUsersWithLimit()
			{
				int Limit =config.getIntProperty("limit");
			given()
					.pathParam("limit", Limit)
			.when()
				.get(routes.GET_USERS_WITH_LIMIT)
			
			.then()
				.statusCode(200)
				.log().body()
				.contentType(ContentType.JSON)
				.body("size()", equalTo(Limit));
			
			}
	
		//4) Test to fetch users sorted in ascending order

			@Test @Ignore
			void testGetUsersSorted()
			{
	response=		
			given()
					.pathParam("order","asec")
					
			.when()
				.get(routes.GET_PRODUCTS_SORTED)
			
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.log().body()
				.extract().response();
	
		//getlist will extract id in string fromat so we have to convert it into integer class
		List<Integer> userID = response.jsonPath().getList("id",Integer.class);
		assertThat(isSortedAsceding(userID), is(true));
			
				}
		
			
			//5) Test to fetch users sorted in descending order

			@Test @Ignore
			void testGetUsersSorted_desc()
			{
	response=		
			given()
					.pathParam("order","desc")
					
			.when()
				.get(routes.GET_PRODUCTS_SORTED)
			
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.log().body()
				.extract().response();
	
		//getlist will extract id in string fromat so we have to convert it into integer class
		List<Integer> userID = response.jsonPath().getList("id",Integer.class);
		assertThat(isSortedDesceding(userID), is(true));

					
				}

		//6) Test to create a new user
		@Test@Ignore
		public void testCreateUser()
		{		
		UserPOJO newUser =  Payload.userPayload();
		Integer id =		
			given()
				.contentType(ContentType.JSON)
				.body(newUser)
			.when()
				.post(routes.CREATE_USER)
			
			.then()
				.log().body()
				.statusCode(200)
				.body("id", notNullValue())
				.extract().response().jsonPath().getInt("id");
			
			System.out.println("Generated UserID=====:"+ id);
			
				}

		//7) Test to update user
		
		@Test
		public void testUpdateUser()
		{
			int userID= config.getIntProperty("userId");
			UserPOJO updateUser = Payload.userPayload();
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id",userID)
			.body(updateUser)
		.when()
				.put(routes.UPDATE_USER)
			
		.then()
			.log().body()
			.statusCode(200)
			.body("username",equalTo(updateUser.getUsername()));
		
		}
	
		
		//8) delete user
		
		@Test
		void testDeleteUser()
		{

			int userId=config.getIntProperty("userId");
			
			given()
				.pathParam("id", userId)
			.when()
				.delete(routes.DELETE_USER)
			.then()
				.statusCode(200);
			}

		
}
