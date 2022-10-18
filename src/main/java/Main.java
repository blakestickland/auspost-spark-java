import com.fasterxml.jackson.databind.ObjectMapper;

import Locality.Locality;
import Locality.LocalityService;

import java.util.List;

import static spark.Spark.*;

public class Main {
	
	private static LocalityService localityService = new LocalityService();
	private static ObjectMapper om = new ObjectMapper();
	
	  public static void main(String[] args) {		  
		  
        // Start embedded server at this port
        port(8080);

        // Main Page, welcome
        get("/", (request, response) -> "Welcome to the Australian Locality API");

        // POST - Add a locality
        post("/locality/add", (request, response) -> {

            String suburb = request.queryParams("suburb");
            String postcode = request.queryParams("postcode");
            Locality locality = localityService.add(suburb, postcode);
            response.status(201); // 201 Created
            return om.writeValueAsString(locality);

        });

        // GET - Give me locality with this id
        get("/locality/:id", (request, response) -> {
        	Locality locality = localityService.findById(request.params(":id"));
        	String id = request.params(":id");
            if (locality != null) {
                return om.writeValueAsString(locality);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("locality with id " + id + " not found!");
            }
        });

        // Get - Give me all localities
        get("/locality", (request, response) -> {
            List result = localityService.findAll();
            if (result.isEmpty()) {
                return om.writeValueAsString("user not found");
            } else {
                return om.writeValueAsString(localityService.findAll());
            }
        });

        // PUT - Update locality
        put("/locality/:id", (request, response) -> {
            String id = request.params(":id");
            Locality locality = localityService.findById(id);
            if (locality != null) {
                String suburb = request.queryParams("suburb");
                String postcode = request.queryParams("postcode");
                localityService.update(id, suburb, postcode);
                return om.writeValueAsString("locality with id " + id + " is updated!");
            } else {
                response.status(404);
                return om.writeValueAsString("locality not found");
            }
        });

        // DELETE - delete locality
        delete("/locality/:id", (request, response) -> {
            String id = request.params(":id");
            Locality locality = localityService.findById(id);
            if (locality != null) {
            	localityService.delete(id);
                return om.writeValueAsString("locality with id " + id + " is deleted!");
            } else {
                response.status(404);
                return om.writeValueAsString("locality not found");
            }
        });
	}
}