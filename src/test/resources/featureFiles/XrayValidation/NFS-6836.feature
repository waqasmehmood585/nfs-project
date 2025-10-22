Feature: NFS-6836 Test Case
  NFS-6836: NLA: Approval and Rejection comments- Verify that user should be allowed to enter
  approval or rejection comments (Approver Comment at event rework at Event level Scenario)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Approver Comment at event rework at Event level Scenario
  #Jira_ID:NFS-6836
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
Scenario Outline: NFS-6836 Test Case
   #--------------------Reading Test Case Excel--------------------------(approver comment at event rework at event level)
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
   #--------------------Contract Event--------------------------
   Then User adds a New Contract Event "Event-1"
   Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
   Then User sends the "Contract" for "contract-rework-btn" workflow with a message "Contract has been reworked"
   Then User validate the "Contract" with a message "Contract has been reworked"
   Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
   Then User sends the "Contract" for "contract-approve-btn" workflow transition
   #--------------------Lease Component Level--------------------------
   And User tries to create Lease Component
   Then User enters data under Lease Component Definition page
   And User add new term and condition at "Inception" level
   And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
   And User sends the "Lease Component" for "lc-approve-btn" workflow transition
   Then User click on the "Lease Component" with Name "NFS-6836"
   #--------------------Lease Component Event Level--------------------------
   Then User adds a New LC Event "Event-1"
   And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
   And User sends the "Lease Component" for "lc-rework-btn" workflow with a message "Lease Component has been reworked"
   Then User validate the "Lease Component" with a message "Lease Component has been reworked"
   And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
   And User sends the "Lease Component" for "lc-approve-btn" workflow transition
   #--------------------Activation Group Level--------------------------
   Then User click on the "Activation Group" with Name "NFS-6836"
   Then User enters the Terms and Conditions tab of Activation Group
   Then User exercise the Term and Condition number "1"
   Then User Completes the Activation Group Unit List
   And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
   And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
   And User sends the "Activation Group" for "ag-approve-btn" workflow transition
   And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
   And User sends the "Activation Group" for "ag-activate-btn" workflow transition
   #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
   Then User tries to create AG Event at "AG Event-1" Level
   And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
   And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
   And User sends the "Activation Group" for "ag-assessment-rework-btn" workflow with a message "Activation Group has been reworked"
   Then User validate the "Activation Group" with a message "Activation Group has been reworked"
   And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
   And User sends the "Activation Group" for "ag-approve-btn" workflow transition
   And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
   And User sends the "Activation Group" for "ag-activate-btn" workflow transition

Examples:
| testCaseNumber   |
| NFS-6836         |
