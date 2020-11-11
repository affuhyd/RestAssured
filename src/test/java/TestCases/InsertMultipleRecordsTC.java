package TestCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.TestBase;
import Utilities.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.InsertRecordPOJO;

public class InsertMultipleRecordsTC extends TestBase {
	List<InsertRecordPOJO> record;

	public InsertMultipleRecordsTC() throws IOException {
		super();
	}

	@BeforeMethod()
	public void setUp() {
		initialization();
		record = TestUtils.insertMultipleRecords(TestUtils.getData("Sheet1"));
	}

	@Test()
	public void InsertMultipleRecordsTest() {
		RequestSpecification requestSpecification = given().header("Content-Type", "application/json").body(record);
		Response response = requestSpecification.log().all().when().post(TestUtils.insertMultiplePersonsAPI);
		System.out.println("****************");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), TestUtils.STATUSCODE_ACCEPTED);
	}
}
