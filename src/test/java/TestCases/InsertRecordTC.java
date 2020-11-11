package TestCases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pojo.InsertRecordPOJO;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;

import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.TestBase;
import Utilities.TestUtils;

public class InsertRecordTC extends TestBase {
	InsertRecordPOJO record;
	Response response;

	public InsertRecordTC() throws IOException {
		super();
	}

	@BeforeMethod()
	public void setUp() {
		initialization();
		record = new InsertRecordPOJO(prop_singleRecord.getProperty("birthday"),
				prop_singleRecord.getProperty("gender"), prop_singleRecord.getProperty("name"),
				prop_singleRecord.getProperty("natID"), prop_singleRecord.getProperty("salary"),
				prop_singleRecord.getProperty("tax"));
	}

	@Test
	public void insertSingleRecordTest() {
		
		record = new InsertRecordPOJO(prop_singleRecord.getProperty("birthday"),
				prop_singleRecord.getProperty("gender"), prop_singleRecord.getProperty("name"),
				prop_singleRecord.getProperty("natID"), prop_singleRecord.getProperty("salary"),
				prop_singleRecord.getProperty("tax"));
		
		response = given().headers("Content-Type", "application/json").body(record).log().all().when()
				.post(TestUtils.insertPersonAPI);
		System.out.println("****************");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), TestUtils.STATUSCODE_ACCEPTED);
	}

	@Test
	public void insertIncorrectRecordTest() {
		
		record = new InsertRecordPOJO(prop_invalidRecord.getProperty("birthday"),
				prop_invalidRecord.getProperty("gender"), prop_invalidRecord.getProperty("name"),
				prop_invalidRecord.getProperty("natID"), prop_invalidRecord.getProperty("salary"),
				prop_invalidRecord.getProperty("tax"));
		
		response = given().headers("Content-Type", "application/json").body(record).log().all().when()
				.post(TestUtils.insertPersonAPI);
		System.out.println("****************");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		Assert.assertNotEquals(response.getStatusCode(), TestUtils.STATUSCODE_ACCEPTED);
	}
	
	@AfterMethod()
	public void tearDown() {
		given().expect().when().get(TestUtils.rakeDatabase);
	}
}
