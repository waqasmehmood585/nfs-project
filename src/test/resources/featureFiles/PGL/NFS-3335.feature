Feature: NFS-3335 Test Case
  NFS-3335: PGL: Disclosure reports failing

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Disclosure reports failing
  #Jira_ID:NFS-3335
  #TC_Category:CustomerBug
  #TC_Customers:Walmart
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @CustomerBug @PGL
  Scenario Outline: NFS-3335 Test Case
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
    Then User exercise the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    Then User stores the "ActivationGroup-1" id
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
      | <testCaseNumber> | 2023-01-01 | 2023-03-31 | Lessee             | Regular Calendar | IFRS                | Finance        | 10 - CAD | null                    | Contract         | Contract-1     | No                        | No               | No             | Yes                    | Yes                      | No                              | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User validate the status of Disclosure Report "Job" for "Disclosure Report"
    #--------------------Disclosure Reports Profile & Job--------------------------
    Then User create Disclosure Report "Job" for "Disclosure Report"
      | Name             | From Date  | To Date    | Principal Position | Calendar Type    | Accounting Standard | Classification | Currency | Activation Group Status | Object List Type | Object List ID | Asset Roll Forward Report | Cash Flow Report | Expense Report | Lease Liability Report | Maturity Analysis Report | Non Lease Charge Expense Report | Weighted Avg Discount Rate Report | Weighted Avg Lease Term Report | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | 2023-01-01 | 2023-03-31 | Lessee             | Regular Calendar | GAAP                | Operating      | 10 - CAD | null                    | Contract         | Contract-1     | No                        | No               | No             | Yes                    | Yes                      | No                              | Yes                               | Yes                            | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then User validate the status of Disclosure Report "Job" for "Disclosure Report-2"

    Examples:
      | testCaseNumber |
      | NFS-3335       |
