package routes_Endpoints;

//This class will have Variables with End points Stored for different requests
public class routes {
	
// BaseURL is declared static so it can be accessible using class name and declaring final as this value will not change
	public static final String BASE_URL ="https://fakestoreapi.com";

	
	
	////////////////PRODUCT MODULES -END POINTS/////////////////
	
	public static final String GET_ALL_PRODUCTS="/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limit={limit}";
    public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
    public static final String GET_ALL_CATEGORIES = "/products/categories";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{category}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";

	
	////////////////USERS MODULES -END POINTS/////////////////

    public static final String GET_ALL_USERS = "/users";
    public static final String GET_USER_BY_ID = "/users/{id}";
    public static final String GET_USERS_WITH_LIMIT = "/users?limit={limit}";
    public static final String GET_USERS_SORTED = "/users?sort={order}";
    public static final String CREATE_USER = "/users";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String DELETE_USER = "/users/{id}";

    
    
    
    
    
	////////////////CART MODULES -END POINTS/////////////////

    public static final String GET_ALL_CARTS = "/carts";
    public static final String GET_CART_BY_ID = "/carts/{id}";
    public static final String GET_CARTS_BY_DATE_RANGE = "/carts?startdate={startdate}&enddate={enddate}";
    public static final String GET_USER_CART = "/carts/user/{userId}";
    public static final String GET_CARTS_WITH_LIMIT = "/carts?limit={limit}";
    public static final String GET_CARTS_SORTED = "/carts?sort={order}";
    public static final String CREATE_CART = "/carts";
    public static final String UPDATE_CART = "/carts/{id}";
    public static final String DELETE_CART = "/carts/{id}";

    

    
	////////////////LOGIN MODULES -END POINTS/////////////////

    public static final String AUTH_LOGIN = "/auth/login";

}
