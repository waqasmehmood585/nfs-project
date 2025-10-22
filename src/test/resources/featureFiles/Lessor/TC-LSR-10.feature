Feature: TC-LSR-10 Test Case
  NFS-15671 - Lessor - Validate Accounting TC-LSR-10

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Lessor - Validate Accounting TC-LSR-10
  #Jira_ID:NFS-15671
  #TC_Category:Engine/Core/Accounting
  #TC_Customers:walmartNUAT
  #TC_FixVersion:N2025.R1
  @Accounting @lessorAccounting @Engine/Core/Accounting
  Scenario Outline: TC-LSR-10 Test Case
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
    Then User exercise the Term and Condition number "1,2,3"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User index the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
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
      | <testCaseNumber> | Lessor             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessor             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-09-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Journal Document Validation-------------- (For IDC Term, Lessor has Journals)---
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
    #--------------------Operating Lease Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "OperatingLease" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "OperatingLease" schedule view at "Inception" level
    #--------------------Payment Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Payment" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Payment" schedule view at "Inception" level
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Payment" Schedules from "2023-01-01" to "2023-09-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Payment" Schedules from "2023-01-01" to "2023-09-30"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-1" Consumer Price Index Values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessor             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-10-01 | User Defined | 2024-06-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Operating Lease Schedule Validationn--------------------------
    And User clicks on "IAS" standard to validate "OperatingLease" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "OperatingLease" schedule view at "Event1-IN" level
    #--------------------Payment Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Payment" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "Payment" schedule view at "Event1-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2023-10-01" to "2024-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2023-10-01" to "2024-06-30"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Payment" Schedules from "2023-10-01" to "2024-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Payment" Schedules from "2023-10-01" to "2024-06-30"
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-LSR-10"
    Then User adds a New LC Event "Increase Lease Length"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User exercise the Term and Condition number "2,3,4"
    Then User index the Term and Condition number "2,3,4"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessor             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2024-07-01 | User Defined | 2024-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Operating Lease Schedule Validationn--------------------------
    And User clicks on "IAS" standard to validate "OperatingLease" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "OperatingLease" schedule view at "Event2-LM" level
    #--------------------Payment Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Payment" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "Payment" schedule view at "Event2-LM" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2024-07-01" to "2024-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2024-07-01" to "2024-12-31"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Payment" Schedules from "2024-07-01" to "2024-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Payment" Schedules from "2024-07-01" to "2024-12-31"
    #--------------------Lease End & Close WorkFlow State-------------------------
    #Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    #Then User sends the "Activation Group" for "ag-close-btn" workflow transition
    #--------------------All Column Schedule Validation After Close--------------------------
    #And User clicks on "IAS" standard to validate "AllColumns" schedule view at "AG-Close" level
    #And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "AG-Close" level
    
    Examples: 
      | testCaseNumber |
      | TC-LSR-10      |
