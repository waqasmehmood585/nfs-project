Feature: SmokeTC032 Test Case
  NFS-2705 SmokeTC #32: Perform Mass Work Flow Job with Profile (CPI-Global Scenario)

  Background:
  User needs to login into the application before performing the test case

    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "user-auxiliary" application url

  #TC_Title:SmokeTC032 - Perform Mass Work Flow Job with Profile (CPI-Global Scenario)
  #Jira_ID:NFS-2705
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P4
  Scenario Outline: SmokeTC032 Test Case
    #--------------------Reading Test Case Excel-----------------------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    #--------------------Master Agreement Level------------------------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then User tries to create Master Agreement
    #--------------------Mass Workflow Transition Job for MLA---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Workflow Transition"-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Action at Step 2 | Allow Hitchhiking | Entity Type      | Document and Posting Date Type |
      | 100        | true                   | Lessee             | Master Agreement | Define        | Send to Approval | Approve          | No                | Master Agreement | null                           |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
      #--------------------Search With ID-----------------------------
    Then User Search "Master Agreement" with ID
    #--------------------Checking the MLA Status----------------------
    Then User verify that "Master Agreement" status is "Active"
    #--------------------Contract Level--------------------------------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    #--------------------Mass Workflow Transition Job for Contract---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity | Initial State | Action at Step 1 | Action at Step 2 | Allow Hitchhiking | Entity Type | Document and Posting Date Type |
      | 100        | true                   | Lessee             | Contract      | Define        | Send to Approval | Approve          | No                | Contract    | null                           |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
      #--------------------Search With ID-----------------------------
    Then User Search "Contract" with ID
    #--------------------Checking the Contract Status----------------------
    Then User verify that "Contract" status is "Active"
    #--------------------Lease Component Level--------------------------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    #--------------------Mass Workflow Transition Job for Lease Component---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity   | Initial State | Action at Step 1 | Action at Step 2 | Allow Hitchhiking | Entity Type     | Document and Posting Date Type |
      | 100        | false                  | Lessee             | Lease Component | Define        | Send to Approval | Approve          | No                | Lease Component | null                           |
    Then the "Mass Workflow Batch" job should be completed with status "Pending User Input"
    Then User Upload the downloaded excel file of "Mass Workflow"
    #--------------------Search With ID-----------------------------
    Then User Search "Lease Component" with ID
    #--------------------Checking the Lease Component Status----------------------
    Then User verify that "Lease Component" status is "Active"
    #--------------------Move to AG--------------------------
    Then User click on the "Activation Group" with Name "SmokeTC032"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    #--------------------Mass Workflow Transition Job for Unit---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity | Initial State | Action at Step 1 | Action at Step 2 | Action at Step 3 | Allow Hitchhiking | Entity Type      | Document and Posting Date Type |
      | 100        | false                  | Lessee             | Unit          | Draft         | Send to Initial  | Received         | Activate         | No                | Activation Group | null                           |
    Then the "Mass Workflow Batch" job should be completed with status "Pending User Input"
    Then User Upload the downloaded excel file of "Mass Workflow"
    #--------------------Search With ID----------------------------------------------------
    Then User Search "Activation Group" with ID
    #--------------------Checking the Unit Status----------------------------------------------------
    Then User verify that "Unit" status is "Active"
    #--------------------Mass Workflow Transition Job for AG---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1   | Action at Step 2 | Action at Step 3       | Action at Step 4 | Allow Hitchhiking | Entity Type      | Document and Posting Date Type |
      | 100        | false                  | Lessee             | Activation Group | Define        | Send to Assessment | Approve          | Confirm Classification | Activate         | No                | Activation Group | ROU Start/End Date             |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
    Then User Upload the downloaded excel file of "Mass Workflow"
    #--------------------Search With ID-----------------------------
    Then User Search "Activation Group" with ID
    #--------------------Checking the AG Status----------------------
    Then User verify that "Activation Group" status is "Active"
    #--------------------Operational Postings Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------SAP Posting Bot Profile & Job--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
    Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Mass Workflow Transition Job for AG---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Workflow Transition"-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Action at Step 2 | Allow Hitchhiking | Entity Type      | Document and Posting Date Type |
      | 100        | false                  | Lessee             | Activation Group | Active        | Lease End        | Close            | No                | Activation Group | ROU Start/End Date             |
    Then the "Mass Workflow Batch" job should be completed with status "Pending User Input"
    Then User Upload the downloaded excel file of "Mass Workflow"
    #--------------------Search With ID-----------------------------
    Then User Search "Activation Group" with ID
    #--------------------Checking the Unit Status----------------------
    Then User verify that "Activation Group" status is "Closed"

    Examples:
      | testCaseNumber |
      | SmokeTC032     |
