package Locality;

public class Locality {
    
	Integer id;
	String suburb;
	String postcode;
	
	public Locality(Integer id, String suburb, String postcode) {
		this.id = id;
		this.suburb = suburb;
		this.postcode = postcode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "Locality [id=" + id + ", suburb=" + suburb + ", postcode=" + postcode + "]";
	}

	
	
	
}
