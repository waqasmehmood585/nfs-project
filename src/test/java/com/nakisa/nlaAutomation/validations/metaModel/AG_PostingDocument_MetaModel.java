package com.nakisa.nlaAutomation.validations.metaModel;

import com.nakisa.nlaAutomation.pageObjects.AG_Schedules;
import com.nakisa.nlaAutomation.pageObjects.Common_BasePage_PageObject;
import com.nakisa.nlaAutomation.pageObjects.MasterAgreement_PageObject;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.validations.AG_PostingDocument_Validation;

import lombok.extern.slf4j.Slf4j;

import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.excel.ExcelConfiguration;
import org.apache.metamodel.excel.ExcelDataContext;
import org.apache.metamodel.query.Query;
import org.apache.metamodel.query.SelectItem;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.util.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AG_PostingDocument_MetaModel extends Common_BasePage_PageObject{
    AG_PostingDocument_MetaModel aG_PostingDocument = aG_PostingDocument_MetaModel.get();
    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    Map<String, SelectItem> columnMap;
    private File baseValueExcelFolder = null;
    DataSet excelDataSet;
    private String standard_Type=null;
    private String document_Type=null;
    private String account_Num=null;
    private  FileInputStream file;
    private File misMatchExcelFile;




    public  AG_PostingDocument_MetaModel createFromFile(File baseValueExcelFile, String excelSheetName) {
        AG_PostingDocument_MetaModel object = new AG_PostingDocument_MetaModel();
        object.excelDataSet = generateExcelSchemaAndDataSet(baseValueExcelFile, excelSheetName);
        object.columnMap = generateColumnMap(object.excelDataSet);
        return object;
    }
    public static DataSet generateExcelSchemaAndDataSet(File baseValueExcelFile, String excelSheetName) {
//        File file = new File(excelPathLocation);
        ExcelDataContext dataContext = new ExcelDataContext(baseValueExcelFile, new ExcelConfiguration());
        Schema excelSchema = dataContext.getDefaultSchema();
        Table excelTable = excelSchema.getTableByName(excelSheetName);
        Query query = dataContext.query().from(excelTable).selectAll().toQuery();
        return dataContext.executeQuery(query);
    }
    public static Map<String, SelectItem> generateColumnMap(DataSet dataset) {
        Map<String, SelectItem> columnMap = new HashMap<String, SelectItem>();
        for (SelectItem column : dataset.getSelectItems()) {
            columnMap.put(column.getColumn().getName(), column);
        }
        return columnMap;
    }


    public void writeObjectValues(ArrayList<AG_PostingDocument_Validation> aGPostingValues, String excelSheetName, File baseValueExcelFile)  {

        try {
            FileInputStream file = null;
            XSSFWorkbook excelWorkbook = null;

            if (!baseValueExcelFile.exists()) {
                excelWorkbook = new XSSFWorkbook();
            } else {
                file = new FileInputStream(baseValueExcelFile);
                excelWorkbook = (XSSFWorkbook) WorkbookFactory.create(file);
            }

            XSSFSheet excelSheet;
            int num=excelWorkbook.getSheetIndex(excelSheetName);
            XSSFRow row;
            int rowNumber = 0;
            int columnNumber = 0;
            if(num==(-1)){
                excelSheet = excelWorkbook.createSheet(excelSheetName);
            }
//			if(rowNumber==0 && scheduleLevel.equalsIgnoreCase("Inception")){
//				excelSheet = excelWorkbook.createSheet(excelSheetName);
//			}
            excelSheet = excelWorkbook.getSheet(excelSheetName);
            rowNumber=excelSheet.getPhysicalNumberOfRows();
            for (AG_PostingDocument_Validation aGPostingValue : aGPostingValues) {
                row = excelSheet.createRow(rowNumber++);
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getDocumentType());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getDocumentDate());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getPostingDate());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getFiscalYear());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getFiscalPeriod());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getInternalStatus());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getExternalStatus());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getJournalDate());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getCompanyCode());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getStandard());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getAccountNumber());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getPayment());
                if (!(masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Indexed Currency").get(0).equalsIgnoreCase("Yes"))) {
                    excelSheet.autoSizeColumn(columnNumber);
                    row.createCell(columnNumber++).setCellValue(aGPostingValue.getAmountContract());
                    excelSheet.autoSizeColumn(columnNumber);
                    row.createCell(columnNumber++).setCellValue(aGPostingValue.getCurrencyContract());
                }
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getAmountCompany());
                excelSheet.autoSizeColumn(columnNumber);
                row.createCell(columnNumber++).setCellValue(aGPostingValue.getCurrencyCompany());
                excelSheet.autoSizeColumn(columnNumber);
                if (MasterHooks.configurationProperties.get().getParallelCurrency()) {
                    row.createCell(columnNumber++).setCellValue(aGPostingValue.getAmountGroup());
                    excelSheet.autoSizeColumn(columnNumber);
                    row.createCell(columnNumber++).setCellValue(aGPostingValue.getCurrencyGroup());
                    excelSheet.autoSizeColumn(columnNumber);
                }

                columnNumber = 0;
            }
            file.close();
            FileOutputStream fileOutput = new FileOutputStream(baseValueExcelFile);
            excelWorkbook.write(fileOutput);
            fileOutput.close();
            excelWorkbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void compareByDocumentType(ArrayList<AG_PostingDocument_Validation> postingValuesToCompare, String documentType, String excelSheetName) {
//        String postingNumber = String.valueOf(postingCount);
        ArrayList<AG_PostingDocument_Validation> excelPostingValidations = new ArrayList<>();
        List<Row> rows = excelDataSet.toRows();
        for (Row row : rows) {
            if (documentType.equalsIgnoreCase(row.getValue(columnMap.get("Document Type")).toString())) {
                AG_PostingDocument_Validation assetDocumentValue = new AG_PostingDocument_Validation();
                setObjectValues(assetDocumentValue, row);
                excelPostingValidations.add(assetDocumentValue);
            }
        }
        compareObjects(excelPostingValidations, postingValuesToCompare, documentType, excelSheetName);
        System.out.println("\n" + documentType + " posting, Excel Values");
        printObjectValues(excelPostingValidations, documentType);
        System.out.println("\n" + documentType + " posting, Application Values");
        printObjectValues(postingValuesToCompare, documentType);
    }

    public void setObjectValues(AG_PostingDocument_Validation aGPostingValue, Row row) {
        aGPostingValue.setDocumentType(row.getValue(columnMap.get("Document Type")).toString());
        aGPostingValue.setDocumentDate(row.getValue(columnMap.get("Document Date")).toString());
        aGPostingValue.setPostingDate(row.getValue(columnMap.get("Posting Date")).toString());
        aGPostingValue.setFiscalYear(row.getValue(columnMap.get("Fiscal Year")).toString());
        aGPostingValue.setFiscalPeriod(row.getValue(columnMap.get("Fiscal Period")).toString());
        aGPostingValue.setInternalStatus(row.getValue(columnMap.get("Internal Status")).toString());
        aGPostingValue.setExternalStatus(row.getValue(columnMap.get("External Status")).toString());
        aGPostingValue.setJournalDate(row.getValue(columnMap.get("Journal Date")).toString());
        aGPostingValue.setCompanyCode(row.getValue(columnMap.get("Company")).toString());
        aGPostingValue.setStandard(row.getValue(columnMap.get("Standard")).toString());
        aGPostingValue.setAccountNumber(row.getValue(columnMap.get("Account Number")).toString());
        aGPostingValue.setPayment(row.getValue(columnMap.get("Payment(Debit/Credit)")).toString());
        if (!(masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Indexed Currency").get(0).equalsIgnoreCase("Yes"))) {
            aGPostingValue.setAmountContract(row.getValue(columnMap.get("amountCONTRACT")).toString());
            aGPostingValue.setCurrencyContract(row.getValue(columnMap.get("CurrencyCONTRACT")).toString());
        }
        aGPostingValue.setAmountCompany(row.getValue(columnMap.get("amountCOMPANY")).toString());
        aGPostingValue.setCurrencyCompany(row.getValue(columnMap.get("CurrencyCOMPANY")).toString());
        if(MasterHooks.configurationProperties.get().getParallelCurrency()) {
            aGPostingValue.setAmountGroup(row.getValue(columnMap.get("amountGroup")).toString());
            aGPostingValue.setCurrencyGroup(row.getValue(columnMap.get("CurrencyGroup")).toString());
        }
    }

    public void compareObjects(ArrayList<AG_PostingDocument_Validation> excelObjects, ArrayList<AG_PostingDocument_Validation> nlaObjects, String documentType, String excelSheetName) {
//        Common_Excel_Validation commonExcelValidation = new Common_Excel_Validation();
//        commonExcelValidation.excelSheetName = excelSheetName;
        ArrayList<AG_PostingDocument_Validation> excelFilteredByDocType = new ArrayList<>();
        for (AG_PostingDocument_Validation excelObject : excelObjects) {
            if (documentType.equalsIgnoreCase(excelObject.getDocumentType())) {
                excelFilteredByDocType.add(excelObject);
            }
        }
        if (excelFilteredByDocType.size() != nlaObjects.size()) {
            System.out.println("Mismatch in object count for Document Type: " + documentType + " in Sheet: " + excelSheetName);
            System.out.println("Excel records count: " + excelFilteredByDocType.size() + " | NLA records count: " + nlaObjects.size());
            Assert.fail("Record count mismatch between Excel and NLA data for Document Type: " + documentType + " in Sheet: " + excelSheetName);
        }
        for (AG_PostingDocument_Validation nlaObject : nlaObjects) {
            for (AG_PostingDocument_Validation excelObject : excelFilteredByDocType) {
                standard_Type = nlaObject.getStandard();
                document_Type = nlaObject.getDocumentType();
                account_Num= nlaObject.getAccountNumber();
                if (documentType.equalsIgnoreCase(excelObject.getDocumentType())) {
                    System.out.println("\n----- Posting Document " + excelObject.getDocumentType() + " Validation Starting -----");
                    compareResults("Document Date", excelObject.getDocumentDate(), nlaObject.getDocumentDate(),excelSheetName);
                    compareResults("Posting Date", excelObject.getPostingDate(), nlaObject.getPostingDate(),excelSheetName);
                    compareResults("Fiscal Year", excelObject.getFiscalYear(), nlaObject.getFiscalYear(),excelSheetName);
                    compareResults("Fiscal Period", excelObject.getFiscalPeriod(), nlaObject.getFiscalPeriod(),excelSheetName);
                    compareResults("Internal Status", excelObject.getInternalStatus(), nlaObject.getInternalStatus(),excelSheetName);
                    compareResults("External Status", excelObject.getExternalStatus(), nlaObject.getExternalStatus(),excelSheetName);
                    compareResults("Journal Date", excelObject.getJournalDate(), nlaObject.getJournalDate(),excelSheetName);
                    compareResults("Company", excelObject.getCompanyCode(), nlaObject.getCompanyCode(),excelSheetName);
                    compareResults("Standard", excelObject.getStandard(), nlaObject.getStandard(),excelSheetName);
                    compareResults("Account Number", excelObject.getAccountNumber(), nlaObject.getAccountNumber(),excelSheetName);
                    compareResults("Payment(Debit/Credit)", excelObject.getPayment(), nlaObject.getPayment(),excelSheetName);
                    if (!(masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Indexed Currency").get(0).equalsIgnoreCase("Yes"))) {
                        compareResults("amountCONTRACT", excelObject.getAmountContract(), nlaObject.getAmountContract(),excelSheetName);
                        compareResults("CurrencyCONTRACT", excelObject.getCurrencyContract(), nlaObject.getCurrencyContract(),excelSheetName);
                    }
                    compareResults("amountCOMPANY", excelObject.getAmountCompany(), nlaObject.getAmountCompany(),excelSheetName);
                    compareResults("CurrencyCOMPANY", excelObject.getCurrencyCompany(), nlaObject.getCurrencyCompany(),excelSheetName);
                    if(MasterHooks.configurationProperties.get().getParallelCurrency()) {
                        compareResults("amountGroup", excelObject.getAmountGroup(), nlaObject.getAmountGroup(),excelSheetName);
                        compareResults("CurrencyGroup", excelObject.getCurrencyGroup(), nlaObject.getCurrencyGroup(),excelSheetName);
                    }
                    System.out.println("----- Posting Document " + excelObject.getDocumentType() + " Validation Ending -----");
                    excelFilteredByDocType.remove(excelObject);
                    break;
                }

            }
        }

    }

    public boolean compareResults(String fieldName, String excelObjectValue, String nlaObjectValue, String excelSheetName) {
        double actualDifferenceValue = 0.0;
        if (!excelObjectValue.equals(nlaObjectValue)) {
            if (excelObjectValue.contains(".") || nlaObjectValue.contains(".")) {
                actualDifferenceValue = Math.abs(Double.parseDouble(nlaObjectValue.replaceAll(",", "")) - Double.parseDouble(excelObjectValue.replaceAll(",", "")));
                if (MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip() >= actualDifferenceValue) {
                    System.out.println(fieldName + " is not matching but actual difference is under the allowed difference : " + excelObjectValue.toString().equals(nlaObjectValue.toString()));
                    System.out.println("\t" + fieldName + ", excel value is : " + excelObjectValue);
                    System.out.println("\t" + fieldName + ", NLA value is : " + nlaObjectValue);
                    System.out.println("\t" + fieldName + ", allowed difference value is : " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip()  + " and actual difference value is : " + actualDifferenceValue);
                } else {
                    System.out.println(fieldName + " is not matching : " + excelObjectValue.toString().equals(nlaObjectValue.toString()));
                    System.out.println("\t" + fieldName + ", excel value is : " + excelObjectValue);
                    System.out.println("\t" + fieldName + ", NLA value is : " + nlaObjectValue);
                    System.out.println("\t" + fieldName + ", allowed difference value is : " + MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip() + " and actual difference value is : " + actualDifferenceValue);
                    try {
                        writeMisMatchExcelValues(excelSheetName, fieldName, excelObjectValue, nlaObjectValue, MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(),
                                actualDifferenceValue, standard_Type, document_Type,account_Num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(fieldName + " is not matching : " + excelObjectValue.toString().equals(nlaObjectValue.toString()));
                System.out.println("\t" + fieldName + ", excel value is : " + excelObjectValue);
                System.out.println("\t" + fieldName + ", NLA value is : " + nlaObjectValue);
                try {
                    writeMisMatchExcelValues(excelSheetName, fieldName, excelObjectValue, nlaObjectValue, MasterHooks.configurationProperties.get().getAllowedDifferenceToSkip(),
                            actualDifferenceValue, standard_Type, document_Type,account_Num);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        } else {
            System.out.println(fieldName + " is matching : " + excelObjectValue.toString().equals(nlaObjectValue.toString()));
            return true;
        }
    }

    public void printObjectValues(ArrayList<AG_PostingDocument_Validation> aGPostingValues, String postingType) {
        for (AG_PostingDocument_Validation objectValues : aGPostingValues) {
            if (postingType.equalsIgnoreCase(objectValues.getDocumentType())) {
                System.out.print(objectValues.getDocumentDate() + "|");
                System.out.print(objectValues.getPostingDate() + "|");
                System.out.print(objectValues.getFiscalYear() + "|");
                System.out.print(objectValues.getFiscalPeriod() + "|");
                System.out.print(objectValues.getInternalStatus() + "|");
                System.out.print(objectValues.getExternalStatus() + "|");
                System.out.print(objectValues.getJournalDate() + "|");
                System.out.print(objectValues.getCompanyCode() + "|");
                System.out.print(objectValues.getStandard() + "|");
                System.out.print(objectValues.getAccountNumber() + "|");
                System.out.print(objectValues.getPayment() + "|");
                if (!(masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Indexed Currency").get(0).equalsIgnoreCase("Yes"))) {
                    System.out.print(objectValues.getAmountContract() + "|");
                    System.out.print(objectValues.getCurrencyContract() + "|");
                }
                System.out.print(objectValues.getAmountCompany() + "|");
                System.out.println(objectValues.getCurrencyCompany() + "|");
                if (MasterHooks.configurationProperties.get().getParallelCurrency()) {
                    System.out.print(objectValues.getAmountGroup() + "|");
                    System.out.print(objectValues.getCurrencyGroup() + "| \n");
                }
            }
        }
    }
    public void writeMisMatchExcelValues(String sheetName, String fieldName, String excelValue, String nlaValue, Double allowedDifference,
                                         Double actualDifference, String standard_Type, String document_Type, String account_Num) throws IOException {
//        if (Strings.isNullOrEmpty(MasterHooks.checkForValidationFailures.get())) {
        copyMisMatchExcelTemplateFile(standard_Type,sheetName);
//        }
        MasterHooks.checkForValidationFailures.set("Failed");
        FileInputStream file = new FileInputStream(misMatchExcelFile);
        XSSFWorkbook workbook=null;
        XSSFSheet sheet;
        try {
            workbook = new XSSFWorkbook(file);
            int sheetIndex = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(sheetIndex);
        }catch(Exception e) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            workbook = new XSSFWorkbook(file);
            int sheetIndex1 = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(sheetIndex1);
        }
//        
//		try {
//			int sheetIndex = workbook.getSheetIndex(sheetName);
//			sheet = workbook.getSheetAt(sheetIndex);
//		} catch (Exception e) {
//			int sheetIndex1 = workbook.getSheetIndex(sheetName);
//			sheet = workbook.getSheetAt(sheetIndex1);
//		}
        XSSFRow row;

        int rowNumber = 0;
        int columnNumber = 0;

        rowNumber = sheet.getLastRowNum() + 1;

        row = sheet.createRow(rowNumber++);
        row.createCell(columnNumber++).setCellValue(MasterAgreement_PageObject.testCaseName.get());
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(sheetName);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(document_Type);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(standard_Type);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(fieldName);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(account_Num);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(excelValue);
        sheet.autoSizeColumn(columnNumber);
        row.createCell(columnNumber++).setCellValue(nlaValue);
        sheet.autoSizeColumn(columnNumber);
        if (excelValue.contains(".") || nlaValue.contains(".")) {
            row.createCell(columnNumber++).setCellValue(allowedDifference);
            sheet.autoSizeColumn(columnNumber);
            row.createCell(columnNumber++).setCellValue(actualDifference);
            sheet.autoSizeColumn(columnNumber);
        }

        columnNumber = 0;

        file.close();
        FileOutputStream fileOutput = new FileOutputStream(misMatchExcelFile);
        workbook.write(fileOutput);
        fileOutput.close();
        workbook.close();
    }
    public void copyMisMatchExcelTemplateFile(String standardName, String sheetName) {
        try {
            XSSFWorkbook excelWorkbook=null;
            File validationFolder= new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/");
            misMatchExcelFile = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "_" + standardName + "_Output.xlsx");
            boolean flag=false;
            if(!validationFolder.exists()){
                validationFolder.mkdirs();
            }
            if (misMatchExcelFile.exists()){
                file = new FileInputStream(misMatchExcelFile);
                try {
                    excelWorkbook = new XSSFWorkbook(file);
                }catch(Exception e) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    excelWorkbook = new XSSFWorkbook(file);
                }
                flag=true;
            }else{
                excelWorkbook = new XSSFWorkbook();
            }
//           String sheetName= returnExcelSheetName(document_Type);
            int num=excelWorkbook.getSheetIndex(sheetName);
            if(num==-1){
                XSSFSheet excelSheet = excelWorkbook.createSheet(sheetName);
                XSSFRow row;

                int rowNumber = 0;
                int columnNumber = 0;

                row = excelSheet.createRow(rowNumber++);
                row.createCell(columnNumber).setCellValue("Test Case Number");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Sheet Name");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Document Type");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Standard");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Field Name");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Account Number");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Excel Value");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Application Value");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Allowed Difference");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Actual Difference");
                excelSheet.autoSizeColumn(columnNumber++);
                FileOutputStream outputStream = new FileOutputStream(misMatchExcelFile);
                excelWorkbook.write(outputStream);
                outputStream.close();
            }
            excelWorkbook.close();
            if (flag){
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String returnExcelSheetName(String documentType) {
//        String excelSheetName = null;
//        if(documentType.contains("Accrual")){
//            excelSheetName="Accrual_Posting";
//        }else if(documentType.contains("Depreciation")){
//            excelSheetName="Depreciation_Posting";
//        }else if(documentType.contains("AllColumns")){
//            excelSheetName="AllColumns";
//        }else if(documentType.contains("Liability")){
//            excelSheetName="Payment_Posting";
//        }
//        return excelSheetName;
//    }


}
