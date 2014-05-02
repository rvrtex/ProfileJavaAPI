package client;

import java.util.Arrays;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

import customExceptions.CustomException;

import EnitiyManager.CRUDService;
import profile.Location;
import profile.Profile;

public class ProfileClient {
	CRUDService service = JAXRSClientFactory.create("http://localhost:8080/hellorest-server/rest", CRUDService.class, Arrays.asList(new JSONProvider<Object>()));
	
	public Profile getProfile(int id) throws CustomException{
		return service.getProfile(id).readEntity(Profile.class);
	}
	
	public Profile updateProfile(int id, Profile profile) throws CustomException{
		return service.updateProfile(id, profile).readEntity(Profile.class);
	}

	public Profile createProfile(Profile profile) throws CustomException{
		return service.createProfile(profile).readEntity(Profile.class);
	}

	public void deleteProfile(int id) throws CustomException{
		service.deleteProfile(id);
	}

	public static void main(String[] args) throws CustomException {
		ProfileClient client = new ProfileClient();
		Profile profile = new Profile();
		profile.setName("ZAD-Man");
		profile.setEmail("goobers@gmail.com");
		Location loc = new Location();
		loc.setCity("Sandy");
		loc.setOwnerID(99);
		loc.setState("CA");
		loc.setStreet("1234 Main St.");
		loc.setZip("90069");
		profile.setLocation(loc);
		profile.setUserRating(5);
		Profile created = client.createProfile(profile);
		System.out.println(client.getProfile(created.getUserID()).getName());
	}
}
