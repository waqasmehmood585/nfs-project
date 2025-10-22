Feature: NFS-5266 Test Case
  NFS-5266: Verify that users are able to create or cancel jobs under Consolidated Transaction Jobs

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that users are able to create or cancel jobs under Consolidated Transaction Jobs
  #Jira_ID:NFS-5266
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @CTR
  Scenario Outline: NFS-5266 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Master Agreement Level--------------------------
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    Then User stores the "Contract-1" id
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    #    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company   | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Consolidated Report Job IAS -------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Consolidated Transaction Report"-->"Consolidated Jobs"
    Then User Create Consolidated Transaction Report "Cancel Job" for "Consolidated Transaction Report"
      | Name             | Accounting Standard | Calendar Type  | Date Range Type | Period Start | Period End | Ledger Type filter 1 | Transaction Type | Ledger Type filter 2 | Ledger Types | External Posting Status Filter | External Posting Status | Contract ID | Cost Center ID | Profit Center ID | Business Area ID | Functional Area ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | IFRS                | 360 Convention | Document Date   | 2023-01-01   | 2023-12-31 | All                  | null             | All                  | null         | null                           | null                    | Contract-1  | null           | null             | null             | null               | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel job for Created Report

    Examples:
      | testCaseNumber |
      | NFS-5266       |
