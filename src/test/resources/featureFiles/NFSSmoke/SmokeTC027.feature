Feature: SmokeTC027 Test Case
  NFS-2096 SmokeTC #27: NFS Mass Export (Unit)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC027 - NFS Mass Export (Unit)
  #Jira_ID:NFS-2096
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC027 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
#    Given User is on "unit-tab" Landing page
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Unit" Export job for "NFS Export-1"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                 |
      | Unit                           | Activation Group Excel ID,Name,State,Currency Display ID,Company Display ID,Internal Asset Class Display ID |
      | Unit - Cost Center Allocations | Unit Excel ID,Cost Center Display ID,Allocation Percentage,Profit Center Display ID,Main Cost Center        |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |
