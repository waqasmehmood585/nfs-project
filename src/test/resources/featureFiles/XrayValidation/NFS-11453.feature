Feature: NFS-11453 Test Case
  NFS-11453: Golden: NLA: Default Field Values - Configs design: Verify if Target fields,
  Conditional fields are present and it is satisfying the Condition then Default Value is set

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify if Target fields, Conditional fields are present and it is satisfying the Condition then Default Value is set
  #Jira_ID:NFS-11453
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Xrays @Default-Value @Coverage
  Scenario Outline: NFS-11453 Test Case
     #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location    | Target Field  | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | Master Agreement | Lease Area    | Active               | Locked            | null       | null       | 0001           |
      | Master Agreement | Business Unit | Active               | Editable          | 0001       | null       | BU0001         |
      | Master Agreement | Company       | Active               | Locked            | BU0001     | null       | 1000           |
      | Master Agreement | Department    | Active               | Editable          | 1000       | null       | DE001          |
      | Master Agreement | Lease Group   | Active               | Editable          | DE001      | null       | LG001          |
    Then User validate the Default Value Configs for "Master Agreement"
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Delete" button for defining configurations with row number "1,2,3,4,5"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location | Target Field     | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      #| Contract         | Principal Position | Active               | Locked            | Lessee     | null       | Lessee         |
      | Contract      | Business Unit    | Active               | Editable          | 0001       | null       | BU0001         |
      | Contract      | Company          | Active               | Locked            | BU0001     | null       | 1000           |
      | Contract      | Lease Department | Active               | Editable          | 1000       | null       | DE001          |
      | Contract      | Lease Group      | Active               | Editable          | DE001      | null       | LG001          |
      | Contract      | Currency         | Active               | Locked            | 1000       | null       | CAD            |
    Then User Search "Master Agreement" with ID
    Then User create a Contract with Default Field Value
    Then User answers all the questions
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User validate the Default Value Configs for "Contract"
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Delete" button for defining configurations with row number "1,2,3,4,5"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |
    Then User click on "Create" button for defining configurations with row number "1"
      | Form Location   | Target Field    | Configuration Status | Field Editability | Condition1       | Condition2 | Default Values |
      | Lease Component | Unit Of Measure | Active               | Locked            | FINQ8S-300 - SAP | null       | EH             |
      | Lease Component | Asset Class     | Active               | Editable          | 1000             | null       | IAC0002        |
    Then User Search "Contract" with ID
    And User tries to create Lease Component
    Then User enters data under LC Definition page for Default Value
    Then User validate the Default Value Configs for "Lease Component"
    And User add new term and condition at "Inception" level
    Then Open Hamburger menu & Click On "Admin"-->"Default Value Configs"-->""
    Then User click on "Delete" button for defining configurations with row number "1,2"
      | Form Location | Target Field | Configuration Status | Field Editability | Condition1 | Condition2 | Default Values |
      | null          | null         | Active               | Editable          | null       | null       | IAC0002        |



    Examples:
      | testCaseNumber |
      | NFS-11453      |