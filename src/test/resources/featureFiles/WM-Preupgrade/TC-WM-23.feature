Feature: TC-WM-23 Test Case
  NFS-12505: Validate TC-WM-23 Scenario-1 for Walmart Pre Upgrade Scenario

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Schedules for Customer Scenario TC-WM-23
  #Jira_ID:NFS-12505
  #TC_Category:CustomerUAT
  #TC_Customers:walmartPreUpgrade
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerUAT @walmartPreUpgrade
  Scenario Outline: TC-WM-23 Test Case
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
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User enters the "Inception" Classification Values of Activation Group
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
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2021-06-01" to "2022-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2021-06-01" to "2022-06-30"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2021-06-01" to "2022-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2021-06-01" to "2022-06-30"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1,2"
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
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-07-01" to "2022-08-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-07-01" to "2022-08-31"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-07-01" to "2022-08-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-07-01" to "2022-08-31"
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
#    Then User Click on Activation Group "Inception" Level
#    Then User tries to create AG Event at "AG Event-2" Level
#    Then User enters the Terms and Conditions tab of Activation Group
#    Then User exercise the Term and Condition number "3"
#    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
#    #--------------------Journal Document Validation--------------------------
#    Then User clicks on "IAS" standard to validate "LeaseModification" documents
#    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
#     #--------------------All Column Schedule Validation--------------------------
#    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-LM" level
#    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-LM" level
#    #--------------------Liability Schedule Validation--------------------------
#    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-LM" level
#    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-LM" level
    #--------------------Lease End & Close WorkFlow State-------------------------
    #Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    #Then User sends the "Activation Group" for "ag-close-btn" workflow transition

    Examples:
      | testCaseNumber |
      | TC-WM-23       |