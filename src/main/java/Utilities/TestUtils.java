package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import pojo.InsertRecordPOJO;

public class TestUtils {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static int STATUSCODE_ACCEPTED = 202;
	public static int STATUSCODE_OK = 200;
	public static String insertPersonAPI = "/calculator/insert";
	public static String insertMultiplePersonsAPI = "/calculator/insert";
	public static String taxReliefAPI = "/calculator/taxRelief";
	public static String uploadcsvAPI = "/calculator/uploadLargeFileForInsertionToDatabase";
	public static String rakeDatabase = "/calculator/rakeDatabase";
	public static String ChromeDriverPath = "src/test/resources/chromedriver.exe";
	public static String FireFoxDriverPath = "src/test/resources/geckodriver.exe";
	public static String filePath_CSV = "src/test/java/TestData/Sample_CitizensData.csv";
	
	static Workbook book;
	static Sheet sheet;
	static List<InsertRecordPOJO> ls;

	public static double calculate_taxRelief(InsertRecordPOJO record) throws ParseException {

		double AgeVariable = getAgeVariable(record);
		Double salary = Double.parseDouble(record.getSalary());
		Double taxPaid = Double.parseDouble(record.getTax());
		double taxRelief = genderbonus(record) + (salary - taxPaid) * (AgeVariable);

		return taxRelief;
	}

	public static double genderbonus(InsertRecordPOJO record) {
		if (record.getGender().equalsIgnoreCase("male") || record.getGender().equalsIgnoreCase("m"))
			return 0.00;
		else
			return 500.00;
	}

	public static double getAgeVariable(InsertRecordPOJO record) throws ParseException {
		double age = age(record.getBirthday());

		if (age <= 18)
			return 1.0;
		if (age > 18 && age <= 35)
			return 0.8;
		if (age > 35 && age <= 50)
			return 0.5;
		if (age > 50 && age <= 75)
			return 0.367;
		if (age >= 76)
			return 0.05;
		else
			return 0.00;
	}

	public static double age(String birthday) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		Date date = formatter.parse(birthday);
		// Converting obtained Date object to LocalDate object
		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();
		// Calculating the difference between given date to current date.
		Period period = Period.between(givenDate, LocalDate.now());

		return period.getYears();
	}

	public static Object[][] getData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("src/test/java/TestData/testData.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;

	}

	public static List<InsertRecordPOJO> insertMultipleRecords(Object[][] data) {
		int rows = data.length;
		// int columns = data[0].length;
		for (int i = 0; i < rows; i++) {
			ls.add(new InsertRecordPOJO(data[i][0].toString(), data[i][1].toString(), data[i][2].toString(),
					data[i][3].toString(), data[i][4].toString(), data[i][5].toString()));
		}
		return ls;
	}
}
