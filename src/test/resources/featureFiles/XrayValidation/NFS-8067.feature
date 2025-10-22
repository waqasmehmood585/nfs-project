Feature: NFS-8067 Test Case
  NFS-8067: Verify downloaded report is showing correct data when Asset Impairment Gain/Loss event is applied

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify downloaded report is showing correct data when Asset Impairment Gain/Loss event is applied
  #Jira_ID:NFS-8067
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @GL
  Scenario Outline: NFS-8067 Test Case
#    #--------------------Reading Test Case Excel--------------------------
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
    Then User stores the "ActivationGroup-1" id
    #    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
     #--------------------Activation Group Asset Impairment - Loss Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
     #--------------------Activation Group Asset Impairment - Gain Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
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
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-03-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
        #--------------------GL Balance Reports IAS Profile & Job-------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"GL Balance Report"-->"GL Jobs"
    Then User Create GL Balance Report "Job" for "GL Balance Report"
      | Name     | Date Range Type | Calendar Type  | Period Start | Period End | From Year | From Posting Period | To Year | To Posting Year | Principal Position | Accounting Standard | Posting Category | Currency Type | Lease Type Filter | Lease Type | Classification Filter Type | Lease Classification | Internal Asset Class Filter Type | Internal Asset Classes | Vendor Filter Type | Vendor | Account Type Filter Type | Account Type | General Ledger Account Filter Type | General Ledger Account | Object Type      | Object List       | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | NFS-0022 | Document Date   | 360 Convention | 2023-01-01   | 2023-03-31 | null      | null                | null    | null            | Lessee             | IFRS                | Internal Posting | 10 - CAD      | All               | null       | All                        | null                 | All                              | null                   | All                | null   | All                      | null         | All                                | null                   | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then Download and Validate "IAS" standard "GL Balance Report" Report
    #--------------------GL Balance Reports GAAP Profile--------------------------
    Then User Create GL Balance Report "Job" for "GL Balance Report"
      | Name     | Date Range Type | Calendar Type  | Period Start | Period End | From Year | From Posting Period | To Year | To Posting Year | Principal Position | Accounting Standard | Posting Category | Currency Type | Lease Type Filter | Lease Type | Classification Filter Type | Lease Classification | Internal Asset Class Filter Type | Internal Asset Classes | Vendor Filter Type | Vendor | Account Type Filter Type | Account Type | General Ledger Account Filter Type | General Ledger Account | Object Type      | Object List       | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | NFS-0023 | Document Date   | 360 Convention | 2023-01-01   | 2023-03-31 | null      | null                | null    | null            | Lessee             | GAAP                | Internal Posting | 10 - CAD      | All               | null       | All                        | null                 | All                              | null                   | All                | null   | All                      | null         | All                                | null                   | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then Download and Validate "GAAP" standard "GL Balance Report" Report

    Examples:
      | testCaseNumber |
      | NFS-8067       |
