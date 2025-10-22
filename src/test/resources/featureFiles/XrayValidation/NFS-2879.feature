Feature: NFS-2879 Test Case
  Verify Report for Disclosure Test Case TC-DR-01

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Report for Disclosure Test Case TC-DR-01
  #Jira_ID:NFS-2879
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @DR
  Scenario Outline: NFS-2879 Test Case
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
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Inception" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-10-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-DR-01"
    Then User adds a New LC Event "New Term"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-DR-01"
    Then User adds a New LC Event "New Term"
    And User add new term and condition at "LC Event-2" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2,3"
    Then User enters the Accounting tab of Activation Group
    Then User changes the Contract Rate or IBR at "AG Event-2" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-LM" level
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event2-LM" level
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Disclosure Reports Job (IAS)--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Jobs"
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name     | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | TC-DR-01 | 2022-06-01 | 2022-12-31 | Lessee             | Regular Calendar | IFRS                | Finance        | 10 - CAD | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    And User Download and Validate "IAS" standard "Asset Roll Forward" Report
    And User Download and Validate "IAS" standard "Cash Flow" Report
    And User Download and Validate "IAS" standard "Expense" Report
    And User Download and Validate "IAS" standard "Lease Liability" Report
    And User Download and Validate "IAS" standard "Maturity Analysis" Report
    #And User Download and Validate "IAS" standard "Non Lease Charge Expense" Report
    And User Download and Validate "IAS" standard "Weighted Avg Discount Rate" Report
    And User Download and Validate "IAS" standard "Weighted Avg Lease Term" Report
    #--------------------Disclosure Reports Profile & Job--------------------------
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name     | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | TC-DR-01 | 2022-06-01 | 2022-12-31 | Lessee             | Regular Calendar | GAAP                | Operating      | 10 - CAD | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    And User Download and Validate "GAAP" standard "Asset Roll Forward" Report
    And User Download and Validate "GAAP" standard "Cash Flow" Report
    And User Download and Validate "GAAP" standard "Expense" Report
    And User Download and Validate "GAAP" standard "Lease Liability" Report
    And User Download and Validate "GAAP" standard "Maturity Analysis" Report
    #And User Download and Validate "GAAP" standard "Non Lease Charge Expense" Report
    And User Download and Validate "GAAP" standard "Weighted Avg Discount Rate" Report
    And User Download and Validate "GAAP" standard "Weighted Avg Lease Term" Report

    Examples: 
      | testCaseNumber |
      | NFS-2879       |
