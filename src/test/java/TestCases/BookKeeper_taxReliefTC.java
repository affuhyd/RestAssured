package TestCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookKeeper_taxReliefTC extends TestBase {

		public BookKeeper_taxReliefTC() throws IOException {
			super();
		}
		
		@BeforeMethod
		public void setUp() {
			initialization();
		}
		
		@Test
		public void getTaxReliefTest() {
		Response response = given().log().all().when().get("/calculator/taxRelief");
		
		given().expect().defaultParser(Parser.JSON).when().get("/calculator/taxRelief");
		
		/*	for (getUser_POJO user : getTax.getUserList()) {
			System.out.println(user.getName());
			System.out.println(user.getnatID());
			System.out.println(user.getrelief());
		}
*/		// System.out.println(ls);
		System.out.println("****************");
		System.out.println(response.asString());

		/*System.out.println(response.getStatusCode());
		JsonPath js = new JsonPath(response.asString());
		int size = js.getInt("array.size()");
		System.out.println(js.getInt("array.size()"));
		//for(int i=0; i<size;i++) {
			
			System.out.println(js.get("array")) ;*/
		}

}
