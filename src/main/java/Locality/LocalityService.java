package Locality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalityService {
    
//	No database involves, just store all users’ objects into a local variable.
	
	public static Map<String, Locality> localities = new HashMap<>();
	private static final AtomicInteger count = new AtomicInteger (0);
	
	public Locality findById(String id) {
		return localities.get(id);
	}
	
    public Locality add(String suburb, String postcode) {
        int currentId = count.incrementAndGet();
        Locality locality = new Locality(currentId, suburb, postcode);
        localities.put(String.valueOf(currentId), locality);
        return locality;
    }
	
	public Locality update(String id, String suburb, String postcode) {

		Locality locality = localities.get(id);
        if (suburb != null) {
        	locality.setSuburb(suburb);
        }

        if (postcode != null) {
        	locality.setPostcode(postcode);
        }
        localities.put(id, locality);

        return locality;

    }
	
	public void delete(String id) { 
		localities.remove(id);
	}
	
	public List findAll() {
		return new ArrayList<>(localities.values());
	}
	
	public LocalityService() {
		
	}
}
