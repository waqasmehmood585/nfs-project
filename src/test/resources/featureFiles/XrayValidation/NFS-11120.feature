Feature: NFS-11120 Test Case
  NFS-11120 : Revert AG - Scenario 1 - AG Activation with no Event & no Charges

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Revert AG - Scenario 1 -AG Activation with no Event & no Charges
  #Jira_ID:NFS-11120
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-11120 Test Case
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
    #--------------------Activation Group Split--------------------------
    Then User Split the Activation Group
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
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
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-04-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #    --------------------Revert the AG--------------------------
    Then User revert the AG with reversal reason as "Reversal in current period"
    Then User verify that "Revert" button is "Disabled" for "Reverted" Activation Group
    Then User verify that Activation Group "Inception" exists with "Active" status and reference number
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Posted" from "2022-01-01" to "2022-01-31"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Posted" from "2022-01-01" to "2022-01-31"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Posted" from "2022-01-01" to "2022-01-01"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Posted" from "2022-01-01" to "2022-01-01"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-02-01" to "2022-04-30"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "AllColumns" is "Reversed" from "2022-02-01" to "2022-04-30"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-02-01" to "2022-04-01"
    Then User clicks on "GAAP" schedule to validate "Internal" status of "Liability" is "Reversed" from "2022-02-01" to "2022-04-01"
    #--------------------Search With ID--------------------------
    Then User click on the "Split Activation Group" with Name "NFS-11120"
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Casualty Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Revert the AG--------------------------
    Then User verify that "Revert" button is "Disabled" for "Casualty" Activation Group
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      |       1000 | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-03-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------SAP Posting Bot Profile & Job--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
    Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Activation Group Lease End & CLose--------------------------
    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    Then User verify that "Revert" button is "Disabled" for "Lease End" Activation Group
    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
    Then User verify that "Revert" button is "Disabled" for "Closed" Activation Group

    Examples: 
      | testCaseNumber |
      | NFS-11120      |
