Feature: NFS-13925 Test Case
  NFS-13925: NLA: Batch Schedular for internal postings- Verify that Schedule Jobs Run for From Date
  (Period Start Date) with To Date (Job Execution Date and Period End Date)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with specific credentials
    Then User opens the "nakisa-financial-suite" application url with specific user

  #TC_Title:Verify that Schedule Jobs Run for From Date (Period Start Date) with To Date (Job Execution Date and Period End Date)
  #Jira_ID:NFS-13925
  #TC_Category:Regression
  #TC_Customers:None
   #TC_FixVersion:Nakisa 2024.R1
#  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-13925 Test Case
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
    #    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
   #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job --------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Schedule Jobs"
    Then User tries to create Batch Posting Schedule Job
      | Name             | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type    | From Date  | To Date Type       | Posting & Document Date Type | Open Drafts | Entity Type      | Schedule Option | Hour | Date | Week Days | Month | Months Name |
      | <testCaseNumber> | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | Period Start Date | 2024-01-01 | Job Execution Date | Job Execution Date           | Yes         | Activation Group | Hourly          | 1    | null | null      | null  | null        |



    Examples:
      | testCaseNumber |
      | NFS-13925     |