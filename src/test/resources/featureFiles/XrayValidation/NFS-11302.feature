Feature: NFS-11302 Test Case
  NFS-11302 - Verify Periodic Posting Status, Activity Analysis and Disclosure Reports Modifications

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Periodic Posting Status, Activity Analysis and Disclosure Reports Modifications
  #Jira_ID:NFS-11302
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @PPSR
  Scenario Outline: NFS-11302 Test Case
    #--------------------Reading Test Case Excel (Short Term Contract)------------------
    Given Reading test case inputs from excel file "NFS-11302-1"
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
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
#    Then User opens the "user-auxiliary" application url
#    #--------------------Batch Profile --------------------------
#    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
#    Then Users create Profile for "Batch"
#      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
#      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
#    #--------------------Operational Postings Job --------------------------
#    Then User opens the "nakisa-financial-suite" application url
#    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
#    Then User tries to create Batch Posting Job
#      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types   | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
#      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-06-30 | Schedule Period Dates | Yes         | Activation Group |
#    Then the "Batch" job should be completed with status "Done"
    # --------------------Master Agreement Search--------------------------
    Then User Search "Master Agreement" with ID
    #--------------------Reading Test Case Excel (Lease Low Value)--------------
    Given Reading test case inputs from excel file "NFS-11302-2"
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
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-2" id
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Operational Postings Job --------------------------
#    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
#    Then User tries to create Batch Posting Job
#      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types   | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
#      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-06-30 | Schedule Period Dates | Yes         | Activation Group |
#    Then the "Batch" job should be completed with status "Done"
    #--------------------Master Agreement Search--------------------------
    Then User Search "Master Agreement" with ID
    #--------------------Reading Test Case Excel (Lease Low Value)--------------
    Given Reading test case inputs from excel file "NFS-11302-3"
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    Then User stores the "Contract-3" id
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    Then User stores the "LeaseComponent-3" id
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-3" id
    #    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Operational Postings Job --------------------------
#    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
#    Then User tries to create Batch Posting Job
#      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types   | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
#      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-06-30 | Schedule Period Dates | Yes         | Activation Group |
#    Then the "Batch" job should be completed with status "Done"
    #--------------------Master Agreement Search--------------------------
    Then User Search "Master Agreement" with ID
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    Then User stores the "Contract-4" id
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    Then User stores the "LeaseComponent-4" id
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2,3,4"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-4" id
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Operational Postings Job --------------------------
#    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
#    Then User tries to create Batch Posting Job
#      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                       | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
#      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment,Charge | Open             | User Defined   | 2023-01-01 | User Defined | 2023-06-30 | Schedule Period Dates | Yes         | Activation Group |
#    Then the "Batch" job should be completed with status "Done"
#    #--------------------SAP Posting Bot Profile & Job--------------------------
#    Then User opens the "sap-posting-bot" application url
#    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
#    Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
#    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
#    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
#    #--------------------User Auxiliary Profile--------------------------
#    Then User opens the "user-auxiliary" application url
#    #--------------------Batch Profile --------------------------
#    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
#    Then Users create Profile for "Report"
#      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
#      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
#    #--------------------Periodic Posting Status Reports Job--------------------------
#    Then User opens the "nakisa-financial-suite" application url
#    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Jobs"
#    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
#      | Name             | Report Type | Activation Group Status | Calendar Type    | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification                                | Lease Type | Partner | Asset Class | Object List Type | Object List ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | By Amount   | Active                  | Regular Calendar | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance,Short term,Low value,Service Contract | null       | null    | null        | Activation Group | All            | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    Then User Download and Validate "IAS" standard "Activity Analysis By Amount" Report
#    #--------------------Activity Analysis Reports Job GAAP--------------------------
#    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
#      | Name             | Report Type | Activation Group Status | Calendar Type    | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification                                          | Lease Type | Partner | Asset Class | Object List Type | Object List ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | By Amount   | Active                  | Regular Calendar | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | GAAP                | Finance,Operating,Short term,Low value,Service Contract | null       | null    | null        | Activation Group | All            | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    Then User Download and Validate "GAAP" standard "Activity Analysis By Amount" Report
#    #--------------------Periodic Posting Status Reports Profile & Job--------------------------
#    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Jobs"
#    Then User create Periodic Posting Status Report "Job" for "Periodic Posting Status Report"
#      | Name             | Posting Type | Calendar Type    | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification                                | Object List Type | Object List ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | All          | Regular Calendar | All                    | null            | All                    | null            | 2023-01-01 | 2023-06-30 | null      | null                | null    | null              | Lessee             | IFRS                | Finance,Short term,Low value,Service Contract | Activation Group | All            | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    Then User Download and Validate "IAS" standard "Periodic Posting Status Report" Report
#    #--------------------------------For GAAP-Operating Standard Report-------------------------------
#    Then User create Periodic Posting Status Report "Job" for "Periodic Posting Status Report"
#      | Name             | Posting Type | Calendar Type    | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification                                          | Object List Type | Object List ID | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | All          | Regular Calendar | All                    | null            | All                    | null            | 2023-01-01 | 2023-06-30 | null      | null                | null    | null              | Lessee             | GAAP                | Finance,Operating,Short term,Low value,Service Contract | Activation Group | All            | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    Then User Download and Validate "GAAP" standard "Periodic Posting Status Report" Report
#    #--------------------Disclosure Reports Profile & Job--------------------------
#    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Jobs"
#    Then User create Disclosure Report "Job" for "Disclosure Report"
#      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification                                | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | 2023-01-01 | 2023-12-31 | Lessee             | Regular Calendar | IFRS                | Finance,Short term,Low value,Service Contract | 10 - CAD | Active                  | Contract         | All            | No                        | Yes              | Yes            | No                     | No                       | Yes                             | No                                | No                             | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    And User Download and Validate "IAS" standard "Cash Flow" Report
#    And User Download and Validate "IAS" standard "Expense" Report
#    And User Download and Validate "IAS" standard "Non Lease Charge Expense" Report
#    #--------------------Disclosure Reports Profile & Job--------------------------
#    Then User create Disclosure Report "Job" for "Disclosure Report"
#      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification                                          | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
#      | <testCaseNumber> | 2023-01-01 | 2023-12-31 | Lessee             | Regular Calendar | GAAP                | Finance,Operating,Short term,Low value,Service Contract | 10 - CAD | Active                  | Contract         | All            | No                        | Yes              | Yes            | No                     | No                       | Yes                             | No                                | No                             | null   | null  | null   | null    | null   | null | null | null      | null  |
#    Then the "Report" job should be completed with status "Done"
#    And User Download and Validate "GAAP" standard "Cash Flow" Report
#    And User Download and Validate "GAAP" standard "Expense" Report
#    And User Download and Validate "GAAP" standard "Non Lease Charge Expense" Report

    Examples: 
      | testCaseNumber |
      | NFS-11302      |
