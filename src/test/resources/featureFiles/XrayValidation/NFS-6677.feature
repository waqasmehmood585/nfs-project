Feature: NFS-6677 Test Case
  NFS-6677 - Verify that users are able to post Payment, Accrual, Depreciation and Charges from Posting jobs in Batch management
  by selecting specific Contract Display IDs in filtering

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify user is able to post Payment,Accrual,Depreciation,Charges from Posting jobs in Batch management (CT ID)
  #Jira_ID:NFS-6677
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-6677 Test Case
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
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    #And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                       | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type |
      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment,Charge | Open             | User Defined   | 2022-01-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Contract    |
    Then the "Batch" job should be completed with status "Done"
      #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Journal Document Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Journals" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Journals" is "Posted"
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Charge-1" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Charge-1" is "Posted"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Posted"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Posted"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Posted"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Posted"
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                       | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type |
      |       1000 | Lessee             | Reversal         | Erp System's Default | Accrual,Depreciation,Payment,Charge | Open             | User Defined   | 2022-01-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Contract    |
    Then the "Batch" job should be completed with status "Done"
     #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Charge-1" is "Reversed"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Charge-1" is "Reversed"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Reversed"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Reversed"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Reversed"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Reversed"

    Examples: 
      | testCaseNumber |
      | NFS-6677       |
