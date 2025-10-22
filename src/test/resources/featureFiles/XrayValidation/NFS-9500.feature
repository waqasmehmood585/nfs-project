Feature: NFS-9500 Test Case
  NFS-9500 - Verify that user should be able to export reports also verify the information and errors.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that user should be able to export "Import Reports" also verify the information and errors.
  #Jira_ID:NFS-9500
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Import
  Scenario Outline: NFS-9500 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import MLA--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Master Agreement" with Excel File "NFS-6799_MLA"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition     |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user download the "Import Report"
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                                   |
      | MA - Definition       | Excel ID,Year,Master Agreement Name,Lease Area Display ID,Status,Messages                     |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID,Status,Messages |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID,Messages                                   |
    Then Delete the File
    Then user download the "Original Import File"
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                   |
      | MA - Definition       | Excel ID,Year,Master Agreement Name,Lease Area Display ID                     |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID                            |
    Then Delete the File
    #--------------------Mass Import Contract--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract-Errors" with Excel File "NFS-6799_CT"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Lease Determination,Accounting |
    And user open report of "CT - LSE Definition" to get Record ID
    Then user download the "Import Report"
    Then User checks the File for Column Exist
      | Sheet Name                   | Column Name                                                                                                                                                                                                              |
      | CT - LSE Lease Determination | Contract Excel ID                                                                                                                                                                                                        |
      | CT - LSE Definition          | Excel ID,Master Agreement Excel ID,Contract Name,External Reference,Internal Reference,Lease Type,Contract Currency Display ID,Principal Position,Lease Area Display ID,Business Unit Display ID,Company Code Display ID |
      | CT - LSE Accounting          | Contract Excel ID,Contract Rate,Use Ibr Rate,Compounding Frequency,Calendar Type,Profit Center Display ID,Cost Center Display ID,Generate Vendor Invoice                                                                 |
    Then Delete the File
    Then user download the "Original Import File"
    Then User checks the File for Column Exist
      | Sheet Name                   | Column Name                                                                                                                                                                                                              |
      | CT - LSE Lease Determination | Contract Excel ID                                                                                                                                                                                                        |
      | CT - LSE Definition          | Excel ID,Master Agreement Excel ID,Contract Name,External Reference,Internal Reference,Lease Type,Contract Currency Display ID,Principal Position,Lease Area Display ID,Business Unit Display ID,Company Code Display ID |
      | CT - LSE Partners            | Contract Excel ID,Partner Display ID,Excel ID,Partner Role Display ID                                                                                                                                                    |
      | CT - Partner Contacts        | Contract Partner Excel ID,Contact Excel ID                                                                                                                                                                               |
      | CT - LSE Accounting          | Contract Excel ID,Contract Rate,Use Ibr Rate,Compounding Frequency,Calendar Type,Profit Center Display ID,Cost Center Display ID,Generate Vendor Invoice                                                                 |
      | CT - Cost Center Allocations | Contract Excel ID,Cost Center Display ID,Profit Center Display ID,Allocation Percentage,Main Cost Center                                                                                                                 |
      | CT - Notifications           | Contract Excel ID,Contract Notification Topic Type,Subscribed Username                                                                                                                                                   |
    Then Delete the File


    Examples:
      | testCaseNumber |
      | NFS-6799       |