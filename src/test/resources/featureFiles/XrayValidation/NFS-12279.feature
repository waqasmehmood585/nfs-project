Feature: NFS-12279 Test Case
  NFS-12279: NLA: Default Field Value: Validating Complete flow of Default Fields and the Supported fields with the Conditions.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Validating Complete flow of Default Fields and the Supported fields with the Conditions.
  #Jira_ID:NFS-12279
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Xrays @Default-Value @Coverage
  Scenario Outline: NFS-12279 Test Case
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location    | Target Field  | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | Master Agreement | Lease Area    | Active               | Editable          | null       | null       | 0001           |
      | Contract         | Business Unit | Active               | Editable          | 0001       | null       | BU0002         |
      | Lease Component  | Asset Class   | Inactive             | Locked            | 1000       | null       | IAC0001        |

   # | Activation Group |  CPI Category   | Active                |     Locked        |     CPI Local |     1000       |UF-CLP      |
    Then User click on "Update" button for defining configurations with row number "1,2,3"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |
      | null          | null         | Inactive             | Locked            | null       | null       | BU0001         |
      | null          | null         | Active               | Locked            | null       | null       | 0002           |
    Then User click on "Delete" button for defining configurations with row number "1,3"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |



    Examples:
      | testCaseNumber |
      | NFS-12279      |