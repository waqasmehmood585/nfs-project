Feature: NFS-12144 Test Case
  NFS-12144: Validations for system classification and confirmed classification in cases of PO/Transfer of ownership/Specialized Asset At Inception.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Validations for system/confirmed classification in cases of PO/Transfer of ownership/Specialized Asset (Inception)
  #Jira_ID:NFS-12144
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-12144 Test Case
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
    Then User enters the carry over balance tab of lease component
    Then User enters data under the carry over balance page
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the "Inception" Definition Page values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    #--------------------Classification Page Validation--------------------------
    Then User validate the "Inception" Classifications Tab Values
    Then user validate confirm Classification Value "Finance"
    #--------------------Scenario 2 Validation--------------------------
    Then User enters the "Scenario-2" Definition Page values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    #--------------------Classification Page Validation--------------------------
    Then User validate the "Scenario-2" Classifications Tab Values
    Then user validate confirm Classification Value "Finance"
     #--------------------Scenario 3 Validation--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    Then User enters the "Scenario-3" Definition Page values of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    #--------------------Classification Page Validation--------------------------
    Then User validate the "Scenario-3" Classifications Tab Values
    Then user validate confirm Classification Value "Operating,Finance"

    Examples: 
      | testCaseNumber |
      | NFS-12144      |
