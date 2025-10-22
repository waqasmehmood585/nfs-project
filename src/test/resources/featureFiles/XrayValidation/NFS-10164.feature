Feature: NFS-10164 Test Case
  NFS-10164 - Base rent (monthly) with mid -month payments, free rent, indexation on ROU Anniversary and supplements.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Base rent (monthly) with mid -month payments, free rent, indexation on ROU Anniversary and supplements.
  #Jira_ID:NFS-10164
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Indexation-Split
  Scenario Outline: NFS-10164 Test Case
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
    Then User exercise the Term and Condition number "2"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
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
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "NFS-10164"
    Then User adds a New LC Event "Incentive"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event-------------------------- Without Batch
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "3"
    Then User index the Term and Condition number "3"
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
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-2" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-IN" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "NFS-10164"
    Then User adds a New LC Event "Reduction"
    And User add new term and condition at "LC Event-2" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event-------------------------- Without Batch
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "4"
    Then User index the Term and Condition number "4"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event3-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event3-LM" level


    Examples:
      | testCaseNumber |
      | NFS-10164      |
