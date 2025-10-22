Feature: SmokeTC012 Test Case
  NFS-10868 SmokeTC # 12: Validating workflows for Contract, Lease component and AG at event level

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC012 - Validating workflows for Contract, Lease component and AG at event level
  #Jira_ID:NFS-10868
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P3
  Scenario Outline: SmokeTC012 Test Case
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
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Contract Rate Change Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the Accounting tab of Activation Group
    Then User changes the Contract Rate or IBR at "AG Event-1" level
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-assessment-rework-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group Event" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-reject-btn" workflow transition
    #--------------------Lease Component Event Level--------------------------
    Then User click on the "Lease Component" with Name "SmokeTC012"
    Then User adds a New LC Event "Increase Rent"
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-rework-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component Event" for "lc-approve-btn" workflow transition
    #--------------------Contract Event Level--------------------------
    Then User click on the "Contract" with Name "SmokeTC012"
    Then User adds a New Contract Event "Add Partners"
    Then User sends the "Contract Event" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract Event" for "contract-rework-btn" workflow transition
    Then User sends the "Contract Event" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract Event" for "contract-approve-btn" workflow transition

    Examples: 
      | testCaseNumber |
      | SmokeTC012     |
