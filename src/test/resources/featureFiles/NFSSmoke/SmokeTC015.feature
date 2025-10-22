Feature: SmokeTCThen the "Batch" job should be completed with status "Done" Test Case
  NFS-10876 SmokeTC # 15: Perform Mass Import Job for Contact

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC015 - Perform Mass Import Job for Contact
  #Jira_ID:NFS-10876
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  #Contact Import for MLA & Contract
  #Verify Contact, Status And Search
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC015 Import-Scenario:1 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import Contact--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contact" with Excel File "SmokeTC015_Contact"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport |
      | null       | null               | null            | null        | null           |
    And user open report of "Contact" to get Record ID
    #--------------------Mass Import MLA--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC015_MLA1.0"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Master Agreement" with ID
    Then user enters the Partners tab of "Master Agreement" to validate Contact Import
    Then User verify that "Master Agreement" status is "Active"
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC015_CT1.0"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Contract" with ID
    Then user enters the Partners tab of "Contract" to validate Contact Import
    Then User verify that "Contract" status is "Active"
    #--------------------Mass Import Contract (Checking the Error Message)-------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user verify Error message of Mass Import for "Master Agreement" with Wrong Excel File "SmokeTC015_Contract_Error"
      | ERP System              | Principal Position | Alert Message                                                      |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided workbook does not contain any sheet named MA - Definition |
    Then user verify Error message of Mass Import for "Contract" with Wrong Excel File "SmokeTC015_MLA_Error"
      | ERP System              | Principal Position | Alert Message                                                          |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided workbook does not contain any sheet named CT - LSE Definition |
    Then user verify Error message of Mass Import for "Lease Component" with Wrong Excel File "SmokeTC015_Contract_Error"
      | ERP System              | Principal Position | Alert Message                                                          |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided workbook does not contain any sheet named LC - LSE Definition |
    Then user verify Error message of Mass Import for "Charge" with Wrong Excel File "SmokeTC015_MLA_Error"
      | ERP System              | Principal Position | Alert Message                                             |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided workbook does not contain any sheet named Charge |
    Then user verify Error message of Mass Import for "Contact" with Wrong Excel File "SmokeTC015_MLA_Error"
      | ERP System              | Principal Position | Alert Message                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided workbook does not contain any sheet named Contact |
    #--------------------Mass Import Contract (Checking the Incorrect File)-------------------
    Then user verify Error message of Mass Import for "Master Agreement" with Wrong Excel File "Incorrect_File"
      | ERP System              | Principal Position | Alert Message                        |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Provided file extension is not .xlsx |
    #--------------------Mass Import MLA (Multiple Contracts Validation)--------------------------
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC015_MLA1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC015_CT1.2"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Master Agreement" with ID
    And user validated the Records Import on "Master Agreement"


    Examples:
      | testCaseNumber |
      | SmokeTC015     |