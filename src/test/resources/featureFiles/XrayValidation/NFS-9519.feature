Feature: NFS-9519 Test Case
  NFS-9519 : AG Revert: Inception Revert - Verify user will not be able to run any mass job on reverted AG.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify user will not be able to run any mass job on reverted AG.
  #Jira_ID:NFS-9519
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-9519 Test Case
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
    Then User Completes the Activation Group Unit List
    #And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Revert the AG--------------------------
    Then User revert the AG with reversal reason as "Reversal in current period"
    Then User clicks on "Active" status "Activation Group" with name "Inception"
    Then User verify that "Activation Group" status is "Define"
     #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Batch Jobs--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment | Open             | User Defined   | 2022-01-01 | User Defined | 2022-03-31 | Schedule Period Dates | Yes         | Activation Group |
    #--------------------Mass Indexation Jobs--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Jobs"
    Then User tries to create Indexation Posting Job
      | Batch Size | Principal Position | Modification Date Type | Modification Date | Indexation Type (Lease) | Index Level Type (Lease) | New Index Level (Lease) | Reference Date Type (Lease) | Reference Date (Lease) | GAAP Indexation Treatment | Indexation Date Type (Lease) | Indexation Date (Lease) | Indexation Type (Non-Lease) | Index Level Type (Non-Lease) | New Index Level (Non-Lease) | Reference Date Type (Non-Lease) | Reference Date (Non-Lease) | Indexation Date Type (Non-Lease) | Indexation Date (Non-Lease) | Posting Date Type | Posting Date | Document Date | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories | Open Drafts | Entity Type      |
      | 100        | Lessee             | User Defined           | 2022-06-01        | CPI Local               | Rate                     | 110                     | null                        | null                   | Enforce Non-Lease         | Modification Date            | null                    | null                        | null                         | null                        | null                            | null                       | null                             | null                        | Modification Date | null         | null          | All                | null                 | All                 | null           | Yes         | Activation Group |
      #--------------------Mass Modification Jobs--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Modification"-->"Modification Jobs"
    Then User tries to create Modification Posting Job
      | Batch Size | Transaction Type              | Activation Group Event Name | LC Event Name | Modification Date Type | Activation Dates Types | Posting Date | Document Date | Modification Date | Contract rate | Apply Indexation | Exercise | Open Drafts | List Filter Type | Effective Date Type | Effective Date |
      | 100        | Activation Group Modification | E1                          | null          | null                   | null                   | null         | null          | null              | 8             | null             | null     | Yes         | Activation Group | null                | null           |

    Examples:
      | testCaseNumber |
      | NFS-9519       |
