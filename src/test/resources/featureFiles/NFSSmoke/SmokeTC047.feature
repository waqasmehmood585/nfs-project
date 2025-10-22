Feature: SmokeTC047 Test Case
  NFS-8013 SmokeTC # 47: Perform Mass Import Job for Master Agreement

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC047 - Perform Mass Import Job for Master Agreement
  #Jira_ID:NFS-8013
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  #MLA Import for only Mandatory Fields In define
  #Contract Import with Multiple partners in Active
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC047 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import MLA--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC015_MLA1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    Then User Search "Master Agreement" with ID
    Then User verify that "Master Agreement" status is "Define"
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC015_CT1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Contract" with ID
    Then User verify that "Contract" status is "Active"
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC015_CT1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                                  |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    Then User Search "Contract" with ID
    Then User verify that "Contract" status is "Define"

    Examples:
      | testCaseNumber |
      | SmokeTC015     |