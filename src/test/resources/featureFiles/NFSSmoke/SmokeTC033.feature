Feature: SmokeTC033 Test Case
  NFS-8014 SmokeTC #33: NFS Mass Export (Charge)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC033 - NFS Mass Export (Charge)
  #Jira_ID:NFS-8014
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC033 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Charge" Export job for "NFS Export-1"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                           |
      | Charge                         | Unit Excel ID,Expense Category Display ID,Name,Amount,Due Date        |
      | CH - LSE Vendor Payment Splits | Charge Excel ID,Partner Display ID,Partner Role Display ID,Percentage |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |
