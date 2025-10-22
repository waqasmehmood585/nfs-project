Feature: TC-UF-CLP-07 Test Case
  NFS-12493 - Verify Schedules for Accounting Test Case UF/CLP-07

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Schedules for Accounting Test Case UF/CLP-07
  #Jira_ID:NFS-12493
  #TC_Category:CustomerUAT
  #TC_Customers:UF/CLP
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerUAT @UF/CLP
  Scenario Outline: TC-UF-CLP-07 Test Case
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
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
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
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-01-01" to "2018-01-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-01-01" to "2018-01-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-01-01" to "2018-01-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-01-01" to "2018-01-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-02-01" to "2018-02-28"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-02-01" to "2018-02-28"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-02-01" to "2018-02-28"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-02-01" to "2018-02-28"
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-UF-CLP-07"
    Then User adds a New LC Event "Increase in Rent"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
#    Then User index the Term and Condition number "1"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
     #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event3-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event3-LM" level
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-4" Level
    Then User enters the Terms and Conditions tab of Activation Group
#    Then User index the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event4-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event4-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event4-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event4-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-03-01" to "2018-03-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-03-01" to "2018-03-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-03-01" to "2018-03-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-03-01" to "2018-03-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-5" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event5-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event5-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event5-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event5-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-04-01" to "2018-04-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-04-01" to "2018-04-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-04-01" to "2018-04-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-04-01" to "2018-04-30"
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "TC-UF-CLP-07"
    Then User adds a New LC Event "Decrease in Rent"
    And User add new term and condition at "LC Event-2" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-6" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2,3"
#    Then User index the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event6-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event6-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event6-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event6-LM" level
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-7" Level
    Then User enters the Terms and Conditions tab of Activation Group
#    Then User index the Term and Condition number "3"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event7-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event7-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event7-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event7-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-05-01" to "2018-05-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-05-01" to "2018-05-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-05-01" to "2018-05-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-05-01" to "2018-05-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-8" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event8-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event8-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event8-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event8-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-06-01" to "2018-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-06-01" to "2018-06-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-06-01" to "2018-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-06-01" to "2018-06-30"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-9" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event9-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event9-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event9-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event9-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-07-01" to "2018-07-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-07-01" to "2018-07-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-07-01" to "2018-07-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-07-01" to "2018-07-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-10" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event10-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event10-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event10-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event10-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-08-01" to "2018-08-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-08-01" to "2018-08-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-08-01" to "2018-08-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-08-01" to "2018-08-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-11" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event11-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event11-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event11-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event11-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-09-01" to "2018-09-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-09-01" to "2018-09-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-09-01" to "2018-09-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-09-01" to "2018-09-30"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-12" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event12-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event12-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event12-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event12-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-10-01" to "2018-10-31"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-10-01" to "2018-10-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-10-01" to "2018-10-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-10-01" to "2018-10-31"
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-13" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
     #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event13-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event13-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event13-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event13-IN" level
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-11-01" to "2018-11-30"
    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2018-11-01" to "2018-11-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-11-01" to "2018-11-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2018-11-01" to "2018-11-30"
    #--------------------Lease End & Close WorkFlow State-------------------------
#    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
#    Then User sends the "Activation Group" for "ag-close-btn" workflow transition

    Examples:
      | testCaseNumber |
      | TC-UF-CLP-07   |