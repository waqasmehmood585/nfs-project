Feature: NFS-6799 Test Case
  NFS-6799 -  Verify Import with some unchecked check boxes and Profit Center not a mandatory field
  also check import of all Other Cost Objects

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Import with some unchecked check boxes and Profit Center not a mandatory field
  #Jira_ID:NFS-6799
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Import
  Scenario Outline: NFS-6799 Test Case
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
    Then User Search "Master Agreement" with ID
    Then User verify that "Master Agreement" status is "Active"
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "NFS-6799_CT"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Lease Determination,Accounting |
    And user open report of "CT - LSE Definition" to get Record ID
    Then User Search "Contract" with ID
    Then User enters the Accounting tab to validate the Import


    Examples:
      | testCaseNumber |
      | NFS-6799       |