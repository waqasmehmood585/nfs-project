Feature: NFS-3742 Test Case
  NFS-3742: Postings: Verify user should be able to do postings with multiple payment
  terms only on active AG and verify that Button color is changed according to
  posted/reversed/ on all postings for both IFRS and GAAP

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify user should be able to do postings with multiple payment terms of different Payment frequencies
  #Jira_ID:NFS-3742
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-3742 Test Case
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
    Then User exercise the Term and Condition number "1,2,3"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Inception" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
     #--------------------Schedule Posting Reversal--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    #-------------------- Liability Schedule Posting Reversal--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    #--------------------Schedule Posting Reverse--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-03-31"
    #-------------------- Liability Schedule Posting Reverse--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-03-31"
    #-------------------- History button task--------------------------
    Then User clicks on "IAS" standard to see the History of "Accrual" from "AllColumns" Schedules
    Then User clicks on "GAAP" standard to see the History of "Depreciation" from "AllColumns" Schedules
    Then User clicks on "IAS" standard to see the History of "Payment" from "Liability" Schedules
    Then User clicks on "GAAP" standard to see the History of "Payment" from "Liability" Schedules
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "NFS-3742"
    Then User adds a New LC Event "Add Termination Option"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User click on the "Activation Group" with Name "NFS-3742"
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "3"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2023-06-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2023-06-31"
    #-------------------- Liability Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-06-01" to "2023-06-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-06-01" to "2023-06-31"
    #--------------------Schedule Posting Reverse--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2023-06-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2023-06-31"
    #-------------------- Liability Schedule Posting Reverse--------------------------
    Then User clicks on "IAS" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-06-01" to "2023-06-31"
    Then User clicks on "GAAP" Standard to "Reverse" the "Payment" of "Liability" Schedules from "2023-06-01" to "2023-06-31"


    Examples:
      | testCaseNumber |
      | NFS-3742       |
