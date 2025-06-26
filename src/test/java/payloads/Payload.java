package payloads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.AddressPJO_user;
import pojo.CartPOJO;
import pojo.CartProduct_POJO;
import pojo.GeolocationPOJO_user;
import pojo.LoginPOJO;
import pojo.NamePOJO_user;
import pojo.ProductPOJO;
import pojo.UserPOJO;

public class Payload {


	private static final Faker faker = new Faker();

	//Creating categories data to use
	private static final String categories[]= {"electronics", "furniture", "clothing", "books", "beauty"};
	private static final Random random = new Random();


	//////////////////////PRODUCTS - PAYLOAD/////////////////////////

	public static ProductPOJO productPayload() {
		//Commerce function is inbuild use to generate fake data for commerce related

		String name = faker.commerce().productName();
		//Now convert string in double
		Double price = Double.parseDouble(faker.commerce().price());
		String description = faker.lorem().sentence();
		String imageURL = "https://i.pravatar.cc/100";

		/** Instead of using hard coded array index value using random class 
		to randomly pick the value every time a new record is created .
		If hard coded it always create new request with same data**/
		String category =categories[random.nextInt(categories.length)];

		new ProductPOJO(name, price, description, imageURL, category);
		return new ProductPOJO(name, price, description, imageURL, category);
	}


	////////////////////USER- PAYLOAD//////////////////////////////	
	/* json schema
	{
	    email:'John@gmail.com',
	    username:'johnd',
	    password:'m38rmF$',
	    name:{
	        firstname:'John',
	        lastname:'Doe'
	    },
	    address:{
	        city:'kilcoole',
	        street:'7835 new road',
	        number:3,
	        zipcode:'12926-3874',
	        geolocation:{
	            lat:'-37.3159',
	            long:'81.1496'
	        }
	    },
	    phone:'1-570-236-7033'
	}
	 */

	public static UserPOJO userPayload() {

		////////////////////Generating data for Name//////////////////
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();

		NamePOJO_user name = new NamePOJO_user(firstname, lastname);


		////////////////////Generating data for Geo location which in nested in address object//////////////////	 

		String  lat = faker.address().latitude();
		String  lng = faker.address().longitude();

		GeolocationPOJO_user geolocation = new GeolocationPOJO_user(lat, lng);



		//////////////////// Generating data for address which is nested in user json //////////////////
		String city = faker.address().city();
		String street = faker.address().streetName();
		//Below 100 random number will be generated
		int number = random.nextInt(100);
		String zipcode = faker.address().zipCode();

		AddressPJO_user address = new AddressPJO_user(city, street, number, zipcode,geolocation);


		////////////////////Generating data for USER////////////////// 
		String email = faker.internet().emailAddress();
		String password = faker.internet().password();
		String username = faker.name().username();
		String phone = faker.phoneNumber().cellPhone();


		new UserPOJO(email, password, username, name, address, phone);
		return new  UserPOJO(email, password, username, name, address, phone);

	}





	/////////////////////CART///////////////////////////////

	/** Cart schema: 
	 * {
    id:Number,
    userId:Number,
    date:Date,
    	//Separate product pojo class will be created, as this is cart product is different not similar to product pojo class 
    products:[{productId:Number,quantity:Number}]
}
	 */

	/**
	 * passing userid as a parameter in the method as userid will act as input for whose cart it is
	 * or for which user cart belongs to 
	 * 
	 */
	public static CartPOJO cartPayload(int userId) {

		//each prod has id and quantity, for multiple id and quantity
		List<CartProduct_POJO> products = new ArrayList<>();

		// Adding one random product to the cart
		int productId = random.nextInt(100);
		int quantity = random.nextInt(10) + 1;  //this will return random number between 0to10 and it might return 0 value hence adding 1 with it

		products.add(new CartProduct_POJO(productId, quantity));

		//  Can use this date code to change the format
		//new Date()  ----> Returns date like  Wed Feb 19 13:17:45 IST 202
		// We need to convert this to "yyyy-MM-dd" format in String 

		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);// Define output date format
		String date = outputFormat.format(new Date());//Converting to String


		new CartPOJO(userId,date, products); 
		return new CartPOJO(userId, date, products);


	}



	///////////////////LOGIN//////////////////////////
	
	/**
{
    username:String,
    password:String
}
	 */
	 

	public static LoginPOJO loginPayload()
	{
	

	String username = faker.name().username();
	String password = faker.internet().password();
	
	LoginPOJO loggin = new LoginPOJO(username, password);
	
	return loggin;
	
	}
	
}
