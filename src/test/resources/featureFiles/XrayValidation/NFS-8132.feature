Feature: NFS-8132 Test Case
  NFS-8132: Verify that user is able to edit Activity Analysis & Periodic Postings Report Schedule Job

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that user is able to edit and delete Activity Analysis & Periodic Postings Report Schedule Job
  #Jira_ID:NFS-8132
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @PPSR @AAR
  Scenario Outline: NFS-8132 Test Case
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
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Periodic Posting Profile & Schedule Job----------------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Periodic Posting Status Reports"-->"Periodic Schedule Jobs"
    Then User create Periodic Posting Status Report "Scheduled Job" for "Periodic Posting Status Report"
      | Name     | Posting Type | Calendar Type    | Internal Status Filter | Internal Status | External Status Filter | External Status | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | NFS-8132 | All          | Regular Calendar | All                    | null            | All                    | null            | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Activation Group | ActivationGroup-1 | No     | Yes   | No     | No      | No     |    1 |   19 | null      | null  |
    Then Users edit the created scheduled job
    Then User "Disable" Scheduled Job for "Periodic Posting Status Report"
    Then User deletes Scheduled Job for "Periodic Posting Status Report"
    #--------------------Activity Analysis Reports Job (IAS)--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Schedule Jobs"
    Then User create Activity Analysis Report "Scheduled Job" for "Activity Analysis Report"
      | Name     | Report Type        | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type                | Partner | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | NFS-8138 | By Amount,By Count | Active                  | 360 Convention | 2023-01-01 | 2023-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Fixed Lease Contract | null    | null        | Activation Group | ActivationGroup-1 | Yes    | No    | No     | No      | No     |    1 | null | null      | null  |
    Then Users edit the created scheduled job
    Then User "Disable" Scheduled Job for "Activity Analysis Report"
    Then User deletes Scheduled Job for "Activity Analysis Report"

    Examples: 
      | testCaseNumber |
      | NFS-8132       |
