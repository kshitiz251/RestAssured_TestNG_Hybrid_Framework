package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import api.utilities.Utilities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	static ResourceBundle getUrl(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
		
	}

	public static Response createUser(User payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

		//		.when().post(Routes.post_url);
				.when().post(Utilities.getPropertisData("post_url"));
			
		return response;
	}

	public static Response readUser(String userName) {

		Response response = given().pathParam("username", userName)

				.when().get(Utilities.getPropertisData("get_url"));
			//	.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String userName, User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)

				.when()
					.put(Routes.update_url);

		return response;
	}
	
	
	public static Response deleteUser(String userName) {

		Response response = given()
				.pathParam("username", userName)

				.when()
					.delete(Routes.delete_url);

		return response;
	}
}
