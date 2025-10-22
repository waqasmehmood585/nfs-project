Feature: SmokeTC037 Test Case
  NFS-2132 SmokeTC #37: NFS Export (MLA Export from Landing Page)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC037 - NFS Export (MLA Export from Landing Page)
  #Jira_ID:NFS-2132
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC037 Test Case
    Given User is on "master-agreement-tab" Landing page
    Then User Selects the "Master Agreement" records and Export it
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                   |
      | MA - Definition       | Migrated,Excel ID,Year,Master Agreement Name,Lease Area Display ID,State      |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID                            |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC037     |
