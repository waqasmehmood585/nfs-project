Feature: NFS-7753 Test Case
  NFS-7753: Golden: Audit Logs - Test Audit Log feature for MLA, Contract, LC and AG

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Test Audit Log feature for MLA, Contract, LC and AG
  #Jira_ID:NFS-7753
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4
#  @Regression @Xray @AuditLogs
  Scenario Outline: NFS-7753 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Validating Audit logs feature--------------------------
    Then Open Hamburger menu & Click On "Audit Logs"-->"View Latest Audit Logs"-->""
    Then User select filters for Audit Logs
    |Created At   |  Created By  | Detail 1  | Detail 1 Type  | Detail 2  | Detail 2 Type | Detail 3   |  Detail 3 Type  | Entity Name  | Field Name  | New Value  | Old Value | Revision Id | Revision Type  |
    | value       |  value       | value     |   value        | value     |  value        |  value     |  value          | value        | value       | value      |  null     | value       |   null         |
    Then User click on Filter Profiles to create a profile with name "<testCaseNumber>"
    Then User click on Filter Profiles to delete a created profile
    Then User click on toggle filters for ascending and descending operations
    Then User click on Export Table button to export as "XLS,CSV,PDF"
    Then User click on Export Table button to export as "Schedule"
    Then User schedule the Audit log report with format as "CSV"
    | Schedule From  |  Schedule To   |  Schedule Option | Hour    | Date    | Week Days | Month | Months Name |
    | 2022-01-01     | 2022-12-31     |   Hourly         |    2     | null   | null      | null  | null        |
    Then Open Hamburger menu & Click On "Audit Logs"-->"Export Archived Audit Logs"-->""
    Then User export archived audit logs
    | From Date    | To Date      |  Entity  |  Created By                |
    | 2022-01-01   | 2022-03-31   |  All     | waqas.mehmood@nakisa.com   |


    Examples:
      | testCaseNumber |
      | NFS-7753       |
