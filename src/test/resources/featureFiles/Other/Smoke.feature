Feature: Smoke Test Case
  User logins into the application with valid credentials in-order to perform the test case

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  @OLDSmoke
  Scenario Outline: Smoke Test Case
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
    Then User exercise the Term and Condition number "1,2,3,4"
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
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "Smoke Test-LC"
    Then User adds a New LC Event "Decrease Lease Length"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User exercise the Term and Condition number "3,4,6,7,8,9"
    And User makes the Rou End Date Change To "2028-03-15"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "Smoke Test-LC"
    Then User adds a New LC Event "Increase Lease Length"
    And User add new term and condition at "LC Event-2" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User exercise the Term and Condition number "11,13,15,16,17,18"
    And User makes the Rou End Date Change To "2032-03-15"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-LM" level
    #--------------------Liability Schedule Validation--------------------------
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-LM" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-LM" level
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "Smoke Test-LC"
    Then User adds a New LC Event "Increase Payment"
    And User add new term and condition at "LC Event-3" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User changes the record to be shown per page to "50"
    And User Exercises the terms and conditions "28"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event3-LM" level
    #--------------------Liability Schedule Validation--------------------------
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event3-LM" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event3-LM" level
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-4" Level
    Then User enters the Terms and Conditions tab of Activation Group Event
    Then User changes the record to be shown per page to "50"
    And User Disables the terms and conditions "38"
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event4-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event4-LM" level
    #--------------------Liability Schedule Validation--------------------------
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event4-LM" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event4-LM" level
    #--------------------Activation Group Casualty Event--------------------------
    Then User tries to create AG Event at "AG Event-5" Level
    And User sends the "Activation Group Event" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event5-CA" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event5-CA" level
    #--------------------Liability Schedule Validation--------------------------
    #    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event5-CA" level
    #    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event5-CA" level
    #--------------------Activation Group Lease End & CLose--------------------------
    #    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
    #    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
    
    Examples: 
      | testCaseNumber |
      | Smoke          |
