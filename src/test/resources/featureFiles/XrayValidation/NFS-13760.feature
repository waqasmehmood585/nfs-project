Feature: NFS-13760 Test Case
  NFS-13760:Test Income Statement Report for an AG verify that report is correctly mapped.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Test Income Statement Report for an AG verify that report is correctly mapped.
  #Jira_ID:NFS-13760
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @ICStatement
  Scenario Outline: NFS-13760 Test Case
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
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Income Statement Report Download and Validate--------------------------
    Then Open Hamburger menu & Click On "Reporting Module"-->"Financial Reports"-->"Income Statement Report"
    Then User download and validate "Income Statement Report" for "IAS" Standard with "Finance" Classification
    Then Open Hamburger menu & Click On "Reporting Module"-->""-->"Income Statement Report"
    Then User download and validate "Income Statement Report" for "GAAP" Standard with "Operating" Classification

    Examples: 
      | testCaseNumber |
      | NFS-13760      |
