Feature: SmokeTC021 Test Case
  NFS-6598 SmokeTC # 21 Disclosure Reports for RUB - Verify user is able to generate disclosure reports for all the records without giving specific contract ID

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC021 - Disclosure Reports for RUB
  #Jira_ID:NFS-6598
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P1
  Scenario Outline: SmokeTC021 Test Case
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
    Then User stores the "Contract-1" id
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    Then User stores the "LeaseComponent-1" id
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-1" id
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Disclosure Reports Job (IAS)--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Disclosure Reports"-->"Disclosure Jobs"
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | 2023-05-01 | 2023-08-31 | Lessee             | Regular Calendar | IFRS                | Finance        | RUB      | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Disclosure Report" job should be completed with status "Done"
    And User Download and Validate "IAS" standard "Asset Roll Forward" Report
    And User Download and Validate "IAS" standard "Cash Flow" Report
    And User Download and Validate "IAS" standard "Expense" Report
    And User Download and Validate "IAS" standard "Lease Liability" Report
    And User Download and Validate "IAS" standard "Maturity Analysis" Report
    And User Download and Validate "IAS" standard "Non Lease Charge Expense" Report
    And User Download and Validate "IAS" standard "Weighted Avg Discount Rate" Report
    And User Download and Validate "IAS" standard "Weighted Avg Lease Term" Report
     #--------------------Disclosure Reports Job (GAAP)--------------------------
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | 2023-05-01 | 2023-08-31 | Lessee             | Regular Calendar | GAAP                | Operating      | RUB      | Active                  | Contract         | Contract-1     | Yes                       | Yes              | Yes            | Yes                    | Yes                      | Yes                             | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Disclosure Report" job should be completed with status "Done"
    And User Download and Validate "GAAP" standard "Asset Roll Forward" Report
    And User Download and Validate "GAAP" standard "Cash Flow" Report
    And User Download and Validate "GAAP" standard "Expense" Report
    And User Download and Validate "GAAP" standard "Lease Liability" Report
    And User Download and Validate "GAAP" standard "Maturity Analysis" Report
    And User Download and Validate "GAAP" standard "Non Lease Charge Expense" Report
    And User Download and Validate "GAAP" standard "Weighted Avg Discount Rate" Report
    And User Download and Validate "GAAP" standard "Weighted Avg Lease Term" Report

    Examples:
      | testCaseNumber |
      | SmokeTC021     |

