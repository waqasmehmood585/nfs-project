Feature: NFS-13357 Test Case
  NFS-13357: Golden: NLA - Verify Optional Asset Class behavior for Certain Lease
  Types (Low Value, Short Term, Non Lease Service Contract) - Import/Export

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Asset Class behavior for (Low Value, Short Term, Non Lease Service Contract) - Import/Export
  #Jira_ID:NFS-13357
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @Import
  Scenario Outline: NFS-13357 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "NFS-13357"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "NFS-13357"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "NFS-13357"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to get Record ID
    Then User Search "Lease Component" with ID
    Then User verify that "Lease Component" status is "Define"
    Then User enters the Terms and Conditions tab of Lease Component
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition

    Examples:
      | testCaseNumber |
      | NFS-13357      |
