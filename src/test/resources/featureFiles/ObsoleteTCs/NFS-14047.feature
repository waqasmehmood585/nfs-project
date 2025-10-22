Feature: NFS-14047 Test Case
  NFS-14047: NLA: Batch Schedular for internal postings- Verify that if a profile
  is selected with specific inputs then Schedular should pick all the related AGs to that profile

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with specific credentials
    Then User opens the "nakisa-financial-suite" application url with specific user

  #TC_Title:NLA:Verify if  profile is selected then Schedular should pick all the related AGs
  #Jira_ID:NFS-14047
  #TC_Category:Regression
  #TC_Customers:None
   #TC_FixVersion:Nakisa 2024.R1
#  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-14047 Test Case
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
#    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Profiles"
#    Then User opens the "nakisa-financial-suite" application url with "6th" specific user
      | Name             | Lease Area | Business Unit | Company | Lease Department | Cost Center | WBS  | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | null       | null          | null    | null             | null        | null | null          | null            | null          |
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Schedule Jobs"
    Then User tries to create Batch Posting Schedule Job
    |Name              | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type       | Posting & Document Date Type    | Open Drafts  | Entity Type      |Schedule Option | Hour | Date | Week Days | Month | Months Name |
    |<testCaseNumber>  | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2024-01-01 | Period End Date    |Job Execution Date               |  Yes          | Activation Group |Hourly          |    1 | null | null      | null  | null        |



    Examples:
      | testCaseNumber |
      | NFS-14047     |