Feature: SmokeTC040 Test Case
  NFS-8104 SmokeTC #40: NFS Export (Activation Group Export from Landing Page)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC040 - NFS Export (Activation Group Export from Landing Page)
  #Jira_ID:NFS-8104
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC040 Test Case
    Given User is on "activation-group-tab" Landing page
    Then User Selects the "Activation Group" records and Export it
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                                             |
      | AG - LSE Definition            | Name,State,Transfer of Ownership,Specialized Asset                                                                                                                                      |
      | AG - LSE Accounting            | Contract Rate,Provisioning Frequency,Compounding Frequency,Calendar Type,Indexation Type (Lease),Indexation Type (Non-lease),Purchase Organization Display ID,Purchase Order Display ID |
      | AG - Cost Center Allocations   | Allocation Percentage,Main Cost Center                                                                                                                                                  |
      | AG - LSE Terms & Conditions    | Exercise,Term Type,Expense Category Display ID,Amount Frequency,Amount,Payment Frequency,First Payment Date,Payment Term End Date                                                       |
      | AG - LSE Vendor Payment Splits | Activation Group Financial Term Display ID,Partner Display ID,Partner Role Display ID,Percentage                                                                                        |
      | AG - LSE Classifications       | Confirmed Classification,System Classification,Useful Life (Year),Useful Life (Month),Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases                      |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC040     |
