package pojo;

public class AddressPJO_user {
	
	private String city;
	private String street;
	private int number;
	private String zipcode;
	
	// declaring geolocation inside the address as per the schema
	private GeolocationPOJO_user geolocation;
	   
	// Constructor
	public AddressPJO_user(String city, String street, int number, String zipcode, GeolocationPOJO_user geolocation) {
		
		this.city = city;
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.geolocation = geolocation;
	}
	
	
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public GeolocationPOJO_user getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(GeolocationPOJO_user geolocation) {
		this.geolocation = geolocation;
	}

	
	  
}
