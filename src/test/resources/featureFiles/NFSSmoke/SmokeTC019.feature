Feature: SmokeTC019 Test Case
  NFS-10881 SmokeTC #19: NFS Mass Export (Unit, Charge, Schedules)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC019 - NFS Mass Export (Unit, Charge, Schedules)
  #Jira_ID:NFS-10881
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P6
  Scenario Outline: SmokeTC019 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #    --------------------Master Agreement Level--------------------------
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    Then User export the Terms
    Then Delete the File
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    Then User Completes the Activation Group Unit List
    #--------------------Charge--------------------------
    Then User clicks on Charges to add charge at "Inception" level
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #    Then User clicks on "IAS" standard to Post the Charge "1"
    #    Then User clicks on "GAAP" standard to Post the Charge "1"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Export"-->""
    Then User create "Master Agreement" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                   |
      | MA - Definition       | Migrated,Excel ID,Year,Master Agreement Name,Lease Area Display ID,State      |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID                            |
    Then Delete the File
    Then User create "Contract" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                   | Column Name                                                                                                                                                                                                                             |
      | CT - LSE Lease Determination | Contract Excel ID                                                                                                                                                                                                                       |
      | CT - LSE Definition          | Migrated,Excel ID,Master Agreement Excel ID,Contract Name,External Reference,Internal Reference,Lease Type,Contract Currency Display ID,Principal Position,Lease Area Display ID,Business Unit Display ID,Company Code Display ID,State |
      | CT - LSE Partners            | Contract Excel ID,Partner Display ID,Excel ID,Partner Role Display ID                                                                                                                                                                   |
      | CT - Partner Contacts        | Contract Partner Excel ID,Contact Excel ID                                                                                                                                                                                              |
      | CT - LSE Accounting          | Contract Excel ID,Contract Rate,Use Ibr Rate,Compounding Frequency,Calendar Type,Profit Center Display ID,Cost Center Display ID,Generate Vendor Invoice                                                                                |
      | CT - Cost Center Allocations | Contract Excel ID,Cost Center Display ID,Profit Center Display ID,Allocation Percentage,Main Cost Center                                                                                                                                |
    Then Delete the File
    Then User create "Lease Component" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                                                  |
      | LC - LSE Definition            | Contract Excel ID,Lease Component Name,Internal Asset Class Display ID,Unit Of Measure Display ID,Rou Start Date,Rou End Date                                                                |
      | LC - LSE Carry-Over Balances   | Lease Component Excel ID,Accounting Standard Display ID,Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases                                                         |
      | LC - Unit Distributions        | Lease Component Excel ID,Activation Group Name,Number Of Units                                                                                                                               |
      | LC - LSE Terms & Conditions    | Lease Component Excel ID,Term Type,Expense Category Display ID,Name,Amount,Payment Frequency,Amount Frequency,Payment Mode,Payment Calculation Mode,First Payment Date,Payment Term End Date |
      | LC - LSE Vendor Payment Splits | Lease Component Financial Term Excel ID,Partner Display ID,Partner Role Display ID,Percentage                                                                                                |
    Then Delete the File
    Then User create "Activation Group" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                        |
      | AG - LSE Definition            | Name,State,Transfer of Ownership,Specialized Asset                                                                                                                 |
      | AG - LSE Accounting            | Contract Rate,Provisioning Frequency,Compounding Frequency,Calendar Type,Indexation Type (Lease),Indexation Type (Non-lease),Current Index Level (Lease),Consumer Price Index Category Display ID (Lease),Reference Date (Lease),Consumer Price Index Category Display ID (Non-lease),Reference Date (Non-Lease)     |
      | AG - Cost Center Allocations   | Allocation Percentage,Main Cost Center                                                                                                                             |
      | AG - LSE Terms & Conditions    | Exercise,Term Type,Expense Category Display ID,Amount Frequency,Amount,Payment Frequency,First Payment Date,Payment Term End Date                                  |
      | AG - LSE Vendor Payment Splits | Activation Group Financial Term Display ID,Partner Display ID,Partner Role Display ID,Percentage                                                                   |
      | AG - LSE Classifications       | Confirmed Classification,System Classification,Useful Life (Year),Useful Life (Month),Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases |
    Then Delete the File
    Then User create "Unit" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                 |
      | Unit                           | Activation Group Excel ID,Name,State,Currency Display ID,Company Display ID,Internal Asset Class Display ID |
      | Unit - Cost Center Allocations | Unit Excel ID,Cost Center Display ID,Allocation Percentage,Profit Center Display ID,Main Cost Center        |
    Then Delete the File
    Then User create "Charge" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                           |
      | Charge                         | Unit Excel ID,Expense Category Display ID,Name,Amount,Due Date        |
      | CH - LSE Vendor Payment Splits | Charge Excel ID,Partner Display ID,Partner Role Display ID,Percentage |
    Then Delete the File
    Then User create "Schedule" Export job for "NFS Export-2"
    Then User checks the File for Column Exist
      | Sheet Name                    | Column Name                                                                                                 |
      | LSE Schedule                  | Accounting Standard Display ID,Lease Classification,Currency Display ID                                     |
      | LSE All Columns Schedule      | Lease Classification,Accrual Status,Depreciation Status,Currency Display ID                                 |
      | LSE Payment Schedule          | Lease Classification,Payment Status,Currency Display ID                                                     |
      | LSE Liability Schedule        | Lease Classification,Payment Status,Currency Display ID                                                     |
      | LSE Asset Transition Schedule | Lease Classification,Weighted Average Rate,Opening GBV,Closing GBV,Currency Display ID,Depreciation Expense |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | SmokeTC019     |