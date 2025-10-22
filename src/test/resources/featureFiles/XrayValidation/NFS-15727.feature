Feature: NFS-15727 Test Case
  NFS-15727: Perform Mass Import Job for Lessor (Multiple Contacts,LC,Charges)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Perform Mass Import Job for Lessor (Multiple Contacts,LC,Charges)
  #Jira_ID:NFS-15727
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:N2025.R1
  @Regression @Xray @Import
  Scenario Outline: NFS-15727 Test Case
    #--------------------Mass Import Contact--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "NFS-15727_MLA_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition     |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "NFS-15727_CT_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                             |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners |
    And user open report of "CT - LSR Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Master Agreement" with ID
    And user validated the Records Import on "Master Agreement"
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "NFS-15727_LC_LSR"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessor             | Yes             | Active      | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSR Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Contract" with ID
    And user validated the Records Import on "Contract"
    #--------------------Mass Import Unit--------------------------
    Then User Search "Lease Component" with ID
    Then User click on the "Activation Group" with Name "NFS-15727-1_AG_LSR"
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
    Then user performed Mass Import for "Charge" with Excel File "NFS-15727_Charge_LSR"
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
      | SmokeTC016     |
