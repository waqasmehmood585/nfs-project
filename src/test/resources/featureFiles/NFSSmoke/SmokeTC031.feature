Feature: SmokeTC031 Test Case
  NFS-2083 SmokeTC #31: Scheduler jobs for SAP Posting bot.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with specific credentials
    Then User opens the "nakisa-financial-suite" application url with specific user

  #TC_Title:SmokeTC031 - Scheduler jobs for SAP Posting bot.
  #Jira_ID:NFS-2083
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee
  Scenario Outline: SmokeTC031 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------SAP Posting Bot Profile & Job--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
    Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Scheduled Jobs"
    Then User create SAP Posting Scheduled Job for "SAP Posting Job"
      | Batch Size | Hourly | Daily | Weekly | Monthly | Yearly | Hour | Date | Week Days | Month |
      | 1000       | Yes    | No    | No     | No      | No     | 1    | null | null      | null  |

    Examples:
      | testCaseNumber |
      | SmokeTC031     |
