Feature: NFS-7444 Test Case
  NFS-7444: Verify that that A.A Profile is not getting created when user skips any required field and the submit button is not enabled

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "user-auxiliary" application url

  #TC_Title:Verify that that A.A Profile is not getting created when user skips any required field
  #Jira_ID:NFS-7444
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-7444 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type  | Fiscal Variant | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 360 Convention | null           | All        | All           | 1000    | All              | All         | All         | All | All           |

    Examples:
      | testCaseNumber |
      | NFS-7444       |
