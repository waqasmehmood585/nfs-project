Feature: NFS-9803 Test Case
  NFS-9803: Golden: NLA: Non-Lease Service CT - Verify the events applied on non-lease service contracts.
  should be able define inputs to refine the report generation

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify the events applied on non-lease service contractsto refine the report generation
  #Jira_ID:NFS-9803
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-9803 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Master Agreement Level--------------------------
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then enters data under Contract Definition page
      | External CT Ref | Internal CT Ref | Validity From | Validity To | Lease Type                      | Contract Category | Amendment Date | Currency              | Form of Lease | Description | Joint Venture | Lease Department                  | Lease Group                | Signing Person | Signature Place | Date Of Signature | Group 1               | Group 2      | Group 3 | Group 4 |
      |            9803 |            9803 | 2024-01-01    | 2024-12-31  | 06 - Non-Lease Service Contract | 01 - Budgeted     | 2024-12-31     | CAD - Canadian Dollar | Full          | NFS-9803    | Gross Lease   | DE001 - Global Leasing Department | LG001 - Global Lease Group | Nakisa         | Canada          | 2024-12-31        | 1 - Standard Contract | 1 - Standard |    9803 |    9803 |
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Contract Event Level Add New Partners--------------------------
    Then User adds a New Contract Event "Add Partners"
    Then User selects partner role and partner under Contract Partner page at "Contract Event-1" Level
    #Then User verify that for GVI Contract User should not be able to Add "Lessor" Partners
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
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "NFS-9803"
    Then User adds a New LC Event "Adding Term"
    And User add new term and condition at "LC Event-1" level
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Indexation Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User enters the Accounting tab of Activation Group
    Then User enters the "AG Event-1" Consumer Price Index Values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Terms & Conditions Reassessment Event-------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    Then User index the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition

    Examples: 
      | testCaseNumber |
      | NFS-9803       |
