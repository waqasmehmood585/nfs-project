Feature: NFS-10449 Test Case
  NFS-10449 COB LC-Import: Verify that all COB fields have Values in LC & AG Carry over balances page after import successfully

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that all COB fields have Values in LC & AG Carry over balances page after import successfully
  #Jira_ID:NFS-10449
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Import
  Scenario Outline: NFS-10449 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import MLA--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "NFS-10449_MLA"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "NFS-10449_CT"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "NFS-10449_LC"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                           |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Unit Distribution,Carry-Over Balance,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to get Record ID
    Then User Search "Lease Component" with ID
    Then User enters the carry over balance tab of lease component
    Then User validate all COB fields at "Lease Component"
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    Then User enters the Classification tab of Activation Group
    Then User validate all COB fields at "Activation Group"

    Examples: 
      | testCaseNumber |
      | NFS-10449      |
