package TestCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.TestBase;
import pojo.InsertRecordPOJO;
import pojo.getUser_POJO;
import Utilities.TestUtils;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookKeeper_taxReliefTC extends TestBase {
	getUser_POJO[] getTax;
	Response response;
	List<InsertRecordPOJO> records;

	public BookKeeper_taxReliefTC() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		response = given().expect().when().get(TestUtils.taxReliefAPI);
		getTax = response.as(getUser_POJO[].class);
	}

	@Test
	public void getTaxReliefTest() {
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), TestUtils.STATUSCODE_OK);

		for (int i = 0; i < getTax.length; i++) {
			System.out.println(getTax[i].getName());
			System.out.println(getTax[i].getnatID());
			System.out.println(getTax[i].getrelief());
		}
	}

	@Test
	public void natIDMaskedWith$Test() {

		for (int i = 0; i < getTax.length; i++) {
			for (int j = 4; j <= 8; j++) {
				Assert.assertTrue(getTax[i].getnatID().charAt(j) == '$');
				System.out.println(getTax[i].getnatID());
			}
		}
	}
	
	@Test
	public void computationTaxReliefTest() throws ParseException {
		given().expect().when().get(TestUtils.rakeDatabase);
		records = TestUtils.insertMultipleRecords(TestUtils.getData("Sheet1"));
		for(int i =0; i<records.size();i++) {
		double CalculatedTaxRelief = TestUtils.calculate_taxRelief(records.get(i));
		getTax = given().expect().when().get(TestUtils.taxReliefAPI).as(getUser_POJO[].class);
		double ExpectedTaxRelief = Double.parseDouble(getTax[i].getrelief());
		Assert.assertTrue(ExpectedTaxRelief-CalculatedTaxRelief < 50.00);
		
		}
	}
}