package pojo;

public class CartProduct_POJO {

	/** 
	 *  products:[{productId:Number,quantity:Number}]
}
	 */

	private int productID;
	private int quantity;


	public CartProduct_POJO(int productID, int quantity) {

		this.productID = productID;
		this.quantity = quantity;
	}


	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



}
