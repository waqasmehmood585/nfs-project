Feature: SmokeTC049 Test Case
  NFS-7982 SmokeTC # 49: Perform Mass Import Job for LC (Multiple Terms With GVI)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC049 - Perform Mass Import Job for LC (Multiple Terms With GVI)
  #Jira_ID:NFS-7982
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  #Contract (Multiple Partners & GVI)
  # LC (Multiple Terms With GVI)
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC049 Test Case
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
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC016_CT1.0"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "SmokeTC016_LC1.0"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to get Record ID
    Then User Search "Lease Component" with ID
    Then User verify that "Lease Component" status is "Define"
    Then User enters the Terms and Conditions tab of Lease Component
    #--------------------Mass Import LC T&C--------------------------
    Then User Import "SmokeTC016_LCT&C1.0" from Terms and Conditions tab of Lease Component
    And user validated the Records Import on "LC T&C"
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    Then User enters the Unit List tab of Activation Group
    Then User enters the Charge List tab of Activation Group
    #--------------------Mass Import Charge--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Charge" with Excel File "SmokeTC016_CH1.0"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport               |
      | null       | null               | null            | null        | Charge,Vendor Payment Splits |
    Then User Search "Activation Group" with ID
    Then User enters the Charge List tab of Activation Group
    And user validated the Records Import on "Charge"

    Examples:
      | testCaseNumber |
      | SmokeTC016     |