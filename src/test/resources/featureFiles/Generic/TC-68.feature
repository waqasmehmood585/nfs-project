Feature: TC-68 Test Case
  User logins into the application with valid credentials in-order to perform the test case

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Schedules for Accounting Test Case TC-68
  #Jira_ID:NFS-10176
  #TC_Category:Engine/Core/Accounting
  #TC_Customers:walmartNUAT
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Engine/Core/Accounting @walmartNUAT
  Scenario Outline: TC-68 Test Case
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
    Then User exercise the Term and Condition number "1,2"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
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
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-08-01" to "2022-12-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-08-01" to "2022-12-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-08-01" to "2022-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-08-01" to "2022-12-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Inception" level
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-1" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-IN" level
    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event1-IN" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-68"
    Then User adds a New LC Event "Increase In Rent"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "3"
    Then User index the Term and Condition number "3"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-LM" level
    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-04-01" to "2023-06-30"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-04-01" to "2023-06-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-04-01" to "2023-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-04-01" to "2023-06-30"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event2-LM" level
    #--------------------Activation Group Casualty Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Terms and Conditions tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event3-CA" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event3-CA" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event3-CA" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event3-CA" level
    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-07-01" to "2023-07-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-07-01" to "2023-07-31"
    #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-07-01" to "2023-07-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-07-01" to "2023-07-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event3-CA" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event3-CA" level
    #--------------------Activation Group Lease End & CLose--------------------------
#    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
#    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
#    #--------------------All Column Schedule Validation After Close--------------------------
#    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "AG-Close" level
#    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "AG-Close" level

    Examples: 
      | testCaseNumber |
      | TC-68          |
