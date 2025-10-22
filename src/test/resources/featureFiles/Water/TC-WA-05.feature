Feature: TC-WA-05 Test Case
  NFS-12469 - Verify Schedules for Accounting Test Case WA-05

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Schedules for Accounting Test Case WA-05
  #Jira_ID:NFS-12469
  #TC_Category:CustomerUAT
  #TC_Customers:waters
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerUAT @waters
  Scenario Outline: TC-WA-05  Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Master Agreement Level----------------------------------------------
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level-----------------------------------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Lease Component Level--------------------------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Inception" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-01-01" to "2023-05-27"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-01-01" to "2023-05-27"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-01-01" to "2023-05-27"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-01-01" to "2023-05-27"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Inception" level
    #--------------------Lease Component Event Level----------------------------
    Then User click on the "Lease Component" with Name "TC-WA-05"
    Then User adds a New LC Event "Add Termination Option Term"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Mass Modification Profile & Jobs------------------
#    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Modification"-->"Modification Profiles"
#    Then Users tries to create Profile for "Mass Modification"
#      #if Filter Type is List then last 1 field are needed (Document Type)
#      | Name         | Lease Area | Business Unit | ERP  | Company | Lease Department | Cost Center | WBS  | Profit Center | Functional Area | Business Area | Document Type    | Display ID   |
#      | Profile-Name | null       | null          | null | null    | null             | null        | null | null          | null            | null          | Activation Group | AG-000000129 |
#    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Modification Jobs"
#    Then User tries to create Modification Posting Job
#      | Batch Size | Transaction Type              | Activation Group Event Name | LC Event Name | Modification Date Type | Activation Dates Types | Posting Date | Document Date | Modification Date | Contract rate | Apply Indexation   | Exercise          | Open Drafts | List Filter Type | Effective Date Type | Effective Date |
#      |       1000 | Activation Group Modification | LeaseModification-1         | null          | User Defined           | User Defined           | 2023-05-28   | 2023-05-28    | 2023-05-28        |           7.5 | Keep Current State | Apply on All T&Cs | Yes         | Activation Group | User Defined        | 2022-01-01     |
#    Then User Upload the downloaded excel file of "Mass Modification"
#    #--------------------Search With ID--------------------------
#    Then User Search "Activation Group" with ID
#    --------------------Activation Group Terms & Conditions Reassessment Event------------------Without Mass Modification
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-05-28" to "2023-09-30"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-05-28" to "2023-09-30"
#    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-05-28" to "2023-09-30"
#    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-05-28" to "2023-09-30"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event1-LM" level
    #--------------------Activation Group Lease End & CLose--------------------------
#    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
#    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
#    #--------------------All Column Schedule Validation After Close--------------------------
#    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "AG-Close" level
#    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "AG-Close" level

    Examples: 
      | testCaseNumber |
      | TC-WA-05       |
