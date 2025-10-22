Feature: SmokeTC030 Test Case
  NFS-16825 SmokeTC #30: Mass Inter company Transfer - Running Mass Inter company Transfer Jobs

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC030 - Mass Inter company Transfer - Running Mass Inter company Transfer Jobs
  #Jira_ID:NFS-16825
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:N2025.R3
  @Smoke @lessee
  Scenario Outline: SmokeTC030 Test Case
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
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2023-01-01 | User Defined | 2023-06-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
      #--------------------Inter Company Transfer Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Inter Company Transfer"-->"Inter Company Transfer Jobs"
    Then User tries to create Inter Company Transfer Job
      | Name              | Transfer Date   | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | List Filter Type |
      | InterCompany Job  | 2023-07-01      | List              | FINQ8S-300  | All        | All           | All     | Contract         |
    Then the "Inter Company Transfer Batch" job should be completed with status "Pending User Input"
    Then User Update the downloaded excel file of "Inter Company Transfer"
    Then User Upload the downloaded excel file of "Inter Company Transfer"
     #--------------------SAP Posting Bot (External Posting)--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
    Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
     #--------------------Contract Validation--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then User Search "Contract" with ID
    Then User stores the "InterCompanyTransfer Contract" id
    Then User Search "InterCompanyTransfer Contract" with ID
    Then User validate the fields of "Contract" with the data
    | Company Code |
    | 2000         |
    #--------------------Activation Group Validation--------------------------
    Then User Search "Activation Group" with ID
    Then User clicks on "Active" status "Activation Group" with name "INTERCOMPANY_TRANSFER"
    Then User verify that "InterCompanyTransfer Activation Group" status is "Active"
    Then User verify that "Lease End Button" is "Enable"
    Then User verify that "Event Button" is "Disable"
     #------------Storing and Retrieving InterCompanyTransfer Activation Group ID----------
    Then User stores the "InterCompanyTransfer Activation Group" id
    Then User Search "InterCompanyTransfer Activation Group" with ID
     #--------------------Validation--------------------------
    Then User verify that "InterCompanyTransfer Activation Group" status is "Active"
    Then User verify that "Event Button" is "Enabled"
    #--------------------Revert--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Inter Company Transfer"-->"Inter Company Transfer Jobs"
    Then User view the "Inter Company Transfer" Job
    Then User revert the "Inter Company Transfer" Job
    #--------------------Revert Validation--------------------------
    Then User Search "Activation Group" with ID
    Then User clicks on "Inactive" status "Activation Group" with name "Inception"
    Then User clicks on "Reverted" status "Activation Group" with name "INTERCOMPANY_TRANSFER"
    Then User clicks on "Active" status "Activation Group" with name "Inception"
    Then User verify that "Activation Group" status is "Active"
    Then User verify that "Lease End Button" is "Disable"
    Then User verify that "Event Button" is "Enabled"

    Examples:
      | testCaseNumber |
      | SmokeTC030     |
