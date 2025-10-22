Feature: NFS-9467 Test Case
  NFS-9467 : Event Revert - Verify when an AG is reverted open drafts should be discarded.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Event Revert - Verify when an AG is reverted open drafts should be discarded.
  #Jira_ID:NFS-9467
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-9467 Test Case
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
    Then User exercise the Term and Condition number "1,2"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User enters the "Inception" Classification Values of Activation Group
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the "AG Event-1" Definition Page values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Revert the AG--------------------------
    Then User revert the AG with reversal reason as "Reversal in current period"
    #--------------------Revert the AG--------------------------
    Then User clicks on "Active" status "Activation Group" with name "Inception"
    Then User verify the changes in Activation Group after AG Revert
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-2" Consumer Price Index Values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-3" Consumer Price Index Values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #-----------Activation Group Contract Rate Change-------------------
    Then User tries to create AG Event at "AG Event-4" Level
    Then User enters the Accounting tab of Activation Group
    Then User changes the Contract Rate or IBR at "AG Event-4" level
    #--------------------Activation Group Casualty Event--------------------------
    Then User clicks on "Active" status "Activation Group" with name "Indexation-3"
    Then User tries to create AG Event at "AG Event-5" Level
    #--------------------Revert the AG--------------------------
    Then User clicks on "Active" status "Activation Group" with name "Indexation-3"
    Then User revert the AG with reversal reason as "Reversal in current period"
    #-------------------------Verification---------------------
    Then User verify that Activation Group "Indexation-2" exists with "Active" status and reference number
    Then User verify that Activation Group "Indexation-3" with "Reverted" status should "Exists"
    Then User verify that Activation Group "Indexation-2" with "Inactive" status should "Exists"
    Then User verify that Activation Group "Inception" exists with "Revision" status and reference number
    Then User verify that Activation Group "Inception" with "Inactive" status should "Exists"
    Then User verify that Activation Group "LeaseModification-3" with "Draft" status should "Not Exists"
    Then User verify that Activation Group "Casualty-4" with "Draft" status should "Not Exists"

    Examples: 
      | testCaseNumber |
      | NFS-9467       |
