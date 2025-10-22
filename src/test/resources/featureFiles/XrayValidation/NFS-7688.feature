Feature: NFS-7688 Test Case
  NFS-7688 - Verify Fiscal year and period selection for report Job.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Fiscal year and period selection for report Job.
  #Jira_ID:NFS-7688
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @PPSR
  Scenario Outline: NFS-7688 Test Case
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
    Then User stores the "LeaseComponent-1" id
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-1" id
    #    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job --------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Reversal         | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-07-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
   #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant  | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Fiscal Variant | PF - FINQ8S-300 | All        | All           | FINQ8S-300 - CA - 1005 | All              | All         | All         | All | All           |
    #--------------------Periodic Posting Status Reports Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Jobs"
    Then User create Periodic Posting Status Report "Job" for "Periodic Posting Status Report"
      | Name             | Posting Type | Calendar Type  | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date | To Date | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | All          | Fiscal Variant | All                    | null            | All                    | null            | null      | null    | 2023      | 1 (2023-01-01)      | 2023    | 11 (2023-11-30)   | Lessee             | IFRS                | Finance        | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then User Download and Validate "IAS" standard "Periodic Posting Status Report" Report
    #--------------------------------For GAAP-Operating Standard Report-------------------------------
    Then User create Periodic Posting Status Report "Job" for "Periodic Posting Status Report"
      | Name             | Posting Type | Calendar Type  | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date | To Date | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | All          | Fiscal Variant | All                    | null            | All                    | null            | null      | null    | 2023      | 1 (2023-01-01)      | 2023    | 11 (2023-11-30)   | Lessee             | GAAP                | Operating      | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then User Download and Validate "GAAP" standard "Periodic Posting Status Report" Report

    Examples:
      | testCaseNumber |
      | NFS-7688       |
