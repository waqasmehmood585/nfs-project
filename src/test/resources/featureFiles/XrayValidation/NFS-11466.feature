Feature: NFS-11466 Test Case
  NFS-11466: Golden: Default Field Values - Configs page: Verify the searching, Sorting
  and pagination of the configs

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify the searching, Sorting and pagination of the configs
  #Jira_ID:NFS-11466
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4
  @Regression @Xrays @Default-Value @Coverage
  Scenario Outline: NFS-11466 Test Case
     #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location    | Target Field    | Configuration Status | Field Editability | Condition1       | Condition2 | Default Values |
      | Master Agreement | Lease Area      | Active               | Locked            | null             | null       | 0001           |
      | Contract         | Company         | Active               | Editable          | BU0001           | null       | 1000           |
      | Lease Component  | Unit Of Measure | Inactive             | Locked            | FINQ8S-300 - SAP | null       | EH             |
    Then User search the record on Default Value Config Page
    | Target Field                      | Configuration Status|
    | Master Agreement - Business Unit  | Active              |
    Then User click on "Delete" button for defining configurations with row number "1,2,3"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |



    Examples:
      | testCaseNumber |
      | NFS-11466      |