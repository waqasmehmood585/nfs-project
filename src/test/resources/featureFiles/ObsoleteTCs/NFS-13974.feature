Feature: NFS-13974 Test Case
  NFS-13974: Golden: Mass Workflow Transition Unit : Verify user can Transit Unit
  status by using Mass Workflow

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify user can Transit Unit status by using Mass Workflow
  #Jira_ID:NFS-13974
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  #@Regression @Xray @Core-Functionality
  Scenario Outline: NFS-13974 Test Case
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
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | All               | null        | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Mass Workflow Transition Job for MLA---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Workflow Transition"-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Action at Step 2  | Allow Hitchhiking | Entity Type      |
      | 100        | true                   | Lessee             | Unit             | Draft         | Send to Initial  |  Received        | Send to Approval  No                | Activation Group |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
       #--------------------Search With ID-----------------------------
    Then User Search "Activation Group" with ID
    #--------------------Checking the Unit Status----------------------
    Then User verify that "Unit" status is "Received"
     #--------------------Mass Workflow Transition Job for AG---------------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
    Then User tries to create Workflow Posting Job
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Allow Hitchhiking | Entity Type      |
      | 100        | true                   | Lessee             | Unit             | Received      | Activate         | No                | Activation Group |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
       #--------------------Search With ID-----------------------------
    Then User Search "Activation Group" with ID
    #--------------------Checking the Unit Status----------------------
    Then User verify that "Unit" status is "Active"

    Examples:
      | testCaseNumber |
      | NFS-13974      |
