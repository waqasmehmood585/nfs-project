Feature: SmokeTC014 Test Case
  NFS-10870 SmokeTC # 14: All Scheduler jobs

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with specific credentials
    Then User opens the "nakisa-financial-suite" application url with specific user

  #TC_Title:SmokeTC014 - All Scheduler jobs
  #Jira_ID:NFS-10870
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee
  Scenario Outline: SmokeTC014 Test Case
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
    Then User exercise the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-1" id
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Delete Schedule jobs--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Schedule Job"
    And User delete Schedule Job for "Disclosure Reports"
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Schedule Jobs"
    And User delete Schedule Job for "Activity Analysis Reports"
    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Schedule Jobs"
    And User delete Schedule Job for "Periodic Posting Status Reports"
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Schedule Jobs"
    And User delete Schedule Job for "Operational Schedule Jobs"
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Scheduled Jobs"
    And User delete Schedule Job for "Indexation Scheduled Jobs"
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Consolidated Transaction Report"-->"Consolidated Scheduled Jobs"
    And User delete Schedule Job for "Consolidated Transaction Report"
    Then Open Hamburger menu & Click On ""-->"GL Balance Report"-->"GL Scheduled Jobs"
    And User delete Schedule Job for "GL Balance Report"
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
     #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Disclosure Report Schedule Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Schedule Job"
    Then User create Disclosure Report "Scheduled Job" for "Disclosure Report"
      | Name       | From Date  | To Date    | Principal Position | Calendar Type  | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | SmokeTC014 | 2023-01-01 | 2023-06-30 | Lessee             | 360 Convention | IFRS                | Finance        | 10 - CAD | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |
    #--------------------Activity Analysis Reports Schedule Job--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Schedule Jobs"
    Then User create Activity Analysis Report "Scheduled Job" for "Activity Analysis Report"
      | Name       | Report Type        | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type           | Partner | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | SmokeTC014 | By Amount,By Count | Active                  | 360 Convention | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Fixed Lease Contract | null    | null        | Activation Group | ActivationGroup-1 | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |
    #--------------------Periodic Posting Schedule Job----------------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Schedule Jobs"
    Then User create Periodic Posting Status Report "Scheduled Job" for "Periodic Posting Status Report"
      | Name       | Posting Type | Calendar Type  | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | SmokeTC014 | All          | 360 Convention | All                    | null            | All                    | null            | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Activation Group | ActivationGroup-1 | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |
    #--------------------Operational Postings Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-07-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
     #--------------------Operational Postings Scheduled Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Schedule Jobs"
    Then User tries to create Batch Posting Schedule Job
      | Name             | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type    | Posting & Document Date Type | Open Drafts | Entity Type      | Entity ID         | Schedule Option | Hour | Date | Week Days | Month | Months Name |
      | <testCaseNumber> | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2024-01-01 | Period End Date | Job Execution Date           | Yes         | Activation Group | ActivationGroup-1 | Hourly          | 1    | null | null      | null  | null        |
      #--------------------Consolidated Report Job IAS -------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Consolidated Transaction Report"-->"Consolidated Scheduled Jobs"
    Then User Create Consolidated Transaction Report "Scheduled Job" for "Consolidated Transaction Report"
      | Name             | Filter Profile/Object | Application                   | Accounting Standard | Calendar Type  | Date Range Type | Period Start | Period End | Ledger Type filter 1 | Transaction Type | Ledger Type filter 2 | Ledger Types | External Posting Status Filter | External Posting Status | Object Type | Object List | Business Area ID | Functional Area ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Object                | Nakisa Lease Accounting (NLA) | IFRS                | 360 Convention | Posting Date    | 2023-07-01   | 2023-12-31 | All                  | null             | All                  | null         | null                           | null                    | Contract ID | Contract-1  | null             | null               | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |
    #--------------------GL Balance Scheduled Reports Profile & Job--------------------------
    Then Open Hamburger menu & Click On ""-->"GL Balance Report"-->"GL Scheduled Jobs"
    Then User Create GL Balance Report "Scheduled Job" for "GL Balance Report"
      | Name             | Date Range Type | Calendar Type  | Period Start | Period End | From Year | From Posting Period | To Year | To Posting Year | Principal Position | Accounting Standard | Posting Category | Currency Type | Lease Type Filter | Lease Type | Classification Filter Type | Lease Classification | Internal Asset Class Filter Type | Internal Asset Classes | Vendor Filter Type | Vendor | Account Type Filter Type | Account Type | General Ledger Account Filter Type | General Ledger Account | Object Type      | Object List       | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | Posting Date    | 360 Convention | 2023-07-01   | 2023-12-31 | null      | null                | null    | null            | Lessee             | IFRS                | Internal Posting | 10 - CAD      | All               | null       | All                        | null                 | All                              | null                   | All                | null   | All                      | null         | All                                | null                   | Activation Group | ActivationGroup-1 | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |
   #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>" using sheet "inputs-2"
    Then User opens the "nakisa-financial-suite" application url
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
    Then User stores the "Contract-2" id
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    Then User stores the "LeaseComponent-2" id
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
    Then User stores the "ActivationGroup-2" id
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Operational Postings Job --------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-01-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Mass Indexation Schedule Job-----------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Scheduled Jobs"
    Then User tries to create Indexation Posting Schedule Job
      | Batch Size | Principal Position | Modification Date Type | Indexation Type (Lease) | Reference Date Type (Lease) | Indexation Date Type (Lease) | Indexation Type (Non-Lease) | Reference Date Type (Non-Lease) | Indexation Date Type (Non-Lease) | GAAP Indexation Treatment | Posting Date Type      | Open Drafts | Entity Type      | Entity ID         | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories  | Schedule Option | Hour | Date | Week Days | Month | Months Name |
      | 100        | Lessee             | Next Available Posting | CPI Global              | Next Available Posting      | Next Available Posting       | null                        | null                            | null                             | Enforce Non-Lease         | Next Available Posting | Yes         | Activation Group | ActivationGroup-2 | All                | null                 | List                | UF-CLP - UF-CLP | Hourly          | 1    | null | null      | null  | null        |

    Examples:
      | testCaseNumber |
      | SmokeTC014     |
