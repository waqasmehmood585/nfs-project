Feature: NFS-5653 Test Case
  NFS-5653: PGL: [Pfizer-SaaS-Migration] Rounding curr adj issue during Casualty

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Rounding curr adj issue during Casualty
  #Jira_ID:NFS-5653
  #TC_Category:CustomerBug
  #TC_Customers:Pfizer
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerBug @PGL
  Scenario Outline: NFS-5653 Test Case
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
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User enters the "Inception" Classification Values of Activation Group
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Casualty Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
     #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 001        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-09-30 | Schedule Period Dates | Yes         | Activation Group |
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
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-CA" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-CA" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-CA" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-CA" level
    #--------------------Lease End & Close WorkFlow State-------------------------
    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    Then User sends the "Activation Group" for "ag-close-btn" workflow transition

    Examples:
      | testCaseNumber |
      | NFS-5653       |
