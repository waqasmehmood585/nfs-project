Feature: NFS-13901 Test Case
  NFS-13901: Golden: Mass Workflow Transition: Verify that user should be able to perform
  mass workflow job for MLA

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that user should be able to perform mass workflow job for MLA
  #Jira_ID:NFS-13901
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  #@Regression @Xray @Core-Functionality
  Scenario Outline: NFS-13901 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
#    #--------------------Master Agreement Level--------------------------
#    Then User tries to create Master Agreement
#    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
#    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
#    #--------------------Mass Workflow Transition Job for MLA---------------------------------------
#    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Workflow Transition"-->"Workflow Profiles"
#    Then Users tries to create Profile for "Mass Workflow Transition"
#      | Name             | Lease Area | Business Unit | Company   | Lease Department | Cost Center | WBS  | Profit Center | Functional Area | Business Area |
#      | <testCaseNumber> | null       | null          | null      | null             | null        | null | null          | null            | null          |
#    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Workflow Jobs"
#    Then User tries to create Workflow Posting Job
#      | Batch Size | Default Workbook Input | Principal Position | Target Entity               | Initial State | Final State  | Allow Hitchhiking | Entity Type      |
#      | 100        | True                   | Lessee             | Master Agreement            | Active        | Discarded   | No                 | Master Agreement |
#    #--------------------Search With ID-----------------------------
#    Then User Search "Master Agreement" with ID
#    #--------------------Checking the MLA Status----------------------
#    Then User verify that "Master Agreement" status is "Discarded"
#    Then User opens the "nakisa-financial-suite" application url
    #--------------------Master Agreement Level--------------------------
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
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
      | Batch Size | Default Workbook Input | Principal Position | Target Entity    | Initial State | Action at Step 1 | Allow Hitchhiking | Entity Type      |
      | 100        | true                   | Lessee             | Master Agreement | Active        | Close            | No                | Master Agreement |
    Then the "Mass Workflow Batch" job should be completed with status "Transition Done"
      #--------------------Search With ID-----------------------------
    Then User Search "Master Agreement" with ID
    #--------------------Checking the MLA Status----------------------
    Then User verify that "Master Agreement" status is "Closed"



    Examples:
      | testCaseNumber |
      | NFS-13901      |
