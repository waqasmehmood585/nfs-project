Feature: SmokeTC013 Test Case
  NFS-10869 SmokeTC #13 : Mass Indexation - Create CPI Local Mass Indexation Profile & Job (Lease & Non-Lease)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC013 - Create CPI Local Mass Indexation Profile & Job (Lease & Non-Lease)
  #Jira_ID:NFS-10869
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P5
  Scenario Outline: SmokeTC013 Test Case
    #--------------------Reading Test Case Excel-------------------------- CPI-Local (Lease & Non-Lease)
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
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
     #--------------------Mass Indexation Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Jobs"
    Then User tries to create Indexation Posting Job
      | Batch Size | Principal Position | Modification Date Type | Modification Date | Indexation Type (Lease) | Index Level Type (Lease) | New Index Level (Lease) | Reference Date Type (Lease) | Reference Date (Lease) | GAAP Indexation Treatment | Indexation Date Type (Lease) | Indexation Date (Lease) | Indexation Type (Non-Lease) | Index Level Type (Non-Lease) | New Index Level (Non-Lease) | Reference Date Type (Non-Lease) | Reference Date (Non-Lease) | Indexation Date Type (Non-Lease) | Indexation Date (Non-Lease) | Posting Date Type | Posting Date | Document Date | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories | Open Drafts | Entity Type      |
      | 100        | Lessee             | User Defined           | 2022-06-01        | CPI Local               | Rate                     | 110                     | null                        | null                   | Enforce Non-Lease         | Modification Date            | null                    | CPI Local                   | Rate                         | 105                         | null                            | null                       | Modification Date                | null                        | Modification Date | null         | null          | All                | null                 | All                 | null           | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
      #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Opens the Draft Event--------------------------
    Then User Click on Activation Group "Mass Indexation By Task" Level
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-IN" level

    Examples: 
      | testCaseNumber |
      | SmokeTC013     |
