package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DPUserTests {
	
	@Test(priority = 0, dataProvider = "Data", dataProviderClass =  DataProviders.class)
	public void testPostUser(String userId, String userName, String fname, String lname, String useremail, String pwd, String phoneno) {

		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phoneno);
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 1, dataProvider = "UserName" , dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) {

		Response response = UserEndPoints.readUser(userName);
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), 200);

	}

	

	@Test(priority = 2 , dataProvider = "UserName" , dataProviderClass = DataProviders.class)
	public void testdeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
	
		Assert.assertEquals(response.getStatusCode(), 200);

	}


}
