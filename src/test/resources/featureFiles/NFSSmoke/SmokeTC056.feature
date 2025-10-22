Feature: SmokeTC056 Test Case
  NFS-8105 SmokeTC #56: NFS Export (Lease Component Export from Landing Page)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC056 - NFS Export (Lease Component Export from Landing Page)
  #Jira_ID:NFS-8105
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC056 Test Case
    Given User is on "lease-component-tab" Landing page
    Then User Selects the "Lease Component" records and Export it
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                                                  |
      | LC - LSE Definition            | Contract Excel ID,Lease Component Name,Internal Asset Class Display ID,Unit Of Measure Display ID,Rou Start Date,Rou End Date                                                                |
      | LC - LSE Carry-Over Balances   | Lease Component Excel ID,Accounting Standard Display ID,Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases                                                         |
      | LC - Unit Distributions        | Lease Component Excel ID,Activation Group Name,Number Of Units                                                                                                                               |
      | LC - LSE Terms & Conditions    | Lease Component Excel ID,Term Type,Expense Category Display ID,Name,Amount,Payment Frequency,Amount Frequency,Payment Mode,Payment Calculation Mode,First Payment Date,Payment Term End Date |
      | LC - LSE Vendor Payment Splits | Lease Component Financial Term Excel ID,Partner Display ID,Partner Role Display ID,Percentage                                                                                                |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC056     |
