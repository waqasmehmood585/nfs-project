Feature: NFS-5000 Test Case
  NFS-5000: PGL: [Nakisa 2023.R4] NLA: Access Denied error message is shown when user click on Cancel workflow transition button

  User logins into the application with valid credentials in-order to perform the test case

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:NLA: Access Denied error message is shown when user click on Cancel workflow transition button
  #Jira_ID:NFS-5000
  #TC_Category:CustomerBug
  #TC_Customers:TEConnectivity
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerBug @PGL
  Scenario Outline: NFS-5000 Test Case
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
    And User clicks on Generate Schedules button and click on Cancel button


    Examples:
      | testCaseNumber |
      | NFS-5000      |
