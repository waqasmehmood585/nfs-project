Feature: NFS-12285 Test Case
  NFS-12285: NLA: NLA: Contract Expiration Report- Verify that user should be able to apply filters and
  user should be able define inputs to refine the report generation (Code Coverage)
  should be able define inputs to refine the report generation
  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that user should be able to apply filters and user should be able define inputs to refine the report generation (Code Coverage)
  # to refine the report generation
  #Jira_ID:NFS-12285
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R1
  @Regression @Xray @ContractExpiration
  Scenario Outline: NFS-12285 Test Case
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
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #---------------------------Contract Expiration Report---------------------#
    Then Open Hamburger menu & Click On "Reporting Module"-->"Contract Expiration Report"-->""
    Then User select filters from Contract Expiration Report
      | Filter Name             | Filter value                              |
      | Accounting Standard     | GAAP                                      |
      | Classification          | OPERATING                                 |
      | System                  | FINQ8S-300                                |
      | Activation Group Id     | id                                        |
      | Migration Status        | null                                      |
      | Lease Area              | 0001 - Global Lease Area                  |
      | Business Unit           | BU0001                                    |
      | Company                 | 1000                                      |
      | Partner                 | DWR Inc.                                  |
      | Lease Department        | null                                      |
      | Lease Group             | null                                      |
      | Lease Type              | Lease Contract (Fix)                      |
      | Asset Class             | IAC0002 - PROPERTY                        |
      | Activation Group Status | Active                                    |
      | Business Area           | null                                      |
      | Profit Center           | 0000000120                                |
      | Cost Center ID          | 0000012010                                |
      | As At Date              | From This month and before Next 11 months |
  #Activation Group Revision  | Business Area  | Profit Center ID  | Cost Center ID  | As At Date      |
#    | IAS                | FINANCE,PROVISIONING     | FINQ8S-300| 0001 - Global Lease Area | BU0001        | 1000,1005| DWR Inc.   | DE001           | LG001       | Lease Contract (Fix) |  IAC0001 - Equipment  |  Active                     | 0002           |  0000000120       | 0000012010     | null            |





#    | Accounting Standard| Classification           |  System   | Lease Area              | Business Unit  | Company  | Partner   | Lease Department | Lease Group | Lease Type           | Asset Class            | Activation Group Revision  | Business Area  | Profit Center ID  | Cost Center ID  | As At Date      |
#    | IAS                | FINANCE,PROVISIONING     | FINQ8S-300| 0001 - Global Lease Area | BU0001        | 1000,1005| DWR Inc.   | DE001           | LG001       | Lease Contract (Fix) |  IAC0001 - Equipment  |  Active                     | 0002           |  0000000120       | 0000012010     | null            |

#    | Accounting Standard | Classification  |  System   | Lease Area     | Business Unit  | Company  | Partner   | Lease Department | Lease Group | Lease Type  | Asset Class  | Activation Group Revision  | Business Area  |    Profit Center ID              | Cost Center ID       | As At Date      |
#    | null                | null            | null       | null          | null           | null      | null     | null             | null         | null       |  null       |  null                        | null           |  0000000120,0000000510          | 0000012010,0000011050      | null            |

    Examples:
      | testCaseNumber |
      | NFS-12285      |
