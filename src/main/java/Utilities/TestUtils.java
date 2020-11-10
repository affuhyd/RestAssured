package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	public static long STATUSCODE_ACCEPTED = 202;
	public static String insertPersonAPI = "/calculator/insert"; 
	public static String insertMultiplePersonsAPI = "/calculator/insert";
	public static String taxReliefAPI = "/calculator/taxRelief";
	public static String uploadcsvAPI = "/calculator/uploadLargeFileForInsertionToDatabase" ;

	static Workbook book;
	static Sheet sheet;
	static List<InsertRecordPOJO> ls;

	public static String getMultiple() {

		String json = "[{\"birthday\":\"01012011\",\"gender\":\"M\",\"name\":\"Josef\",\"natid\":\"S3201111Y\",\"salary\":\"345333\",\"tax\":\"0.9\"},{\"birthday\":\"01012011\",\"gender\":\"F\",\"name\":\"Jasmine\",\"natid\":\"S3211111Y\",\"salary\":\"345333\",\"tax\":\"0.9\"}]";
		return json;

		/*
		 * List<InsertRecordPOJO> ls = new ArrayList<InsertRecordPOJO>();
		 * 
		 * ls.add(record) InsertMultipleRecordPOJO records = new
		 * InsertMultipleRecordPOJO(); records.setMultipleRecords(ls);
		 */

	}

	public static void calculate_taxRelief(InsertRecordPOJO record) {

		double AgeVariable = 0;
		Double salary = Double.parseDouble(record.getSalary());
		Double taxPaid = Double.parseDouble(record.getTax());

		double taxRelief = genderbonus(record) + (salary - taxPaid) * (AgeVariable);

	}

	public static double genderbonus(InsertRecordPOJO record) {
		if (record.getGender().equalsIgnoreCase("male"))
			return 0.00;
		else
			return 500.00;
	}

	public static void getAgeVariable(InsertRecordPOJO record) {

		record.getBirthday();

	}

	public static int age(Date birthday, Date date) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		int d1 = Integer.parseInt(formatter.format(birthday));
		int d2 = Integer.parseInt(formatter.format(date));
		int age = (d2 - d1) / 10000;
		return age;
	}

	public static Object[][] getData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("src/test/resources/testData.xlsx");
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
		//int columns = data[0].length;
		
		for(int i=0; i<rows; i++) {
			ls.add(new InsertRecordPOJO(data[i][0].toString(),data[i][1].toString(),data[i][2].toString(),data[i][3].toString(),data[i][4].toString(),data[i][5].toString()));
		}
		
		return ls;
	}
}
