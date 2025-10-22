Feature: SmokeTC011 Test Case
  NFS-8015 SmokeTC #11: NFS Mass Export (Lease Component)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC011 - NFS Mass Export (Lease Component)
  #Jira_ID:NFS-8015
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC011 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Given User is on "lease-component-tab" Landing page
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Lease Component" Export job for "NFS Export-1"
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
      | SmokeTC019     |
