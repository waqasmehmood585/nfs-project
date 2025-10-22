Feature: NFS-12277 Test Case
  NFS-12277: Reclassification: system classification should be finance with confirm classification as Finance or Operating at AG Event
  when Lease term over useful life equal or exceed the Classification Percentage Term threshold defined at FOS

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Classification as Finance or Operating at AG Event when Lease term over useful life equal or exceed the Classification Percentage Term
  #Jira_ID:NFS-12277
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-12277 Test Case
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
    #--------------------Activation Group Level Scenario-1--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User enters the "Inception" Definition Page values of Activation Group
    Then User enters the Classification tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User validate the "Inception" Classifications Tab Values
    Then user validate confirm Classification Value "Operating,Finance"
    #--------------------Activation Group Level Scenario-2--------------------------
    Then User enters the "Scenario-2" Definition Page values of Activation Group
    And User makes the Rou End Date Change To "2036-12-31"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User validate the "Scenario-2" Classifications Tab Values
    Then user validate confirm Classification Value "Operating,Finance"
    And User makes the Rou End Date Change To "2023-12-31"
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Activation Group Level Scenario-3--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    Then User enters the "AG Event-1" Definition Page values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User validate the "Scenario-3" Classifications Tab Values
    Then user validate confirm Classification Value "Operating,Finance"
    #--------------------Activation Group Level Scenario-4--------------------------
    Then User enters the "Scenario-3" Definition Page values of Activation Group
    And User makes the Rou End Date Change To "2036-12-31"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User validate the "Scenario-4" Classifications Tab Values
    Then user validate confirm Classification Value "Operating,Finance"

    Examples:
      | testCaseNumber |
      | NFS-12277      |
