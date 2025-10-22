Feature: SmokeTC041 Test Case
  NFS-8103 SmokeTC #41: NFS Export (Unit Export from Landing Page)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC041 - NFS Export (Unit Export from Landing Page)
  #Jira_ID:NFS-8103
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC041 Test Case
#    Given User is on "unit-tab" Landing page
    Then User Selects the "Unit" records and Export it
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                          |
      | Unit                           | Name,State,Currency Display ID,Company Display ID,Internal Asset Class Display ID                    |
      | Unit - Cost Center Allocations | Unit Excel ID,Cost Center Display ID,Allocation Percentage,Profit Center Display ID,Main Cost Center |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC041     |
