package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import java.util.List;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.ProductPOJO;
import routes_Endpoints.routes;

public class Product_TestCases  extends BaseClass{
	
	Response response;
	
	//1) Test to retrieve all products
		@Test @Ignore
		public void testGetAllProducts()
		{
			given()
			
			.when()
				.get(routes.GET_ALL_PRODUCTS)
			
			.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.log().body();
		}

		//2) Test to retrieve a single product by ID
		@Test @Ignore
		public void testGetSingleProductById()
		{
			//calling product id from config file  and concatenating it 
		     int productID =config.getIntProperty("productId");
			given()
				.pathParam("id", productID)
			.when()
				.get(routes.GET_PRODUCT_BY_ID)
			
			.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.log().body(); 
		}

		
		//3) Test to retrieve a limited number of products
		@Test @Ignore
		public void testGetLimitedProducts()
		{
			given()
				.pathParam("limit", 3)
			
			.when()
				.get(routes.GET_PRODUCTS_WITH_LIMIT)
			
			.then()
				.statusCode(200)
				.body("size()", equalTo(3))
				.log().body();
					
		}
		
		//4) Test to retrieve products sorted in descending order
		@Test@Ignore
		public void testGetSortedProducts()
		{
		 response =
			given()
				.pathParam("order","desc")
			
			.when()
					.get(routes.GET_PRODUCTS_SORTED)
			
			.then()
					.statusCode(200)
					.extract().response();
			
		 List<Integer> prodID = response.jsonPath().getList("id", Integer.class);
	
		 assertThat(isSortedDesceding(prodID), is(true));		
		}

		
		//5) Test to retreive products sorted in Ascending order
				@Test@Ignore
				public void testGetSortedProductsAsc()
				{
					Response response=given()
						.pathParam("order", "asc")
					.when()
						.get(routes.GET_PRODUCTS_SORTED)
					.then()
						.statusCode(200)
						.extract().response();
					
					List<Integer> prodID=response.jsonPath().getList("id", Integer.class);
					 assertThat(isSortedAsceding(prodID), is(true));
				}
			
			//6) Test to get all product categories
				@Test@Ignore
				public void testGetAllCategories()
				{
					given()
					
					.when()
						.get(routes.GET_ALL_CATEGORIES)
					.then()
						.statusCode(200)
						.body("size()",greaterThan(0));

				}

				//7) Test to get products by category
				
				@Test@Ignore
				public void testGetProductsByCategory()
				{
					given()
						.pathParam("category", "electronics")
					
					.when()
						.get(routes.GET_PRODUCTS_BY_CATEGORY)
					.then()
						.statusCode(200)
						.body("size()",greaterThan(0))
						.body("category", everyItem(notNullValue()))
						.body("category", everyItem(equalTo("electronics")))
						.log().body();


				}

		//8) Test to add a new product
				@Test
				public void testAddNewProduct()
				{
					//data pass in productpayload is passed in newProduct var
					ProductPOJO newProduct=Payload.productPayload();
					
					
					int productId=
					given()
						.contentType(ContentType.JSON)
						//passing the data in body
						.body(newProduct)
						
					.when()
						.post(routes.CREATE_PRODUCT)
					.then()
						.log().body()
						.statusCode(200)
						.body("id", notNullValue())
						.body("title", equalTo(newProduct.getTitle()))  //title and name same var
						.extract().jsonPath().getInt("id"); //Extracting Id from response body
					
					System.out.println(productId);
					
				}

				//9) Test to update an existing product
				@Test
				public void testUpdateProduct()
				{
					int productId=config.getIntProperty("productId");
					
					ProductPOJO updatedPayload=Payload.productPayload();
					
					given()
						.contentType(ContentType.JSON)
						.body(updatedPayload)
						.pathParam("id", productId)
						
					.when()
						.put(routes.UPDATE_PRODUCT)
					.then()
						.log().body()
						.statusCode(200)
						.body("title", equalTo(updatedPayload.getTitle()));
						
				}

				
				//10) test to delete a product
				@Test
				public void testDeleteProduct()
				{
					int productId=config.getIntProperty("productId");
					
					given()
						.pathParam("id",productId)
					.when()
						.delete(routes.DELETE_PRODUCT)
					.then()
						.statusCode(200);
				}
				

				
}
