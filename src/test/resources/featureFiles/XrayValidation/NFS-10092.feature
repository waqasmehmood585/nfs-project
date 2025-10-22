Feature: NFS-10092 Test Case
  NFS-10092 : AG Split - Verify unit distribution tab is added in LC import.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:AG Split - Verify unit distribution tab is added in LC import
  #Jira_ID:NFS-10092
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Import
  Scenario Outline: NFS-10092 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then User download the Import Template file of "Lease Component"
      | ERP System              | Principal Position | SheetsToImport                                                                             |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Definition,Unit Distributions,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    Then User checks the File for Column Exist
      | Sheet Name              | Column Name                                                    |
      | LC - Unit Distributions | Lease Component Excel ID,Activation Group Name,Number Of Units |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | NFS-10092      |
