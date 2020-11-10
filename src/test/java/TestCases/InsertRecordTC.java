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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.TestBase;
import Utilities.TestUtils;

//5a8cffe36f1d846e14b1c3fddc07ba54
//6ea5a333d418a54d075223e1f3ab59bb

public class InsertRecordTC extends TestBase {
	InsertRecordPOJO record;

	public InsertRecordTC() throws IOException {
		super();
	}

	@BeforeMethod()
	public void setUp() {
		initialization();
		record = new InsertRecordPOJO(prop.getProperty("birthday"), prop.getProperty("gender"),
				prop.getProperty("name"), prop.getProperty("natID"), prop.getProperty("salary"),
				prop.getProperty("tax"));
	}

	@Test
	public void insertSingleRecordTest() {
		Response response = given().headers("Content-Type", "application/json").body(record).log().all().when()
				.post(TestUtils.insertPersonAPI);
		System.out.println("****************");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), TestUtils.STATUSCODE_ACCEPTED);
	}
	
	@Test
	public void insertIncorrectRecordTest() {
		Response response = given().headers("Content-Type", "application/json").body(record).log().all().when()
				.post(TestUtils.insertPersonAPI);
		System.out.println("****************");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), TestUtils.STATUSCODE_ACCEPTED);
	}
}
