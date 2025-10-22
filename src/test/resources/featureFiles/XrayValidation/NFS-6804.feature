Feature: NFS-6804 Test Case
  NFS-6804: Verify that Error Message should be clear if user enter the wrong data in Mandatory Fields of right template

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that Error Message should be clear if user enter the wrong data in Mandatory Fields of right template
  #Jira_ID:NFS-6804
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @Import
  Scenario Outline: NFS-6804 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Import Contact--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contact-Errors" with Excel File "NFS-6804"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport |
      | null       | null               | null            | null        | null           |
    And user open report of "Contact" to Check the Report "Message" as "Column Email is mandatory and a value should be provided."
    #--------------------Mass Import MLA--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement-Errors" with Excel File "NFS-6804"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition     |
    And user open report of "MA - Definition" to Check the Report "Message" as "Column Year is mandatory and a value should be provided."
    #--------------------Mass Import Contract--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract-Errors" with Excel File "NFS-6804"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to Check the Report "Message" as "Column Master Agreement Excel ID is mandatory and a value should be provided."
    #--------------------Mass Import LC--------------------------
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component-Errors" with Excel File "NFS-6804"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to Check the Report "Message" as "Column Contract Excel ID is mandatory and a value should be provided."

    Examples:
      | testCaseNumber |
      | NFS-6804       |
