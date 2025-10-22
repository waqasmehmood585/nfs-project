Feature: NFS-11446 Test Case
  NFS-11446: Golden: NLA: Default Field Values - Configs design: Verify user should not be able to create conflicting
  configs of same level

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify user should not be able to create conflicting configs of same level
  #Jira_ID:NFS-11446
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4
  @Regression @Xrays @Default-Value @Coverage
  Scenario Outline: NFS-11446 Test Case
     #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location    | Target Field  | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | Master Agreement | Lease Area    | Active               | Locked            | null       | null       | 0001           |
      Then Verify if user created the same Config then error message should be shown
        | Form Location    | Target Field  | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
        | Master Agreement | Lease Area    | Active               | Locked            | null       | null       | 0001           |
    Then User click on "Delete" button for defining configurations with row number "1"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |

    Examples:
      | testCaseNumber |
      | NFS-11446      |