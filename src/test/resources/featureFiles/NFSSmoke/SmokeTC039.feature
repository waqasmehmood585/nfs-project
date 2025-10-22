Feature: SmokeTC039 Test Case
  NFS-7983 SmokeTC # 39: Perform Mass Import Jobs for Lessor

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC039 - Perform Mass Import Job for Lessor
  #Jira_ID:NFS-7983
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:N2025.R1
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC039 Test Case
    #--------------------Mass Import Contact--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contact" with Excel File "SmokeTC015_Contact"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport |
      | null       | null               | null            | null        | null           |
    And user open report of "Contact" to get Record ID
    #--------------------Mass Import MLA--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC039_MLA_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Master Agreement" with ID
    Then user enters the Partners tab of "Master Agreement" to validate Contact Import
    Then User verify that "Master Agreement" status is "Active"
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC039_CT_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSR Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Contract" with ID
    Then user enters the Partners tab of "Contract" to validate Contact Import
    Then User verify that "Contract" status is "Active"
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "SmokeTC039_LC_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSR Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Lease Component" with ID
    Then User verify that "Lease Component" status is "Active"
    #--------------------Mass Import Unit--------------------------
    Then User click on the "Activation Group" with Name "SmokeTC039_AG_LSR"
    Then User enters the Unit List tab of Activation Group
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Unit" with Excel File "SmokeTC016_UN"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport               |
      | null       | Lessor             | Yes             | Delivered   | Unit,Cost Center Allocations |
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Charge--------------------------
    Then User Search "Activation Group" with ID
    Then User enters the Charge List tab of Activation Group
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Charge" with Excel File "SmokeTC039_Charge_LSR"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport               |
      | null       | null               | null            | null        | Charge,Vendor Payment Splits |
    Then User Search "Activation Group" with ID
    Then User enters the Charge List tab of Activation Group
    And user validated the Records Import on "Charge"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2,3"
    #--------------------Mass Import Activation Group--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Activation Group" with Excel File "SmokeTC039_AG_LSR"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport                        |
      | null       | Lessor             | Yes             | Active      | Definition,Accounting,Classifications |
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Activation Group" with ID
    Then User verify that "Activation Group" status is "Active"

    Examples:
      | testCaseNumber |
      | SmokeTC039     |
