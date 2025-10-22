Feature: NFS-9579 Test Case
  NFS-9579 - NLA & SPB: Copy and Cancellation of Batch & Report Jobs/Tasks

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:NLA & SPB - Copy and Cancellation of Batch & Report Jobs/Tasks
  #Jira_ID:NFS-9579
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-9579 Test Case
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
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-1" id
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Create Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
     #--------------------Operational Postings--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2025-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then User cancel "All Task" of "Operational Postings"
    #--------------------Copy Job---------------------------------------
    Then User copy the Job of "Operational Postings"
     #--------------------Cancel Job---------------------------------------
    Then User cancel "Job" of "Operational Postings"
     #--------------------Mass Indexation--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Jobs"
    Then User tries to create Indexation Posting Job
      | Batch Size | Principal Position | Modification Date Type | Modification Date | Indexation Type (Lease) | Index Level Type (Lease) | New Index Level (Lease) | Reference Date Type (Lease) | Reference Date (Lease) | GAAP Indexation Treatment | Indexation Date Type (Lease) | Indexation Date (Lease) | Indexation Type (Non-Lease) | Index Level Type (Non-Lease) | New Index Level (Non-Lease) | Reference Date Type (Non-Lease) | Reference Date (Non-Lease) | Indexation Date Type (Non-Lease) | Indexation Date (Non-Lease) | Posting Date Type | Posting Date | Document Date | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories | Open Drafts | Entity Type      |
      | 100        | Lessee             | User Defined           | 2022-06-01        | CPI Local               | Rate                     | 110                     | null                        | null                   | Enforce Non-Lease         | Modification Date            | null                    | null                        | null                         | null                        | null                            | null                       | null                             | null                        | Modification Date | null         | null          | All                | null                 | All                 | null           | Yes         | Activation Group |
    Then User cancel "All Task" of "Mass Indexation"
    #--------------------Copy Job-------------------------------------
    Then User copy the Job of "Mass Indexation"
     #--------------------Cancel Job----------------------------------
    Then User cancel "Job" of "Mass Indexation"
    #--------------------Mass Workflow Transition---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Workflow Transition"-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Allow Hitchhiking | Entity Type         | Document and Posting Date Type |
      | 100        | false                  | Lessee             | Master Agreement | Active        | Callback         | No                | Master Agreement    | null                           |
    Then User cancel "All Task" of "Mass Workflow Transition"
     #--------------------Copy Job-------------------------------------
    Then User copy the Job of "Mass Workflow Transition"
     #--------------------Cancel Job----------------------------------
    Then User cancel "Job" of "Mass Workflow Transition"
      #--------------------Mass Modification--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Modification"-->"Modification Jobs"
    Then User tries to create Modification Posting Job
      | Batch Size | Transaction Type             | Activation Group Event Name | LC Event Name | Modification Date Type | Activation Dates Types | Posting Date | Document Date | Modification Date | Contract rate | Apply Indexation | Exercise | Open Drafts | List Filter Type | Effective Date Type | Effective Date |
      | 100        | Lease Component Modification | null                        | Add TO Term   | null                   | null                   | null         | null          | null              | null          | null             | null     | Yes         | Lease Component  | null                | null           |
    Then User cancel "All Task" of "Mass Modification"
     #--------------------Copy Job-------------------------------------
    Then User copy the Job of "Mass Modification"
     #--------------------Cancel Job----------------------------------
    Then User cancel "Job" of "Mass Modification"
     #--------------------Inter Company Transfer--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Inter Company Transfer"-->"Inter Company Transfer Jobs"
    Then User tries to create Inter Company Transfer Job
      | Name             | Transfer Date | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | List Filter Type     |
      | InterCompany Job | 2023-07-01    | List              | FINQ8S-300  | All        | All           | All     | Contract             |
    Then User cancel "All Task" of "Inter Company Transfer"
    #--------------------Copy Job-------------------------------------
    Then User copy the Job of "Inter Company Transfer"
     #--------------------Cancel Job----------------------------------
    Then User cancel "Job" of "Inter Company Transfer"
    #--------------------------Reports-----------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Activity Analysis Report--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Jobs"
    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
      | Name             | Report Type | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type           | Partner | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | By Count    | Active                  | 360 Convention | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Fixed Lease Contract | null    | null        | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel "Report Job" of "Activity Analysis Report"
    Then User copy the Job of "Activity Analysis Report"
    Then User cancel "Report Job" of "Activity Analysis Report"
         #--------------------Periodic Posting Status Report--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Jobs"
    Then User create Periodic Posting Status Report "Job" for "Periodic Posting Status Report"
      | Name             | Posting Type | Calendar Type    | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | All          | Regular Calendar | All                    | null            | All                    | null            | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel "Report Job" of "Periodic Posting Status Report"
    Then User copy the Job of "Periodic Posting Status Report"
    Then User cancel "Report Job" of "Periodic Posting Status Report"
    #--------------------Disclosure Report--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Jobs"
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | 2023-05-01 | 2023-08-31 | Lessee             | Regular Calendar | IFRS                | Finance        | RUB      | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel "All Task" of "Disclosure Report"
    Then User copy the Job of "Disclosure Report"
    Then User cancel "All Task" of "Disclosure Report"
      #--------------------GL Balance Report-------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"GL Balance Report"-->"GL Jobs"
    Then User Create GL Balance Report "Job" for "GL Balance Report"
      | Name             | Date Range Type | Calendar Type  | Period Start | Period End | From Year | From Posting Period | To Year | To Posting Year | Principal Position | Accounting Standard | Posting Category | Currency Type | Lease Type Filter | Lease Type | Classification Filter Type | Lease Classification | Internal Asset Class Filter Type | Internal Asset Classes | Vendor Filter Type | Vendor | Account Type Filter Type | Account Type | General Ledger Account Filter Type | General Ledger Account | Object Type      | Object List        | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Document Date   | 360 Convention | 2023-01-01   | 2023-12-31 | null      | null                | null    | null            | Lessee             | IFRS                | Internal Posting | 10 - CAD      | All               | null       | All                        | null                 | All                              | null                   | All                | null   | All                      | null         | All                                | null                   | Activation Group | ActivationGroup-1  | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel "Report Job" of "GL Balance Report"
    Then User copy the Job of "GL Balance Report"
    Then User cancel "Report Job" of "GL Balance Report"
    #--------------------Consolidated Report -------------------------
    Then Open Hamburger menu & Click On ""-->"Consolidated Transaction Report"-->"Consolidated Jobs"
    Then User Create Consolidated Transaction Report "Job" for "Consolidated Transaction Report"
      | Name             | Filter Profile/Object | Application                   | Accounting Standard | Calendar Type  | Date Range Type | Period Start | Period End | Ledger Type filter 1 | Transaction Type | Ledger Type filter 2 | Ledger Types | External Posting Status Filter | External Posting Status | Object Type | Object List | Business Area ID | Functional Area ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Profile               | Nakisa Lease Accounting (NLA) | IFRS                | 360 Convention | Document Date   | 2023-01-01   | 2023-06-30 | All                  | null             | All                  | null         | null                           | null                    | null        | null        | null             | null               | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User cancel "Report Job" of "Consolidated Transaction Report"
    Then User copy the Job of "Consolidated Transaction Report"
    Then User cancel "Report Job" of "Consolidated Transaction Report"

    Examples:
      | testCaseNumber |
      | NFS-9579       |
