Feature: NFS-7789 Test Case
  NFS-7789: :Validate AA Report is generated successfully when both mandatory and non-mandaotry fields are filled.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Validate AA Report is generated successfully when both mandatory and non-mandaotry fields are filled.
  #Jira_ID:NFS-7789
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @AAR
  Scenario Outline: NFS-7789 Test Case
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
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
    #--------------------Activity Analysis Reports Job (IAS)--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Jobs"
    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
      | Name             | Report Type | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type                | Partner          | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | By Amount   | Active                  | 360 Convention | 2022-01-01 | 2022-12-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Fixed Lease Contract | V1500 - DWR Inc. |        0002 | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then User Download and Validate "IAS" standard "Activity Analysis By Amount" Report
    #--------------------Activity Analysis Reports Job GAAP--------------------------
    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
      | Name             | Report Type | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type                | Partner          | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | By Amount   | Active                  | 360 Convention | 2022-01-01 | 2022-12-31 | null      | null                | null    | null              | Lessee             | GAAP                | Operating      | Fixed Lease Contract | V1500 - DWR Inc. |        0002 | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |
    Then the "Report" job should be completed with status "Done"
    Then User Download and Validate "GAAP" standard "Activity Analysis By Amount" Report

    Examples: 
      | testCaseNumber |
      | NFS-7789       |
