Feature: TC-LR-04 Test Case
  NFS-3072 - Verify Lessor Work Flow, Posting and Schedules

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Lessor Work Flow, Posting and Schedules
  #Jira_ID:NFS-3072
  #TC_Category:Engine/Core/Accounting
  #TC_Customers:walmartNUAT
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
#  @Accounting @lessorAccounting @Engine/Core/Accounting
  Scenario Outline: TC-LR-04 Test Case
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
    Then User exercise the Term and Condition number "1,2,4,5,6"
    Then User Completes the Activation Group Unit List
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User enters the "Inception" Classification Values of Activation Group
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
      #--------------------Journal Document Validation-------------- (For IDC Term, Lessor has Journals)---
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
     #--------------------Operating Lease Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "OperatingLease" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "OperatingLease" schedule view at "Inception" level
    #--------------------Payment Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Payment" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Payment" schedule view at "Inception" level
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Inception" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2022-01-01" to "2023-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual" of "OperatingLease" Schedules from "2022-01-01" to "2023-12-31"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Payment" Schedules from "2022-01-01" to "2023-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Payment" Schedules from "2022-01-01" to "2023-12-31"
      #--------------------Lease End & Close WorkFlow State-------------------------
    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
    #--------------------All Column Schedule Validation After Close--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "AG-Close" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "AG-Close" level

    Examples:
      | testCaseNumber |
      | TC-LR-04       |