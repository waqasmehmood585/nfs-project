Feature: NFS-10822 Test Case
  NFS-10822: NLA: Coverage – ESS Security – Visit NFS with a User of a Specific Profile.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url with specific user "es user1"

  #TC_Title:ESS Security Coverage - Visit NFS with a User of a Specific Profile.
  #Jira_ID:NFS-10822
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xrays @Coverage
  Scenario Outline: NFS-10822 Test Case
    Given User is on "master-agreement-tab" Landing page
    Given User is on "contract-tab" Landing page
    Given User is on "lease-component-tab" Landing page
    Given User is on "activation-group-tab" Landing page
    Given User is on "unit-tab" Landing page


    Examples:
      | testCaseNumber |
      | NFS-10822      |