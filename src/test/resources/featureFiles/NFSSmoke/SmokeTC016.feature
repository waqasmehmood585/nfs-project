Feature: SmokeTC016 Test Case
  NFS-10877 SmokeTC # 16: Perform Mass Import Job for LC and Charge (Non-GVI)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC016 - Perform Mass Import Job for LC and Charge (Non-GVI)
  #Jira_ID:NFS-10877
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  #LC Import from T&C TAB (Non GVI)
  #Charge Import
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC016 Import-Scenario:1 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading base test case inputs "<testCaseNumber>"
    #--------------------Mass Import MLA--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Master Agreement" with Excel File "SmokeTC016_MLA1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                       |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Partners,Partners Contact |
    And user open report of "MA - Definition" to get Record ID
    #--------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import Contract--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Contract" with Excel File "SmokeTC016_CT1.3"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                              |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | Yes             | Active      | Definition,Lease Determination,Accounting,Cost Center Allocations,Partners,Partner Contacts |
    And user open report of "CT - LSE Definition" to get Record ID
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    #--------------------Mass Import LC--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Lease Component" with Excel File "SmokeTC016_LC1.3"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                            |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Definition,Unit Distribution,Carry-Over Balances,Terms & Conditions,Vendor Payment Splits |
    And user open report of "LC - LSE Definition" to get Record ID
    And user open report of "LC - LSE Terms & Conditions" to get Record ID
    Then User Search "Lease Component" with ID
    Then User verify that "Lease Component" status is "Define"
    Then User enters the Terms and Conditions tab of Lease Component
    #--------------------Mass Import LC T&C--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "LC T&C" with Excel File "SmokeTC016_LCT&C1.1"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                             |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Unit Distribution,Terms & Conditions,Vendor Payment Splits |
    Then User Search "Lease Component" with ID
    Then User enters the Terms and Conditions tab of Lease Component
    And user validated the Records Import on "LC T&C"
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Mass Import Unit--------------------------
    Then User enters the Unit List tab of Activation Group
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Unit" with Excel File "SmokeTC016_UN"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport               |
      | null       | Lessee             | Yes             | Active      | Unit,Cost Center Allocations |
    #--------------------Mass Import Charge--------------------------
    Then User Search "Activation Group" with ID
    Then User enters the Charge List tab of Activation Group
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    # SheetsToImport Will have Sheet names Comma(,) separated
    Then user performed Mass Import for "Charge" with Excel File "SmokeTC016_CH1.1"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport               |
      | null       | null               | null            | null        | Charge,Vendor Payment Splits |
    Then User Search "Activation Group" with ID
    Then User enters the Charge List tab of Activation Group
    And user validated the Records Import on "Charge"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    #--------------------Mass Import Activation Group--------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then user performed Mass Import for "Activation Group" with Excel File "SmokeTC016_AG1.0"
      | ERP System | Principal Position | Auto Transition | Final State | SheetsToImport                        |
      | null       | Lessee             | Yes             | Active      | Definition,Accounting,Classifications |
    #-------------------Checking the Status of Mass Workflow Job-----------------------------------
    And verify that Mass Workflow Job is Completed
    Then User Search "Activation Group" with ID
    Then User verify that "Activation Group" status is "Active"


    Examples:
      | testCaseNumber |
      | SmokeTC016     |
