package com.nakisa.nlaAutomation.validations.metaModel;
import com.nakisa.nlaAutomation.pageObjects.Common_BasePage_PageObject;
import com.nakisa.nlaAutomation.pageObjects.MasterAgreement_PageObject;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.nakisa.nlaAutomation.validations.AG_Classifications_Validation;
import com.nakisa.nlaAutomation.validations.AG_PostingDocument_Validation;
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
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AG_Classifications_MetaModel extends Common_BasePage_PageObject{
    MasterAgreement_PageObject masterAgreementPageObject = masterAgreement_PageObject.get();
    private Map<String, SelectItem> columnMap;
    private DataSet excelDataSet;
    private String standard_Type=null;
    private String classification_Level = null;
    private File misMatchExcelFile;
    private  FileInputStream file;

    public AG_Classifications_MetaModel createFromFile(File baseValueExcelFile, String excelSheetName) {
        AG_Classifications_MetaModel object = new AG_Classifications_MetaModel();
        object.excelDataSet = generateExcelSchemaAndDataSet(baseValueExcelFile, excelSheetName);
        object.columnMap = generateColumnMap(object.excelDataSet);
        return object;
    }
    public static DataSet generateExcelSchemaAndDataSet(File baseValueExcelFile, String excelSheetName) {
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


    public void writeObjectValues(ArrayList<AG_Classifications_Validation> classificationValues, String excelSheetName, File baseValueExcelFile)  {

        try {
            FileInputStream file = new FileInputStream(baseValueExcelFile);
            XSSFWorkbook excelWorkbook = null;
            excelWorkbook = new XSSFWorkbook(file);
            XSSFSheet excelSheet;
            int num=excelWorkbook.getSheetIndex(excelSheetName);
            XSSFRow row;
            int rowNumber = 0;
            int columnNumber = 0;
            if(num==(-1)){
                excelSheet = excelWorkbook.createSheet(excelSheetName);
            }
            excelSheet = excelWorkbook.getSheet(excelSheetName);
            rowNumber=excelSheet.getPhysicalNumberOfRows();
            for (AG_Classifications_Validation aGClassificationValue : classificationValues) {
                String principalPosition = masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0);
                row = excelSheet.createRow(rowNumber++);
                row.createCell(columnNumber++).setCellValue(aGClassificationValue.getStandardName());
                excelSheet.autoSizeColumn(0);
                row.createCell(columnNumber++).setCellValue(aGClassificationValue.getClassificationLevel());
                excelSheet.autoSizeColumn(columnNumber-1);
                if(excelSheetName.equalsIgnoreCase("Classifications")) {
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getSystemClassification());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getConfirmClassification());
                    excelSheet.autoSizeColumn(columnNumber-1);
                }
                if(excelSheetName.equalsIgnoreCase("Classification Summary")){
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPurchaseOption());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getTransferOfOwnership());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getSpecializedAsset());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }else{
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getInterestRate());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getUsefulLifeYear());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getUsefulLifeMonth());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingUsefulLifeYear());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingUsefulLifeMonth());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingTermYear());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingTermMonth());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseTermThreshold());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseTermToUsefulLife());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getInfiniteUsefulLife());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getFmv_UnitReassessed());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvTotal_Unit());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvmlpTotal_Unit());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvThreshold());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvToFMV());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }else{
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getNetLeaseInvestment());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvOfURV());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvOfLeasePayment());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseReceivable());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getDeferredIdcTotal());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getProfitLoss());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Balance")){
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getOverrideCapitalizationDate());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetGBV());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetAD());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyCurrentYearAD());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetGBV());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetAD());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseCurrentYearAD());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }else{
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccumulatedDepreciation());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccruedRent_DeferredLeaseClearing());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCarryingAmountOfNetInvestment());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccruedInterestExpense());
                    excelSheet.autoSizeColumn(columnNumber-1);
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyImpairmentReserveBalance());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetNonRecoverableImpairmentLoss());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetImpairmentReserveBalance());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetNonRecoverableImpairmentLoss());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getStraightLineDepreciation());
                    excelSheet.autoSizeColumn(columnNumber-1);
                }
                columnNumber = 0;
            }
            file.close();
            FileOutputStream fileOutput = new FileOutputStream(baseValueExcelFile);
            excelWorkbook.write(fileOutput);
            fileOutput.close();
            excelWorkbook.close();
        } catch (IOException e) {e.printStackTrace();}

    }
    public void compareByDocumentType(ArrayList<AG_Classifications_Validation> classificationValuesToCompare, String classificationLevel,
                                      String excelSheetName, String classificationsLevel) {

        ArrayList<AG_Classifications_Validation> excelClassificationValidations = new ArrayList<>();
        List<Row> rows = excelDataSet.toRows();
        for (Row row : rows) {
            if (classificationLevel.equalsIgnoreCase(row.getValue(columnMap.get("Classification Level")).toString()))  {
                AG_Classifications_Validation classificationValue = new AG_Classifications_Validation();
                setObjectValues(classificationValue, row, excelSheetName);
                excelClassificationValidations.add(classificationValue);
            }
        }
        compareObjects(excelClassificationValidations, classificationValuesToCompare, classificationLevel, excelSheetName, classificationsLevel);
        System.out.println("\n" + classificationLevel + " Values, Excel Values" + " Classification Level");
        printObjectValues(excelClassificationValidations, classificationLevel, excelSheetName);
        System.out.println("\n" + classificationLevel + "  Values, Application Values" + " Classification Level");
        printObjectValues(classificationValuesToCompare, classificationLevel, excelSheetName);
    }

    public void setObjectValues(AG_Classifications_Validation classificationValue, Row row, String sheet) {

        String principalPosition = masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0);
        classificationValue.setStandardName(row.getValue(columnMap.get("Standard Name")).toString());
        classificationValue.setClassificationLevel(row.getValue(columnMap.get("Classification Level")).toString());
        if (sheet.equalsIgnoreCase("Classifications")) {
            classificationValue.setSystemClassification(row.getValue(columnMap.get("System Classification")).toString());
            classificationValue.setConfirmClassification(row.getValue(columnMap.get("Confirm Classification")).toString());
        }
        if (sheet.equalsIgnoreCase("Classification Summary")) {
            classificationValue.setPurchaseOption(row.getValue(columnMap.get("Purchase Option")).toString());
            if (principalPosition.equalsIgnoreCase("Lessor")) {
                classificationValue.setInterestRate(row.getValue(columnMap.get("Interest Rate")).toString());
            } else {
                classificationValue.setTransferOfOwnership(row.getValue(columnMap.get("Transfer of Ownership")).toString());
                classificationValue.setSpecializedAsset(row.getValue(columnMap.get("Specialized Asset")).toString());
            }
            classificationValue.setUsefulLifeYear(row.getValue(columnMap.get("Useful Life (Year)")).toString());
            classificationValue.setUsefulLifeMonth(row.getValue(columnMap.get("Useful Life (Month)")).toString());
            classificationValue.setRemainingUsefulLifeYear(row.getValue(columnMap.get("Remaining Useful Life (Year)")).toString());
            classificationValue.setRemainingUsefulLifeMonth(row.getValue(columnMap.get("Remaining Useful Life (Month")).toString());
            classificationValue.setRemainingTermYear(row.getValue(columnMap.get("Remaining Term (Year)")).toString());
            classificationValue.setRemainingTermMonth(row.getValue(columnMap.get("Remaining Term (Month)")).toString());

            if (principalPosition.equalsIgnoreCase("Lessee")) {
                classificationValue.setLeaseTermThreshold(row.getValue(columnMap.get("Lease Term Threshold")).toString());
                classificationValue.setLeaseTermToUsefulLife(row.getValue(columnMap.get("Lease Term to Useful Life")).toString());
            }
            classificationValue.setInfiniteUsefulLife(row.getValue(columnMap.get("Infinite Useful Life")).toString());
            classificationValue.setFmv_UnitReassessed(row.getValue(columnMap.get("FMV / Unit (Reassessed)")).toString());
            if (principalPosition.equalsIgnoreCase("Lessee")) {
                classificationValue.setPvTotal_Unit(row.getValue(columnMap.get("PV (Total / Unit)")).toString());
                classificationValue.setPvmlpTotal_Unit(row.getValue(columnMap.get("PVMLP (Total / Unit)")).toString());
                classificationValue.setPvThreshold(row.getValue(columnMap.get("PV Threshold")).toString());
                classificationValue.setPvToFMV(row.getValue(columnMap.get("PV to FMV")).toString());
            } else {
                classificationValue.setNetLeaseInvestment(row.getValue(columnMap.get("Net Lease Investment")).toString());
                classificationValue.setPvOfURV(row.getValue(columnMap.get("PV of URV")).toString());
                classificationValue.setPvOfLeasePayment(row.getValue(columnMap.get("PV of Lease Payment")).toString());
                classificationValue.setLeaseReceivable(row.getValue(columnMap.get("Lease Receivable")).toString());
                classificationValue.setDeferredIdcTotal(row.getValue(columnMap.get("Deferred IDC Total")).toString());
                classificationValue.setProfitLoss(row.getValue(columnMap.get("Profit/Loss")).toString());
            }
        }
        if (sheet.equalsIgnoreCase("Carry-Over Balance")) {
            classificationValue.setOverrideCapitalizationDate(row.getValue(columnMap.get("Override Capitalization Date")).toString());
            if (principalPosition.equalsIgnoreCase("Lessee")) {
                classificationValue.setCompanyAssetGBV(row.getValue(columnMap.get("Company Asset GBV")).toString());
                classificationValue.setCompanyAssetAD(row.getValue(columnMap.get("Company Asset AD")).toString());
                classificationValue.setCompanyCurrentYearAD(row.getValue(columnMap.get("Company Current Year AD")).toString());
                classificationValue.setLeaseAssetGBV(row.getValue(columnMap.get("Lease Asset GBV")).toString());
                classificationValue.setLeaseAssetAD(row.getValue(columnMap.get("Lease Asset AD")).toString());
                classificationValue.setLeaseCurrentYearAD(row.getValue(columnMap.get("Lease Current Year AD")).toString());
            } else {
                classificationValue.setAccumulatedDepreciation(row.getValue(columnMap.get("Accumulated Depreciation")).toString());
                classificationValue.setAccruedRent_DeferredLeaseClearing(row.getValue(columnMap.get("Accrued Rent Clearing/Deferred Lease Clearing")).toString());
                classificationValue.setCarryingAmountOfNetInvestment(row.getValue(columnMap.get("Carrying Amount of Net Investment")).toString());
            }
        }
        if (sheet.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")) {
            classificationValue.setAccruedInterestExpense(row.getValue(columnMap.get("Accrued Interest Expense")).toString());
        }
        if (sheet.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")) {
            classificationValue.setCompanyImpairmentReserveBalance(row.getValue(columnMap.get("Company Impairment Reserve Balance")).toString());
            classificationValue.setCompanyAssetNonRecoverableImpairmentLoss(row.getValue(columnMap.get("Company Asset Non-Recoverable Impairment Loss")).toString());
            classificationValue.setLeaseAssetImpairmentReserveBalance(row.getValue(columnMap.get("Lease Asset Impairment Reserve Balance")).toString());
            classificationValue.setLeaseAssetNonRecoverableImpairmentLoss(row.getValue(columnMap.get("Lease Asset Non-Recoverable Impairment Loss")).toString());
            classificationValue.setStraightLineDepreciation(row.getValue(columnMap.get("Impairment Carry Over Balances are NOT applicable for GAAP")).toString());
        }
    }
    public void compareObjects(ArrayList<AG_Classifications_Validation> excelObjects, ArrayList<AG_Classifications_Validation> nlaObjects, String classificationLevel,
                               String sheet, String journalLevel) {
        String principalPosition = masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0);
        if (excelObjects.size() != nlaObjects.size()) {
            System.out.println("Mismatch in object count for classification Level: " + classificationLevel + " in Sheet: " + sheet);
            System.out.println("Excel records count: " + excelObjects.size() + " | NLA records count: " + nlaObjects.size());
            Assert.fail("Record count mismatch between Excel and NLA data for classification Level: " + classificationLevel + " in Sheet: " + sheet);
        }
        for (AG_Classifications_Validation nlaObject : nlaObjects) {
            for (AG_Classifications_Validation excelObject : excelObjects) {
                standard_Type = nlaObject.getStandardName();
                classification_Level = nlaObject.getClassificationLevel();
                if (classificationLevel.equalsIgnoreCase(excelObject.getClassificationLevel())) {
                    System.out.println("\n----- Classification Values " + excelObject.getClassificationLevel() + " Validation Starting -----");
                    compareResults("Standard Name", excelObject.getStandardName(), nlaObject.getStandardName(),sheet);
                    compareResults("Classification Level", excelObject.getClassificationLevel(), nlaObject.getClassificationLevel(),sheet);

                    if (sheet.equalsIgnoreCase("Classifications")) {
                        compareResults("System Classification", excelObject.getSystemClassification(), nlaObject.getSystemClassification(),sheet);
                        compareResults("Confirm Classification", excelObject.getConfirmClassification(), nlaObject.getConfirmClassification(),sheet);
                    }
                    if (sheet.equalsIgnoreCase("Classification Summary")) {
                        compareResults("Purchase Option", excelObject.getPurchaseOption(), nlaObject.getPurchaseOption(),sheet);
                        if (principalPosition.equalsIgnoreCase("Lessor")) {
                            compareResults("Interest Rate", excelObject.getInterestRate(), nlaObject.getInterestRate(),sheet);
                        } else {
                            compareResults("Transfer of Ownership", excelObject.getTransferOfOwnership(), nlaObject.getTransferOfOwnership(),sheet);
                            compareResults("Specialized Asset", excelObject.getTransferOfOwnership(), nlaObject.getTransferOfOwnership(),sheet);

                        }
                        compareResults("Useful Life (Year)", excelObject.getUsefulLifeYear(), nlaObject.getUsefulLifeYear(),sheet);
                        compareResults("Useful Life (Month)", excelObject.getUsefulLifeMonth(), nlaObject.getUsefulLifeMonth(),sheet);
                        compareResults("Remaining Useful Life (Year)", excelObject.getRemainingUsefulLifeYear(), nlaObject.getRemainingUsefulLifeYear(),sheet);
                        compareResults("Remaining Useful Life (Month)", excelObject.getRemainingUsefulLifeMonth(), nlaObject.getRemainingUsefulLifeMonth(),sheet);
                        compareResults("Remaining Term (Year)", excelObject.getRemainingTermYear(), nlaObject.getRemainingTermYear(),sheet);
                        compareResults("Remaining Term (Month)", excelObject.getRemainingTermMonth(), nlaObject.getRemainingTermMonth(),sheet);

                        if (principalPosition.equalsIgnoreCase("Lessee")) {
                            compareResults("Lease Term Threshold", excelObject.getLeaseTermThreshold(), nlaObject.getLeaseTermThreshold(),sheet);
                            compareResults("Lease Term to Useful Life", excelObject.getLeaseTermToUsefulLife(), nlaObject.getLeaseTermToUsefulLife(),sheet);
                        }
                        compareResults("Infinite Useful Life", excelObject.getInfiniteUsefulLife(), nlaObject.getInfiniteUsefulLife(),sheet);
                        compareResults("FMV / Unit (Reassessed)", excelObject.getFmv_UnitReassessed(), nlaObject.getFmv_UnitReassessed(),sheet);
                        if (principalPosition.equalsIgnoreCase("Lessee")) {
                            compareResults("PV (Total / Unit)", excelObject.getPvTotal_Unit(), nlaObject.getPvTotal_Unit(),sheet);
                            compareResults("PVMLP (Total / Unit)", excelObject.getPvmlpTotal_Unit(), nlaObject.getPvmlpTotal_Unit(),sheet);
                            compareResults("PV Threshold", excelObject.getPvThreshold(), nlaObject.getPvThreshold(),sheet);
                            compareResults("PV to FMV", excelObject.getPvToFMV(), nlaObject.getPvToFMV(),sheet);
                        } else {
                            compareResults("Net Lease Investment", excelObject.getNetLeaseInvestment(), nlaObject.getNetLeaseInvestment(),sheet);
                            compareResults("PV of URV", excelObject.getPvOfURV(), nlaObject.getPvOfURV(),sheet);
                            compareResults("PV of Lease Payment", excelObject.getPvOfLeasePayment(), nlaObject.getPvOfLeasePayment(),sheet);
                            compareResults("Lease Receivable", excelObject.getLeaseReceivable(), nlaObject.getLeaseReceivable(),sheet);
                            compareResults("Deferred IDC Total", excelObject.getDeferredIdcTotal(), nlaObject.getDeferredIdcTotal(),sheet);
                            compareResults("Profit/Loss", excelObject.getProfitLoss(), nlaObject.getProfitLoss(),sheet);
                        }
                    }
                    if (sheet.equalsIgnoreCase("Carry-Over Balance")) {
                        compareResults("Override Capitalization Date", excelObject.getOverrideCapitalizationDate(), nlaObject.getOverrideCapitalizationDate(),sheet);
                        if (principalPosition.equalsIgnoreCase("Lessee")) {
                            compareResults("Company Asset GBV", excelObject.getCompanyAssetGBV(), nlaObject.getCompanyAssetGBV(),sheet);
                            compareResults("Company Asset AD", excelObject.getCompanyAssetAD(), nlaObject.getCompanyAssetAD(),sheet);
                            compareResults("Company Current Year AD", excelObject.getCompanyCurrentYearAD(), nlaObject.getCompanyCurrentYearAD(),sheet);
                            compareResults("Lease Asset GBV", excelObject.getLeaseAssetGBV(), nlaObject.getLeaseAssetGBV(),sheet);
                            compareResults("Lease Asset AD", excelObject.getLeaseAssetAD(), nlaObject.getLeaseAssetAD(),sheet);
                            compareResults("Lease Current Year AD", excelObject.getLeaseCurrentYearAD(), nlaObject.getLeaseCurrentYearAD(),sheet);

                        } else {
                            compareResults("Accumulated Depreciation", excelObject.getAccumulatedDepreciation(), nlaObject.getAccumulatedDepreciation(),sheet);
                            compareResults("Accrued Rent Clearing/Deferred Lease Clearing", excelObject.getAccruedRent_DeferredLeaseClearing(), nlaObject.getAccruedRent_DeferredLeaseClearing(),sheet);
                            compareResults("Carrying Amount of Net Investment", excelObject.getCarryingAmountOfNetInvestment(), nlaObject.getCarryingAmountOfNetInvestment(),sheet);
                        }
                    }
                    if (sheet.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")) {
                        compareResults("Accrued Interest Expense", excelObject.getAccruedInterestExpense(), nlaObject.getAccruedInterestExpense(),sheet);
                    }
                    if (sheet.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")) {
                        compareResults("Company Impairment Reserve Balance", excelObject.getCompanyImpairmentReserveBalance(), nlaObject.getCompanyImpairmentReserveBalance(),sheet);
                        compareResults("Company Asset Non-Recoverable Impairment Loss", excelObject.getCompanyAssetNonRecoverableImpairmentLoss(), nlaObject.getCompanyAssetNonRecoverableImpairmentLoss(),sheet);
                        compareResults("Lease Asset Impairment Reserve Balance", excelObject.getLeaseAssetImpairmentReserveBalance(), nlaObject.getLeaseAssetImpairmentReserveBalance(),sheet);
                        compareResults("Lease Asset Non-Recoverable Impairment Loss", excelObject.getLeaseAssetNonRecoverableImpairmentLoss(), nlaObject.getLeaseAssetNonRecoverableImpairmentLoss(),sheet);
                        compareResults("Impairment Carry Over Balances are NOT applicable for GAAP", excelObject.getStraightLineDepreciation(), nlaObject.getStraightLineDepreciation(),sheet);

                    }
                    System.out.println("----- Classification Values " + excelObject.getClassificationLevel() + " Validation Ending -----");
                    excelObjects.remove(excelObject);
                    break;
                }

            }
        }

    }
    public boolean compareResults(String fieldName, String excelObjectValue, String nlaObjectValue, String excelSheetName) {
        double actualDifferenceValue = 0.0;
        if (!excelObjectValue.equals(nlaObjectValue)) {
            if (excelObjectValue.contains(".") || nlaObjectValue.contains(".")) {
                if (excelObjectValue.contains("%") || nlaObjectValue.contains("%")) {
                    nlaObjectValue = nlaObjectValue.replace("%", "");
                    excelObjectValue = excelObjectValue.replace("%", "");
                }
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
                                actualDifferenceValue, standard_Type, classification_Level);
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
                            actualDifferenceValue, standard_Type, classification_Level);
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
    public void printObjectValues(ArrayList<AG_Classifications_Validation> classificationValues, String postingType, String excelSheetName) {
        for (AG_Classifications_Validation objectValues : classificationValues) {
            if (postingType.equalsIgnoreCase(objectValues.getClassificationLevel())) {
                String principalPosition = masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0);
                System.out.print(objectValues.getStandardName() + "|");
                System.out.print(objectValues.getClassificationLevel() + "|");
                if(excelSheetName.equalsIgnoreCase("Classifications")) {
                    System.out.print(objectValues.getSystemClassification() + "|");
                    System.out.print(objectValues.getConfirmClassification() + "|");
                }
                if(excelSheetName.equalsIgnoreCase("Classification Summary")){
                    System.out.print(objectValues.getPurchaseOption() + "|");
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        System.out.print(objectValues.getTransferOfOwnership() + "|");
                        System.out.print(objectValues.getSpecializedAsset() + "|");
                    }else{
                        System.out.print(objectValues.getInterestRate() + "|");
                    }
                    System.out.print(objectValues.getUsefulLifeYear() + "|");
                    System.out.print(objectValues.getUsefulLifeMonth() + "|");
                    System.out.print(objectValues.getRemainingUsefulLifeYear() + "|");
                    System.out.print(objectValues.getRemainingUsefulLifeMonth() + "|");
                    System.out.print(objectValues.getRemainingTermYear() + "|");
                    System.out.print(objectValues.getRemainingTermMonth() + "|");
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        System.out.print(objectValues.getLeaseTermThreshold() + "|");
                        System.out.print(objectValues.getLeaseTermToUsefulLife() + "|");
                    }
                    System.out.print(objectValues.getInfiniteUsefulLife() + "|");
                    System.out.print(objectValues.getFmv_UnitReassessed() + "|");
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        System.out.print(objectValues.getPvTotal_Unit() + "|");
                        System.out.print(objectValues.getPvmlpTotal_Unit() + "|");
                        System.out.print(objectValues.getPvThreshold() + "|");
                        System.out.print(objectValues.getPvToFMV() + "|");
                    }else{
                        System.out.print(objectValues.getNetLeaseInvestment() + "|");
                        System.out.print(objectValues.getPvOfURV() + "|");
                        System.out.print(objectValues.getPvOfLeasePayment() + "|");
                        System.out.print(objectValues.getLeaseReceivable() + "|");
                        System.out.print(objectValues.getDeferredIdcTotal() + "|");
                        System.out.print(objectValues.getProfitLoss() + "|");
                    }
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Balance")){
                    System.out.print(objectValues.getOverrideCapitalizationDate() + "|");
                    if(principalPosition.equalsIgnoreCase("Lessee")) {
                        System.out.print(objectValues.getCompanyAssetGBV() + "|");
                        System.out.print(objectValues.getCompanyAssetAD() + "|");
                        System.out.print(objectValues.getCompanyCurrentYearAD() + "|");
                        System.out.print(objectValues.getLeaseAssetGBV() + "|");
                        System.out.print(objectValues.getLeaseAssetAD() + "|");
                        System.out.print(objectValues.getLeaseCurrentYearAD() + "|");
                    }else{
                        System.out.print(objectValues.getAccumulatedDepreciation() + "|");
                        System.out.print(objectValues.getAccruedRent_DeferredLeaseClearing() + "|");
                        System.out.print(objectValues.getCarryingAmountOfNetInvestment() + "|");
                    }
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                    System.out.print(objectValues.getAccruedInterestExpense() + "|");
                }
                if(excelSheetName.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                    System.out.print(objectValues.getCompanyImpairmentReserveBalance() + "|");
                    System.out.print(objectValues.getCompanyAssetNonRecoverableImpairmentLoss() + "|");
                    System.out.print(objectValues.getLeaseAssetImpairmentReserveBalance() + "|");
                    System.out.print(objectValues.getLeaseAssetNonRecoverableImpairmentLoss() + "|");
                    System.out.print(objectValues.getStraightLineDepreciation() + "|");
                }
            }

        }
    }
    public void writeMisMatchExcelValues(String sheetName, String fieldName, String excelValue, String nlaValue, Double allowedDifference,
                                         Double actualDifference, String standard_Type, String classificationLevel) throws IOException {
        copyMisMatchExcelTemplateFile(sheetName);
        MasterHooks.checkForValidationFailures.set("Failed");
        try {
            FileInputStream file = new FileInputStream(misMatchExcelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int sheetIndex = workbook.getSheetIndex(sheetName);
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            XSSFRow row;

            int rowNumber = 0;
            int columnNumber = 0;

            rowNumber = sheet.getLastRowNum() + 1;

            row = sheet.createRow(rowNumber++);
            row.createCell(columnNumber++).setCellValue(MasterAgreement_PageObject.testCaseName.get());
            sheet.autoSizeColumn(columnNumber);
            row.createCell(columnNumber++).setCellValue(sheetName);
            sheet.autoSizeColumn(columnNumber);
            row.createCell(columnNumber++).setCellValue(standard_Type);
            sheet.autoSizeColumn(columnNumber);
            row.createCell(columnNumber++).setCellValue(classificationLevel);
            sheet.autoSizeColumn(columnNumber);
            row.createCell(columnNumber++).setCellValue(fieldName);
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
        }catch(Exception e) {e.printStackTrace(); }
    }
    public void copyMisMatchExcelTemplateFile(String sheetName) {
        try {
            XSSFWorkbook excelWorkbook=null;
            File validationFolder= new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/");
            misMatchExcelFile = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "/"
                    + MasterAgreement_PageObject.testCaseName.get() + "_Classifications.xlsx");
            if(!validationFolder.exists()){
                validationFolder.mkdirs();
            }
            boolean flag=false;
            if (misMatchExcelFile.exists()){
                file = new FileInputStream(misMatchExcelFile);
                try {
                    excelWorkbook = new XSSFWorkbook(file);
                }catch(Exception e) {e.printStackTrace();}
                flag=true;
            }else{
                excelWorkbook = new XSSFWorkbook();
            }
            int num=excelWorkbook.getSheetIndex(sheetName);
            if(num==-1){
                XSSFSheet excelSheet = excelWorkbook.createSheet(sheetName);
                XSSFRow row;
                int columnNumber = 0;

                row = excelSheet.createRow(0);
                row.createCell(columnNumber).setCellValue("Test Case Number");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Sheet Name");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Standard");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Classification Level");
                excelSheet.autoSizeColumn(columnNumber++);
                row.createCell(columnNumber).setCellValue("Field Name");
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
            if(flag) {
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeClassificationHeadings(ArrayList<AG_Classifications_Validation> aGClassificationValues, String excelSheetName, File baseValueExcelFile)  {

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
                excelSheet = excelWorkbook.getSheet(excelSheetName);
                rowNumber=excelSheet.getPhysicalNumberOfRows();
                for (AG_Classifications_Validation aGClassificationValue : aGClassificationValues) {
                    String principalPosition = masterAgreementPageObject.getInputValues().get("Inception").get("Contract Level").get("Principal Position").get(0);
                    row = excelSheet.createRow(rowNumber++);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getStandardName());
                    excelSheet.autoSizeColumn(0);
                    row.createCell(columnNumber++).setCellValue(aGClassificationValue.getClassificationLevel());
                    excelSheet.autoSizeColumn(columnNumber-1);
                    if(excelSheetName.equalsIgnoreCase("Classifications")){
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getSystemClassification());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getConfirmClassification());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                    if(excelSheetName.equalsIgnoreCase("Classification Summary")){
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPurchaseOption());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        if(principalPosition.equalsIgnoreCase("Lessee")) {
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getTransferOfOwnership());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getSpecializedAsset());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }else{
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getInterestRate());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getUsefulLifeYear());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getUsefulLifeMonth());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingUsefulLifeYear());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingUsefulLifeMonth());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingTermYear());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getRemainingTermMonth());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        if(principalPosition.equalsIgnoreCase("Lessee")) {
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseTermThreshold());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseTermToUsefulLife());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getInfiniteUsefulLife());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getFmv_UnitReassessed());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        if(principalPosition.equalsIgnoreCase("Lessee")) {
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvTotal_Unit());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvmlpTotal_Unit());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvThreshold());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvToFMV());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }else{
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getNetLeaseInvestment());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvOfURV());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getPvOfLeasePayment());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseReceivable());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getDeferredIdcTotal());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getProfitLoss());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }
                    }
                    if(excelSheetName.equalsIgnoreCase("Carry-Over Balance")){
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getOverrideCapitalizationDate());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        if(principalPosition.equalsIgnoreCase("Lessee")) {
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetGBV());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetAD());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyCurrentYearAD());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetGBV());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetAD());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseCurrentYearAD());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }else{
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccumulatedDepreciation());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccruedRent_DeferredLeaseClearing());
                            excelSheet.autoSizeColumn(columnNumber-1);
                            row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCarryingAmountOfNetInvestment());
                            excelSheet.autoSizeColumn(columnNumber-1);
                        }
                    }
                    if(excelSheetName.equalsIgnoreCase("Carry-Over Liability Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getAccruedInterestExpense());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                    if(excelSheetName.equalsIgnoreCase("Carry-Over Impairment Balance") && principalPosition.equalsIgnoreCase("Lessee")){
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyImpairmentReserveBalance());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getCompanyAssetNonRecoverableImpairmentLoss());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetImpairmentReserveBalance());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getLeaseAssetNonRecoverableImpairmentLoss());
                        excelSheet.autoSizeColumn(columnNumber-1);
                        row.createCell(columnNumber++).setCellValue(aGClassificationValue.getStraightLineDepreciation());
                        excelSheet.autoSizeColumn(columnNumber-1);
                    }
                    columnNumber = 0;
                }
                if (!(file == null)){
                    file.close();
                }
                FileOutputStream fileOutput = new FileOutputStream(baseValueExcelFile);
                excelWorkbook.write(fileOutput);
                fileOutput.close();
            }

            excelWorkbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

