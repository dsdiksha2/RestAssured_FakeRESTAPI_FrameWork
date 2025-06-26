package pojo;

import java.util.Date;
import java.util.List;

public class CartPOJO {


	/** Cart schema: 
	 * {
    id:Number,
    userId:Number,
    date:Date,
    	//Separate product pojo class will be created, as this is cart product is different not similar to product pojo class 
    products:[{productId:Number,quantity:Number}]
}
	 */

	private int userId;
	private String date;      // considering date is String
	private List<CartProduct_POJO> products;



	public CartPOJO(int userId, String date, List<CartProduct_POJO> products) {

		this.userId = userId;
		this.date = date;
		this.products = products;
	}



	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<CartProduct_POJO> getProducts() {
		return products;
	}
	public void setProducts(List<CartProduct_POJO> products) {
		this.products = products;
	}





}
