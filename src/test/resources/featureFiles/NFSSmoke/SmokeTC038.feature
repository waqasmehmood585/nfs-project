Feature: SmokeTC038 Test Case
  NFS-7793 SmokeTC #38: NFS Export (Contract Export from Landing Page)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC038 - NFS Export (Contract Export from Landing Page)
  #Jira_ID:NFS-7793
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC038 Test Case
    Given User is on "contract-tab" Landing page
    Then User Selects the "Contract" records and Export it
    Then User checks the File for Column Exist
      | Sheet Name                           | Column Name                                                                                                                                                                                                                         |
      | CT - LSE Lease Determination         | Contract Excel ID                                                                                                                                                                                                                   |
      | CT - LSE Definition                  | Migrated,Excel ID,Master Agreement Excel ID,Contract Name,External Reference,Internal Reference,Lease Type,Contract Currency Display ID,Principal Position,Lease Area Display ID,Business Unit Display ID,Company Code Display ID |
      | CT - LSE Partners                    | Contract Excel ID,Partner Display ID,Excel ID,Partner Role Display ID                                                                                                                                                               |
      | CT - Partner Contacts                | Contract Partner Excel ID,Contact Excel ID                                                                                                                                                                                        |
      | CT - LSE Accounting                  | Contract Excel ID,Contract Rate,Use Ibr Rate,Compounding Frequency,Calendar Type,Profit Center Display ID,Cost Center Display ID,Generate Vendor Invoice                                                                            |
      | CT - Cost Center Allocations         | Contract Excel ID,Cost Center Display ID,Profit Center Display ID,Allocation Percentage,Main Cost Center                                                                                                                            |
      | CT - Notifications                   | Contract Excel ID,Contract Notification Topic Type,Subscribed Username                                                                                                                                                              |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC038     |