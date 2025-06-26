package pojo;





/* Full Schema
fields:
{
   id:20,
   email:String,
   username:String,
   password:String,
   name:{    	// another pojo class for name json object
       firstname:String,
       lastname:String
       },
   address:{   	  // another pojo class for address json object
   city:String,
   street:String,
   number:Number,
   zipcode:String,
   geolocation:{  	  // another pojo class for geo location json object
       lat:String,
       long:String
       }
   },
   phone:String
}
*/


public class UserPOJO {
	
	/** We have to create multiple pojo class for different object nested inside the one
	 * User POJO is the main pojo class then for another pojo object inside need to create another pojo class**/
	
	// direct fields declared in this class
	private String email;
	private String password;
	private String username;
	
	//indirect object name hence declaring name object using class name
	private NamePOJO_user name;  
	private AddressPJO_user address;
	private String phone;
	

//Constructor	
	public UserPOJO(String email, String password, String username, NamePOJO_user name, AddressPJO_user address,
			String phone) {
		
		this.email = email;
		this.password = password;
		this.username = username;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public NamePOJO_user getName() {
		return name;
	}
	public void setName(NamePOJO_user name) {
		this.name = name;
	}
	public AddressPJO_user getAddress() {
		return address;
	}
	public void setAddress(AddressPJO_user address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
