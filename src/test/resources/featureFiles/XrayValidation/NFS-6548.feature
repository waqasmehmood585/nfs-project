Feature: NFS-6548 Test Case
  NFS-6548: NRE : Introduce SAP Test Connection button - Verify that the switch is present
  on the ERP Systems page to test each SAP server mentioned on the page

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "sap-sync-bot" application url

  #TC_Title:Verify that the switch is present on the ERP Systems page to test each SAP server
  #Jira_ID:NFS-6548
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4
  @Regression @Xrays @SapSyncBot
  Scenario Outline: NFS-6548 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------ERP System Connection test--------------------------
    Then user test the connection of ERP System
    #Then Open Hamburger menu & Click On "Sync Profiles"-->""-->""
    Then Verify user is able to create sap sync profile with settings and name "<testCaseNumber>"
    |Override Default Language  |Select All  | Override Partner Table Name  |
    |English                    |   Yes      |  Partner                     |
    Then User delete the Sap sync profile
    Then Verify user is able to create sap sync profile with settings and name "<testCaseNumber>"
    |Override Default Language  |Select All  | Override Partner Table Name  |
    |English                    |   Yes      |  Partner                     |
   # Then Open Hamburger menu & Click On "Sync Jobs"-->""-->""
    Then User creates a SAP Sync Job with details
    | Global Sync Profile  |
    | NFS-6548             |



    Examples:
      | testCaseNumber |
      | NFS-6548       |
