Feature: NFS-7448 Test Case
  NFS-7448 - Verify that A.A by count is showing accurate data when AG is classified not activated.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title: Verify that A.A by count is showing accurate data when AG is classified not activated.
  #Jira_ID:NFS-7448
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @AAR
  Scenario Outline: NFS-7448 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           |    1000 | All              | All         | All         | All | All           |
    #--------------------Activity Analysis Reports Job (IAS)--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Reporting Module"-->"Activity Analysis Reports"-->"Activity Jobs"
    Then User create Activity Analysis Report "Job" for "Activity Analysis Report"
      | Name             | Report Type | Activation Group Status | Calendar Type  | From Date  | To Date    | From Year | From Posting Period | To Year | To Posting Period | Principal Position | Accounting Standard | Classification | Lease Type           | Partner | Asset Class | Object List Type | Object List ID    | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | <testCaseNumber> | By Count    | Active                  | 360 Convention | 2023-01-01 | 2023-01-31 | null      | null                | null    | null              | Lessee             | IFRS                | Finance        | Fixed Lease Contract | null    | null        | Activation Group | ActivationGroup-1 | null   | null  | null   | null    | null   | null | null | null      | null  |

    #Then User Download and Validate "IAS" standard "Activity Analysis By Count" Report
    Examples: 
      | testCaseNumber |
      | NFS-7448       |
