Feature: NFS-2793 Test Case
  NFS-2793: Switch between Events, Delete Draft Revision at LC & AG and Apply Asset Impairment Events

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Switch between Events, Delete Draft Revision at LC & AG and Apply Asset Impairment Events
  #Jira_ID:NFS-2793
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
   @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-2793 Test Case
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
    #--------------------Lease Component Event Level--------------------------
     Then User click on the "Lease Component" with Name "NFS-2793"
     Then User adds a New LC Event "Event-1"
     Then User clicks on Terms & Conditions on Lease Component to check the editable term
     And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
     And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Lease Component Event Level--------------------------
     Then User click on the "Lease Component" with Name "NFS-2793"
     Then User adds a New LC Event "Event-2"
     Then User clicks on "Revision" status "Lease Component" with name "Inception"
     Then User clicks on "Active" status "Lease Component" with name "Event-1"
     Then User clicks on "Draft" status "Lease Component" with name "Event-2"
     Then User deletes the draft revision
    #--------------------Activation Group Level--------------------------
     Then User click on the "Activation Group" with Name "NFS-2793"
    #--------------------Activation Group Level--------------------------
     Then User enters the Terms and Conditions tab of Activation Group
     Then User exercise the Term and Condition number "1"
     Then User Completes the Activation Group Unit List
     And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
     And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
     And User sends the "Activation Group" for "ag-approve-btn" workflow transition
     And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
     And User sends the "Activation Group" for "ag-activate-btn" workflow transition
   #--------------------Activation Group Terms & Condition reassessment Event--------------------------
     Then User tries to create AG Event at "AG Event-1" Level
     Then User deletes the draft revision
    #--------------------Activation Group Asset Impairment - Loss Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Asset Impairment - Gain Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition


    Examples:
      | testCaseNumber |
      | NFS-2793       |