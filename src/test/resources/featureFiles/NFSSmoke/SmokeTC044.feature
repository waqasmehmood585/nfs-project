Feature: SmokeTC044 Test Case
  NFS-8033 SmokeTC #44: NFS Mass Export (Contract)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC044 - NFS Mass Export (Contact)
  #Jira_ID:NFS-8033
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC044 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Contact" Export job for "NFS Export-1"
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |