Feature: SmokeTC051 Test Case
  NFS-7979 SmokeTC # 51: Perform Mass Import Job for LC (Non GVI)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC051 - Perform Mass Import Job for LC (Non GVI)
  #Jira_ID:NFS-7979
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  #Contract (Non GVI)
  #LC (Multiple Units & COB)
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC051 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import MLA--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC016_MLA1.0"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC016_CT1.2"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "SmokeTC016_LC1.2"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Lease Component" with ID
    Then User verify that "Lease Component" status is "Active"
     #--------------------Mass Import Activation Group--------------------------
    Then User click on the "Activation Group" with Name "TC16_LC_withCOB2"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    Then User enters the Unit List tab of Activation Group
    Then User enters the Charge List tab of Activation Group
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Activation Group" with Excel File "SmokeTC016_AG1.1"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport                        |
      | null       | Lessee             | Yes             | Active      | Definition,Accounting,Classifications |
     #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Activation Group" with ID
    Then User verify that "Activation Group" status is "Active"
#    Then User enters the Charge List tab of Activation Group
#    And user validated the Records Import on "Charge"

    Examples:
      | testCaseNumber |
      | SmokeTC016     |
