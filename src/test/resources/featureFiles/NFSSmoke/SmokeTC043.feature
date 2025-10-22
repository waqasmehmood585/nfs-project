Feature: SmokeTC043 Test Case
  NFS-8034 SmokeTC #43: NFS Mass Export (Master Agreement)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC043 - NFS Mass Export (Master Agreement)
  #Jira_ID:NFS-8034
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC043 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Given User is on "master-agreement-tab" Landing page
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Master Agreement" Export job for "NFS Export-1"
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                   |
      | MA - Definition       | Migrated,Excel ID,Year,Master Agreement Name,Lease Area Display ID,State      |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID                            |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |
