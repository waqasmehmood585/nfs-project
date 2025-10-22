Feature: SmokeTC024 Test Case
  NFS-2098 SmokeTC #24: NFS Mass Export (Schedules)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC024 - NFS Mass Export (Schedules)
  #Jira_ID:NFS-2098
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC024 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Schedule" Export job for "NFS Export-1"
    Then User checks the File for Column Exist
      | Sheet Name                      | Column Name                                                                                                  |
      | LSE Schedule                    | Accounting Standard Display ID,Lease Classification,Currency Display ID                                      |
      | LSE All Columns Schedule        | Lease Classification,Accrual Status,Depreciation Status,Currency Display ID                                  |
      | LSE Payment Schedule            | Lease Classification,Payment Status,Currency Display ID                                                      |
      | LSE Liability Schedule          | Lease Classification,Payment Status,Currency Display ID                                                      |
      | LSE Asset Transition Schedule   | Lease Classification,Weighted Average Rate,Opening GBV,Closing GBV,Currency Display ID,Depreciation Expense  |
    Then Delete the File

    Examples:
      | testCaseNumber |
      | SmokeTC019     |

