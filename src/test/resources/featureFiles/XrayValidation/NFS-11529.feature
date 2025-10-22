Feature: NFS-11529 Test Case
  NFS-11529 - Verify Consolidated Transaction Report for Service Contract

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Consolidated Transaction Report for Service Contract
  #Jira_ID:NFS-11529
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @CTR
  Scenario Outline: NFS-11529 Test Case
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
    #--------------------Batch Posting Profile-------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2024-01-01 | User Defined | 2024-06-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
     #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | 1000    | All              | All         | All         | All | All           |
        #--------------------Consolidated Report Job IAS -------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Consolidated Transaction Report"-->"Consolidated Jobs"
    Then User Create Consolidated Transaction Report "Job" for "Consolidated Transaction Report"
      | Name             | Filter Profile/Object | Application                   | Accounting Standard | Calendar Type  | Date Range Type | Period Start | Period End | Ledger Type filter 1 | Transaction Type | Ledger Type filter 2 | Ledger Types | External Posting Status Filter | External Posting Status | Object Type | Object List | Business Area ID | Functional Area ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Object                | Nakisa Lease Accounting (NLA) | IFRS                | 360 Convention | Document Date   | 2024-01-01   | 2024-06-30 | All                  | null             | All                  | null         | null                           | null                    | Contract ID | Contract-1  | null             | null               | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then Download and Validate "IAS" standard "Consolidated Transaction Report" Report
    #--------------------Consolidated Transaction Reports Profile & Job GAAP--------------------------
    Then User Create Consolidated Transaction Report "Job" for "Consolidated Transaction Report"
      | Name             | Filter Profile/Object | Application                   | Accounting Standard | Calendar Type  | Date Range Type | Period Start | Period End | Ledger Type filter 1 | Transaction Type | Ledger Type filter 2 | Ledger Types | External Posting Status Filter | External Posting Status | Object Type | Object List | Business Area ID | Functional Area ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Object                | Nakisa Lease Accounting (NLA) | GAAP                | 360 Convention | Document Date   | 2024-01-01   | 2024-06-30 | All                  | null             | All                  | null         | null                           | null                    | Contract ID | Contract-1  | null             | null               | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then Download and Validate "GAAP" standard "Consolidated Transaction Report" Report

    Examples:
      | testCaseNumber |
      | NFS-11529      |
