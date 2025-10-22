Feature: SmokeTC053 Test Case
  NFS-2074 SmokeTC #53: NFS Mass Export (Activation Group)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC053 - NFS Mass Export (Activation Group)
  #Jira_ID:NFS-2074
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC053 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Given User is on "activation-group-tab" Landing page
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Activation Group" Export job for "NFS Export-1"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                        |
      | AG - LSE Definition            | Name,State,Transfer of Ownership,Specialized Asset                                                                                                                 |
      | AG - LSE Accounting            | Contract Rate,Provisioning Frequency,Compounding Frequency,Calendar Type,Indexation Type (Lease),Indexation Type (Non-lease)                                       |
      | AG - Cost Center Allocations   | Allocation Percentage,Main Cost Center                                                                                                                             |
      | AG - LSE Terms & Conditions    | Exercise,Term Type,Expense Category Display ID,Amount Frequency,Amount,Payment Frequency,First Payment Date,Payment Term End Date                                  |
      | AG - LSE Vendor Payment Splits | Activation Group Financial Term Display ID,Partner Display ID,Partner Role Display ID,Percentage                                                                   |
      | AG - LSE Classifications       | Confirmed Classification,System Classification,Useful Life (Year),Useful Life (Month),Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |
