package com.nakisa.nlaAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

public class ReadInputValuesFromExcel {

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>> readExcelInputs(String testCaseNumber, String sheetName) throws FileNotFoundException, IOException {

		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>> TestCaseInputs = new LinkedHashMap<>();
		String filePath = "src/test/resources/inputExcelFiles";
		String currentmainSectionKey = null, currentsubSectionKey = null;

		File testCaseFilePath = new File(filePath + "/" + testCaseNumber + ".xls");
		FileInputStream file = new FileInputStream(testCaseFilePath);
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet tCSheet = workbook.getSheet(sheetName);

		for (int rowNum = 0; rowNum < tCSheet.getLastRowNum() + 1; rowNum++) {
			try {
				int colNum = 1;

				String TitleCellValue = tCSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
				if ((StringUtils.containsIgnoreCase(TitleCellValue, "Inception"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Contract Event"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "LC Event"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "AG Event"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Disclosure Report"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Activity Analysis Report"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Periodic Posting Status Report"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Mass Modification"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Mass Workflow"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Consolidated Transaction"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "NFS Export"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "NFS Import"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "GL Balance Report"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "SAP Posting Job"))
						|| (StringUtils.containsIgnoreCase(TitleCellValue, "Scenario"))
				    ||(StringUtils.containsIgnoreCase(TitleCellValue, "Inter Company Transfer"))){

					TestCaseInputs.put(TitleCellValue, new LinkedHashMap<>());
					currentmainSectionKey = tCSheet.getRow(rowNum).getCell(colNum).getStringCellValue();

					for (int sectionRow = rowNum + 1; tCSheet.getRow(sectionRow).getCell(colNum).getStringCellValue() != ""; sectionRow++) {
						if (StringUtils.containsIgnoreCase(TitleCellValue, "Inception")) {
							String currentCellValue = tCSheet.getRow(sectionRow).getCell(colNum).getStringCellValue();
							if (StringUtils.equalsIgnoreCase(currentCellValue, "Master Agreement Level")) {
								TestCaseInputs.get(currentmainSectionKey).put(currentCellValue, new LinkedHashMap<>());
								currentsubSectionKey = currentCellValue;
							} else if (StringUtils.equalsIgnoreCase(currentCellValue, "Contract Level")) {
								TestCaseInputs.get(currentmainSectionKey).put(currentCellValue, new LinkedHashMap<>());
								currentsubSectionKey = currentCellValue;
							} else if (StringUtils.containsIgnoreCase(currentCellValue, "Lease Component Level")) {
								TestCaseInputs.get(currentmainSectionKey).put(currentCellValue, new LinkedHashMap<>());
								currentsubSectionKey = currentCellValue;
							} else if (StringUtils.containsIgnoreCase(currentCellValue, "Activation Group Level")) {
								TestCaseInputs.get(currentmainSectionKey).put(currentCellValue, new LinkedHashMap<>());
								currentsubSectionKey = currentCellValue;
							} // else if (StringUtils.equalsIgnoreCase(currentCellValue, "unit")) {
							// TestCaseInputs.get(currentmainSectionKey).put(currentCellValue, new LinkedHashMap<>());
							// currentsubSectionKey = currentCellValue;
							// }
						} else {
							if (TestCaseInputs.get(currentmainSectionKey).get("eventdata") == null) {
								TestCaseInputs.get(currentmainSectionKey).put("eventdata", new LinkedHashMap<>());
								currentsubSectionKey = "eventdata";
							}
						}

						DataFormatter cellTypeFormatter = new DataFormatter();
						FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
						Cell fieldValue = tCSheet.getRow(sectionRow).getCell(colNum + 1);
						objFormulaEvaluator.evaluate(fieldValue);
						String formattedFieldValue = cellTypeFormatter.formatCellValue(fieldValue, objFormulaEvaluator);
						String fieldKey = tCSheet.getRow(sectionRow).getCell(colNum).getStringCellValue();
						TestCaseInputs.get(currentmainSectionKey).get(currentsubSectionKey).putIfAbsent(fieldKey, new ArrayList<>());
						TestCaseInputs.get(currentmainSectionKey).get(currentsubSectionKey).get(fieldKey).add(formattedFieldValue);
						rowNum = sectionRow + 1;
					}
				}
			} catch (NullPointerException ex) {
				continue;
			}
		}
		workbook.close();
		return TestCaseInputs;
	}

}
