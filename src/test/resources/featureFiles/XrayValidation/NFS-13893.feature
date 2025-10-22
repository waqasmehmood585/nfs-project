Feature: NFS-13893 Test Case
  NFS-13893: SPB: General Ledger- Verify that Ledger Transaction popup should show the details
  button and new dialogue should have 3 sections

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "sap-posting-bot" application url

  #TC_Title:Verify Ledger Transaction popup should show the details button and new dialogue should have 3 sections
  #Jira_ID:NFS-13893
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-13893 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Opening Ledger Transactions--------------------------
    #Then Open Hamburger menu & Click On "Ledger Transactions"-->""-->""
    Then User clicks on Actions button to validate the dialogue sections


    Examples:
      | testCaseNumber |
      | NFS-13893      |
