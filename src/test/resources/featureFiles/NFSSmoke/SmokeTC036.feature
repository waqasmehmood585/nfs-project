Feature: SmokeTC036 Test Case
  NFS-11119 SmokeTC # 36: Verify user can revert AG at event and inception level.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC036 - Verify user can revert AG at event and inception level.
  #Jira_ID:NFS-11119
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P5
  Scenario Outline: SmokeTC036 Test Case
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
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    Then User clicks on "IAS" standard to Post the Charge "1" with validation as "false"
    Then User clicks on "GAAP" standard to Post the Charge "1" with validation as "false"
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-06-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Revert the AG--------------------------
    Then User revert the AG with reversal reason as "Reversal in current period"
    Then User verify the AG Revert Report
    #--------------------Journal Document Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Journals" is "Reversed"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Journals" is "Reversed"
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Charge-1" is "Reversed"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Charge-1" is "Reversed"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-01-01" to "2022-06-30"
    Then User clicks on "IAS" standard to validate Post button is disabled in "AllColumns"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-01-01" to "2022-06-30"
    Then User clicks on "GAAP" standard to validate Post button is disabled in "AllColumns"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-01-01" to "2022-06-30"
    Then User clicks on "IAS" standard to validate Post button is disabled in "Liability"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-01-01" to "2022-06-30"
    Then User clicks on "GAAP" standard to validate Post button is disabled in "Liability"
    #-------------------------Activation Group Level-----------------------
    Then User clicks on "Active" status "Activation Group" with name "Inception"
    Then User verify that "Activation Group" status is "Define"
    #--------------------Lease Component Buttons Status--------------------------
    Then User should not be able to callback Lease Component "SmokeTC036" when AG is Reverted
    #-------------------- Activation Group----------------------------
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Contract Rate Change Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Accounting tab of Activation Group
    Then User changes the Contract Rate or IBR at "AG Event-1" level
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-07-01 | User Defined | 2022-07-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Journal Document Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Journals" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Journals" is "Posted"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Posted" from "2022-07-01" to "2022-07-31"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Posted" from "2022-07-01" to "2022-07-31"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Posted" from "2022-07-01" to "2022-07-31"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Posted" from "2022-07-01" to "2022-07-31"
    #--------------------Lease Component Event Level------------------------------
    Then User click on the "Lease Component" with Name "SmokeTC036"
    Then User adds a New LC Event "Increase Lease Length"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User exercise the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    Then User clicks on Charges to add charge at "AG Event-2" level
    Then User clicks on "IAS" standard to Post the Charge "1,2" with validation as "false"
    Then User clicks on "GAAP" standard to Post the Charge "1,2" with validation as "false"
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-08-01 | User Defined | 2022-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #    --------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Revert the AG--------------------------
    Then User revert the AG with reversal reason as "Reversal in current period"
    Then User verify the AG Revert Report
    #--------------------Journal Document Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Journals" is "Reversed"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Journals" is "Reversed"
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Charge-1" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Charge-1" is "Posted"
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal" status of "Charge-2" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal" status of "Charge-2" is "Posted"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-08-01" to "2022-12-31"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-08-01" to "2022-12-31"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-08-01" to "2022-12-31"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-08-01" to "2022-12-31"
    #-------------------------Activation Group Level-----------------------
    Then User clicks on "Active" status "Activation Group" with name "LeaseModification-1"
    Then User verify that "Activation Group" status is "Active"

    Examples:
      | testCaseNumber |
      | SmokeTC036     |
