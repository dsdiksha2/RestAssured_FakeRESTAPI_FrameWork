package testCases;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import payloads.Payload;
import pojo.ProductPOJO;
import routes_Endpoints.routes;



public class Product_DataDrivenTestCase  extends BaseClass{
	
	// for csv file call csv data provider method name in the below parameter ex: dataProvider="csvDataProvider"
	@Test(dataProvider="jsonDataProvider", dataProviderClass=Utility.DataProviders.class)
	public void testAddNewProduct(Map<String,String> data)
	{
		
		//Creating variable to get the data
		String title = data.get("title");
		String category = data.get("category");
		String description = data.get("description");
		Double price = Double.parseDouble(data.get("price"));
		String image = data.get("image");
		
		
		//Converting above data (from product.json) into POJO form, hence calling the constructor of POJO class
		ProductPOJO AddnewProd = new ProductPOJO(title, price, description, image, category);
		
		
		int productId=
			given()
				.contentType(ContentType.JSON)
				.body(AddnewProd)
				
			.when()
				.post(routes.CREATE_PRODUCT)
			.then()
				.log().body()
				.statusCode(200)
				.body("id", notNullValue())
				.body("title", equalTo(AddnewProd.getTitle()))
				.extract().jsonPath().getInt("id"); //Extracting Id from response body
			
			System.out.println("Product ID======> "+ productId);

			//Delete product
			given()
				.pathParam("id",productId)
			.when()
				.delete(routes.DELETE_PRODUCT)
			.then()
				.statusCode(200);
			
			System.out.println("Deleted Product ID======> "+ productId);

		
		
	}

}
