package com.nakisa.nlaAutomation.utils;

import com.nakisa.nlaAutomation.Listeners.CustomAbstractTestNGCucumberTests;
import com.nakisa.nlaAutomation.pageObjects.MasterAgreement_PageObject;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nakisa.nlaAutomation.stepDefinitions.MasterHooks.*;

public class CommonExcelValidation {
    private static CSVWriter writer;
    public static boolean csvCreated = false;

//  public static String filePath = "Automation-Results";

    public static void RetryEntryToExcel(String testCaseID) {

        try {
            File fis = new File(MasterHooks.retryPath.get());
            FileInputStream inputStream = new FileInputStream(fis);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Retry");

            int rowNum = findRow(sheet, testCaseID);
            if (rowNum == -1) {
                rowNum = sheet.getLastRowNum() + 1;
                Row newRow = sheet.createRow(rowNum);
                Cell cell1 = newRow.createCell(0);
                cell1.setCellValue(testCaseID);
                Cell cell2 = newRow.createCell(1);
                cell2.setCellValue(0);
            } else {
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(1);
                int currentCount = (int) cell.getNumericCellValue();
                cell.setCellValue(currentCount + 1);
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(fis);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("Retry Excel file updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // return retry count w.r.t test case id
    public static int getRetryCount(String tc_id) {
        try {
            File fis = new File(MasterHooks.retryPath.get());
            FileInputStream inputStream = new FileInputStream(fis);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("Retry");

            int rowNum = findRow(sheet, tc_id);
            if (rowNum != -1) {
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(1); // Column 2 (index 1) for retry count
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    return (int) cell.getNumericCellValue();
                }
            }
            inputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Default retry count if not found or error occurs
    }

    //returning Row Number if Test Case ID already exist
    private static int findRow(Sheet sheet, String tc_id) {
        int rowCount = sheet.getLastRowNum();
        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            if (cell.getStringCellValue().equals(tc_id)) {
                return i;
            }
        }
        return -1; // Not found
    }

    //Deleting Mismatch Files in case of Validation failure and UI failed, TC not Completed
    public static void deleteMismatchFiles() {
        File misMatchExcelFileFolder = new File("Automation-Results/validationResults/" + MasterHooks.configurationProperties.get().getWhichBaselineToUseForValidation() + "/"
                + MasterAgreement_PageObject.testCaseName.get());

        // Check if the folder exists and is a directory
        if (misMatchExcelFileFolder.exists() && misMatchExcelFileFolder.isDirectory()) {
            // Delete the folder and its contents recursively
            boolean deleted = deleteFolder(misMatchExcelFileFolder);

            if (deleted) {
                System.out.println("Mismatch Folder deleted successfully.");
            } else {
                System.err.println("Failed to delete Mismatch folder.");
            }
        } else {
            System.err.println("Mismatch Folder does not exist or is not a directory.");
        }
    }

    //delete folder by deleting inner files
    public static boolean deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Recursively delete files and sub-folders
                    boolean success = deleteFolder(file);
                    if (!success) {
                        return false; // Exit early if deletion fails
                    }
                }
            }
        }

        // Delete the empty folder or file
        return folder.delete();
    }
//delete all file inside a folder
    public static boolean deleteFiles(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // Delete file
                        boolean success = file.delete();
                        if (!success) {
                            System.err.println("Failed to delete file: " + file.getAbsolutePath());
                            return false; // Exit early if deletion fails
                        }
                    } else if (file.isDirectory()) {
                        // Recursively process subfolders to delete files inside them
                        boolean success = deleteFiles(file);
                        if (!success) {
                            return false; // Exit early if deletion fails
                        }
                    }
                }
            }
        }
        return true; // Return true if all deletions succeed
    }

    //Setting Max count to Retry in Excel
    public static void setMaxRetryCountInExcel(String tc_id, int value) {
        try {
            File fis = new File(MasterHooks.retryPath.get());
            FileInputStream inputStream = new FileInputStream(fis);
            Workbook workbook = new XSSFWorkbook(inputStream); // Use XSSFWorkbook for .xlsx files
            Sheet sheet = workbook.getSheet("Retry");

            int rowNum = findRow(sheet, tc_id);
            if (rowNum != -1) {
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(1); // Column 2 (index 1) for retry count
                if (cell == null) {
                    cell = row.createCell(1, CellType.NUMERIC);
                }
                cell.setCellValue(value);
            } else {
                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1); // Create new row
                Cell cellId = newRow.createCell(0); // Column 1 (index 0) for tc_id
                Cell cellCount = newRow.createCell(1); // Column 2 (index 1) for retry count
                cellId.setCellValue(tc_id);
                cellCount.setCellValue(value);
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(fis);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            System.out.println("Excel file updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete Folder if not file exist
    public static boolean deleteFolderIfEmpty(File folderPath) {

        if (!folderPath.exists() || !folderPath.isDirectory()) {
            // Folder does not exist or is not a directory
            return false;
        }

        // Check if the folder is empty
        String[] files = folderPath.list();
        if (files != null && files.length == 0) {
            // Delete the folder
            System.out.println("No File in exist in " + folderPath);
            System.out.println("Deleting Folder");
            return folderPath.delete();
        }
        return false; // Folder is not empty
    }

    // Checks the CSV File exist
    public static boolean containsCSVFile(String folderPath) {
        File folder = new File(folderPath);

        // Check if the specified path is a directory
        if (!folder.isDirectory()) {
            System.out.println("Invalid directory path.");
            return false;
        }

        // Get all files in the directory
        File[] files = folder.listFiles();

        // Check if files exist and look for CSV files
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    System.out.println("CSV file found: " + file.getName());
                    return true; // Found a CSV file
                }
            }
        }

        System.out.println("No CSV file found in the directory.");
        return false; // No CSV file found
    }

    // Creating CSV with Headings
    public static void createCSV(String csvFolderPath) {
        if (!containsCSVFile(csvFolderPath)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);
            String fileName= "/NFS-" + formattedDateTime + ".csv";
            String filePath = csvFolderPath + fileName;
            csvPath.set(filePath);
            CustomAbstractTestNGCucumberTests.csvFileName = fileName;
            CustomAbstractTestNGCucumberTests.csvPath=filePath;
            try {
                writer = new CSVWriter(new FileWriter(filePath));
                String[] header = {
                        "Product", "TC_ID", "TC_Category", "TC_Title","TC_Layer", "TC_Customers", "TC_FixVersion",
                        "DateTime_Started", "DateTime_Finished", "TestRun-Duration", "TestRun-Result",
                        "TestRun-Retries", "Env-CoreServiceVersion", "Env-EnvName", "Env-StackName"
                };
                writer.writeNext(header);

                // Set the flag indicating CSV creation is complete
                csvCreated = true;
                writer.close();
                System.out.println("CSV file created successfully");
            } catch (IOException e) {
                System.err.println("Error creating CSV file: " + e.getMessage());
            }
            String retryFilePath = csvFolderPath + "/RetryExcel.xlsx";
            File file = new File(retryFilePath);
            CustomAbstractTestNGCucumberTests.retryPath=retryFilePath;
            if (!file.exists()) {
                try {
                    Workbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("Retry");

                    // Create headers
                    Row headerRow = sheet.createRow(0);
                    Cell headerCell1 = headerRow.createCell(0);
                    headerCell1.setCellValue("TC_ID"); // Test Case ID
                    Cell headerCell2 = headerRow.createCell(1);
                    headerCell2.setCellValue("Retry");

                    // Resize the columns to fit content (optional)
                    sheet.autoSizeColumn(0);
                    sheet.autoSizeColumn(1);

                    // Write the workbook content to a file
                    FileOutputStream outputStream = new FileOutputStream(file);
                    workbook.write(outputStream);
                    System.out.println("Excel file created successfully.");
                } catch (Exception exception) {
                    System.err.println("Error creating Retry Excel file: " + exception.getMessage());
                }
            }
        } else {
            System.out.println("CSV & Retry file already created, skipping creation.");
        }
    }

    //Collecting Results data in after method
    public static void endTime(Scenario scenario) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);
        MasterHooks.timeTCEnd.set(formattedDateTime);
        LocalDateTime startDateTime = LocalDateTime.parse(MasterHooks.timeTCStart.get(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(MasterHooks.timeTCEnd.get(), formatter);
        Duration duration = Duration.between(startDateTime, endDateTime);
        String formattedDuration = formatDuration(duration);
        MasterHooks.durationTC.set(formattedDuration);
        // below method will return Category, customer & FixVersion
        JsonReader.csvTestData(getTCName(scenario.getName()));
//        getLabelsFromFeatureFiles(scenario);
        // Below code returns Tags list
//        Collection<String> tags = scenario.getSourceTagNames();
//        String tcCategories = convertToString(tags);
//        MasterHooks.tcCategory.set(tcCategories);
//        MasterHooks.tcID.set(getSubstringBeforeSpace(scenario.getName()));
        MasterHooks.retries.set("0");
    }

    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
//        long millis = duration.toMillis() % 1000;

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds %= 60;

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    public static String convertToString(Collection<String> collection) {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        for (String item : collection) {
            item = item.replace("@", "");
            if (!isFirst) {
                result.append("/");
            } else {
                isFirst = false;
            }
            result.append(item);
        }
        return result.toString();
    }

    public static String getSubstringBeforeSpace(String input) {
        Pattern pattern = Pattern.compile("(.*?) Test Case");
        Matcher matcher = pattern.matcher(input);
        String value=null;
        if (matcher.find()) {
            value = matcher.group(1);
            System.out.println("Value before ' Test Case': " + value);
        } else {
            value=input;
            System.out.println("No match found.");
        }
//        int spaceIndex = input.indexOf(' ');
        return value ;
    }

    public static void addResultsInCSVFile(String tcId, String status) {
        String fileName = csvPath.get();
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            List<String[]> rows = reader.readAll();
            boolean found = false;

            for (String[] row : rows) {
                if (row.length > 1 && row[1].equals(tcId)) {
                    int number = Integer.parseInt(row[11]);
                    number++;
                    String incrementedNumberAsString = Integer.toString(number);
                    MasterHooks.retries.set(incrementedNumberAsString);
                    row[0] = "NFS";
                    row[1] = tcId;
                    row[2] = MasterHooks.tcCategory.get();
                    row[3] = MasterHooks.title.get();
                    row[4] = "UI";
                    row[5] = MasterHooks.customers.get();
                    row[6] = MasterHooks.fixVersion.get();
                    row[7] = MasterHooks.timeTCStart.get();
                    row[8] = MasterHooks.timeTCEnd.get();
                    row[9] = MasterHooks.durationTC.get();
                    row[10] = status;
                    row[11] = MasterHooks.retries.get();
                    row[12] = MasterHooks.CoreServiceVersion.get();
                    row[13] = MasterHooks.configurationProperties.get().getEnvName();
                    row[14] = MasterHooks.stackName.get();
//					String[] updatedRow = {"NFS", tcId, tcCategory.get(), "UI", timeTCStart.get(), timeTCEnd.get(),
//							durationTC.get(), testStatus.get(), retries.get(), "ToDo", "ToDo", "ToDo"};
//					writer.writeNext(updatedRow);
                    found = true;
                    break;

                }
            }
            if (found) {
                writer = new CSVWriter(new FileWriter(fileName));
                writer.writeAll(rows);
            }

            if (!found) {
                writer = new CSVWriter(new FileWriter(fileName, true));
                String[] newRow = {"NFS", tcId, MasterHooks.tcCategory.get(), MasterHooks.title.get(),"UI", MasterHooks.customers.get(), MasterHooks.fixVersion.get() ,
                        MasterHooks.timeTCStart.get(), MasterHooks.timeTCEnd.get(), MasterHooks.durationTC.get(), status, MasterHooks.retries.get(),
                        MasterHooks.CoreServiceVersion.get(), MasterHooks.configurationProperties.get().getEnvName(), MasterHooks.stackName.get()};
                writer.writeNext(newRow);
            }
            writer.close();
            System.out.println("Row added/updated successfully in CSV");
        } catch (Exception e) {
            System.err.println("Error adding/updating row in CSV file: " + e.getMessage());
        }
    }

    //getting CSV file path form the Automation Results folder
    public static String getCSVFilePath(String folderPath) {
        File folder = new File(folderPath);
        List<String> csvFilePathList = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] csvFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".csv");
                }
            });

            if (csvFiles != null) {
                for (File csvFile : csvFiles) {
                    return csvFile.getAbsolutePath();
                }
            }
        }
        return "";
    }

    public static String getTCName(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        // Find the index of the first space or hyphen
        int endIndex = input.indexOf(' ');
        int hyphenIndex = input.indexOf('-');

        // If no hyphen is found, return the substring up to the space
        if (hyphenIndex == -1) {
            return input.substring(0, endIndex);
        }

        // Otherwise, return the substring up to the first occurrence of space or hyphen
        return input.substring(0, endIndex);
    }

//    public static void getLabelsFromFeatureFiles(Scenario scenario) {
//        String tcName = getTCName(scenario.getName());
//        String directoryPath ="";
//        Collection<String> tags = scenario.getSourceTagNames();
//        String tcCategories = convertToString(tags);
//        if(tcCategories.contains("walmartNUAT")){
//            directoryPath ="src/test/resources/featureFiles/Generic/";
//        }else if (tcCategories.contains("lessorAccounting")){
//            directoryPath ="src/test/resources/featureFiles/Lessor/";
//        }else if (tcCategories.contains("Smoke")){
//            directoryPath ="src/test/resources/featureFiles/NFSSmoke/";
//        } else if (tcCategories.contains("pfizer")){
//            directoryPath ="src/test/resources/featureFiles/Pfizer/";
//        }else if (tcCategories.contains("PGL")){
//            directoryPath ="src/test/resources/featureFiles/PGL/";
//        }else if (tcCategories.contains("provision")){
//            directoryPath ="src/test/resources/featureFiles/Provision/";
//        }else if (tcCategories.contains("walmartSouthAfrica")){
//            directoryPath ="src/test/resources/featureFiles/SouthAfrica/";
//        }else if (tcCategories.contains("UF/CLP")){
//            directoryPath ="src/test/resources/featureFiles/UF-CLP/";
//        }else if (tcCategories.contains("waters")){
//            directoryPath ="src/test/resources/featureFiles/Water/";
//        }else if (tcCategories.contains("walmartPreUpgrade")){
//            directoryPath ="src/test/resources/featureFiles/WM-Preupgrade/";
//        }else if (tcCategories.contains("Xray")){
//            directoryPath ="src/test/resources/featureFiles/XrayValidation/";
//        }
//
//        String wildcardPattern = tcName + ".feature";
//
//        //Reading Feature files to get category, customer and FixVersion
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(directoryPath + wildcardPattern));
//
//            String line;
//
//            // Regular expression to match custom label comments
//            Pattern patternCategory = Pattern.compile("#TC_Category:(.+)");
//            Pattern patternCustomer = Pattern.compile("#TC_Customers:(.+)");
//            Pattern patternFixVersion = Pattern.compile("#TC_FixVersion:(.+)");
//            Pattern patternTitle = Pattern.compile("#TC_Title:(.+)");
//            Pattern patternTcId = Pattern.compile("#Jira_ID:(.+)");
//
//            while ((line = reader.readLine()) != null) {
//                Matcher matcherCategory = patternCategory.matcher(line);
//                if (matcherCategory.find()) {
//                    tcCategory.set(matcherCategory.group(1));
//                    break;
//                }
//            }
//            reader = new BufferedReader(new FileReader(directoryPath + wildcardPattern));
//            while ((line = reader.readLine()) != null) {
//                Matcher matcherCustomer = patternCustomer.matcher(line);
//                if (matcherCustomer.find()) {
//                    customers.set(matcherCustomer.group(1));
//                    break;
//                }
//            }
//            reader = new BufferedReader(new FileReader(directoryPath + wildcardPattern));
//            while ((line = reader.readLine()) != null) {
//                Matcher matcherFixVersion = patternFixVersion.matcher(line);
//                if (matcherFixVersion.find()) {
//                    fixVersion.set(matcherFixVersion.group(1));
//                    break;
//                }
//            }
//            reader = new BufferedReader(new FileReader(directoryPath + wildcardPattern));
//            while ((line = reader.readLine()) != null) {
//                Matcher matcherTitle = patternTitle.matcher(line);
//                if (matcherTitle.find()) {
//                    title.set(matcherTitle.group(1));
//                    break;
//                }
//            }
//            reader = new BufferedReader(new FileReader(directoryPath + wildcardPattern));
//            while ((line = reader.readLine()) != null) {
//                Matcher matcherTcId = patternTcId.matcher(line);
//                if (matcherTcId.find()) {
//                    tcID.set(matcherTcId.group(1));
//                    break;
//                }
//            }
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static String lastModifiedFile(String path){

        // Create a File object representing the folder
        File folder = new File(path);

        // List all directories in the folder
        File[] directories = folder.listFiles(File::isDirectory);

        if (directories != null && directories.length > 0) {
            File[] reportDirectories = Arrays.stream(directories)
                    .filter(dir -> dir.getName().startsWith("Report"))
                    .toArray(File[]::new);
            if (reportDirectories.length > 0) {
                // Sort directories by last modified time in descending order
                Arrays.sort(reportDirectories, Comparator.comparingLong(File::lastModified).reversed());

                // Get the last created directory
                File lastCreatedDirectory = reportDirectories[0];

                // Print the path of the last created directory
                System.out.println("Last created directory: " + lastCreatedDirectory.getAbsolutePath());
                return lastCreatedDirectory.getAbsolutePath();
            }else{
                System.out.println("No directories starting with 'Report' found in the specified folder.");
                return null;
            }
        } else {
            System.out.println("No directories found in the specified folder.");
            return null;
        }
    }
}
