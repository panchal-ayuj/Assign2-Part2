package com.example.part2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.poi.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import static java.util.Calendar.DATE;
import static java.util.Calendar.getInstance;

@SpringBootApplication
public class Part2Application {

	public static List<ExcelData> parseExcel(String filePath) {
		List<ExcelData> dataList = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			 Workbook workbook = new XSSFWorkbook(fileInputStream)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(row.getRowNum() == 0) {
					continue;
				}

				ExcelData excelData = new ExcelData();
				excelData.setDate(getCellValue(row.getCell(0)));
				excelData.setMonth(getCellValue(row.getCell(1)));
				excelData.setTeam(getCellValue(row.getCell(2)));
				excelData.setPanel(getCellValue(row.getCell(3)));
				excelData.setRound(getCellValue(row.getCell(4)));
				excelData.setSkill(getCellValue(row.getCell(5)));
				excelData.setTime(getCellValue(row.getCell(6)));
				excelData.setCurrLoc(getCellValue(row.getCell(7)));
				excelData.setPreLoc(getCellValue(row.getCell(8)));
				excelData.setName(getCellValue(row.getCell(9)));

				dataList.add(excelData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataList;
	}

	private static String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			default:
				return null;
		}
	}

	public static void main(String[] args) {

		SpringApplication.run(Part2Application.class, args);

		String filePath = "C:\\Users\\panchal.kumar\\IdeaProjects\\part2\\src\\main\\resources\\Accolite.xlsx";
		List<ExcelData> dataList = parseExcel(filePath);

		for (int i=0; i<10; i++) {
			System.out.println(dataList.get(i));
		}
	}

}
