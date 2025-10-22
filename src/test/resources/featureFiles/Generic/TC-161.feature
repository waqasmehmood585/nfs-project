Feature: TC-161 Test Case

  User logins into the application with valid credentials in-order to perform the test case

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Golden: NLA: Events Post ROU End Date - Validate Accounting TC-161
  #Jira_ID:NFS-15244
  #TC_Category:Engine/Core/Accounting
  #TC_Customers:walmartNUAT
  #TC_FixVersion:Nakisa 2024.R3
  @Engine/Core/Accounting @walmartNUAT
  Scenario Outline: TC-161 Test Case
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
    Then User enters the "Inception" Definition Page values of Activation Group
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
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    # --------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2021-01-01 | User Defined | 2021-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
     #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
     #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Inception" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Inception" level
     #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-161"
    Then User adds a New LC Event "Increase Lease Length"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
     #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User exercise the Term and Condition number "1"
    And User makes the Rou End Date Change To "2023-12-31"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-04-30 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event1-LM" level
     #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User enters the "AG Event-2" Definition Page values of Activation Group
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    Then User enters the "AG Event-2" Classification Values of Activation Group
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-LM" level
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event2-LM" level



    Examples:
      | testCaseNumber |
      | TC-161         |
