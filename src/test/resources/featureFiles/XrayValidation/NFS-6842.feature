Feature: NFS-6842 Test Case
  NFS-6842 : Excel Import - Verify that User should be able to download Excel Template and it should have correct sheets.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that User should be able to download Excel Template and it should have correct sheets.
  #Jira_ID:NFS-6842
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Import
  Scenario Outline: NFS-6842 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Mass Export-----------------------------------
    Then Open Hamburger menu & Click On "Import/Export Information"-->"Import"-->""
    Then User download the Import Template file of "Master Agreement"
      | ERP System              | Principal Position | SheetsToImport                                      |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | MA - Definition,MA - Partners,MA - Partners Contact |
    Then User checks the File for Column Exist
      | Sheet Name            | Column Name                                                                   |
      | MA - Definition       | Excel ID,Year,Master Agreement Name,Lease Area Display ID                     |
      | MA - Partners         | Master Agreement Excel ID,Partner Display ID,Excel ID,Partner Role Display ID |
      | MA - Partner Contacts | Master Agreement Partner Excel ID,Contact Excel ID                            |
    Then Delete the File
    Then User download the Import Template file of "Contract"
      | ERP System              | Principal Position | SheetsToImport                                                                                                                                |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | CT - LSE Definition,CT - LSE Lease Determination,CT - LSE Accounting,CT - Cost Center Allocations,CT - LSE Partners,CT - LSE Partner Contacts |
    Then User checks the File for Column Exist
      | Sheet Name                   | Column Name                                                                                                                                                                                                              |
      | CT - LSE Lease Determination | Contract Excel ID                                                                                                                                                                                                        |
      | CT - LSE Definition          | Excel ID,Master Agreement Excel ID,Contract Name,External Reference,Internal Reference,Lease Type,Contract Currency Display ID,Principal Position,Lease Area Display ID,Business Unit Display ID,Company Code Display ID |
      | CT - LSE Partners            | Contract Excel ID,Partner Display ID,Excel ID,Partner Role Display ID                                                                                                                                                    |
      | CT - Partner Contacts        | Contract Partner Excel ID,Contact Excel ID                                                                                                                                                                               |
      | CT - LSE Accounting          | Contract Excel ID,Contract Rate,Use Ibr Rate,Compounding Frequency,Calendar Type,Profit Center Display ID,Cost Center Display ID,Generate Vendor Invoice                                                                 |
      | CT - Cost Center Allocations | Contract Excel ID,Cost Center Display ID,Profit Center Display ID,Allocation Percentage,Main Cost Center                                                                                                                 |
      | CT - Notifications           | Contract Excel ID,Contract Notification Topic Type,Subscribed Username                                                                                                                                                   |
    Then Delete the File
    Then User download the Import Template file of "Lease Component"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                                                                                     |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | LC - LSE Definition,LC - Unit Distribution,LC - LSE Carry-Over Balances,LC - LSE Terms & Conditions,LC - LSE Vendor Payment Splits |
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                                                                                                                                          |
      | LC - LSE Definition            | Contract Excel ID,Lease Component Name,Internal Asset Class Display ID,Unit Of Measure Display ID,Rou Start Date,Rou End Date                                                                        |
      | LC - LSE Carry-Over Balances   | Lease Component Excel ID,Accounting Standard Display ID,Accrued Interest Expense,Use Straight-Line Depreciation for Operating Leases                                                                 |
      | LC - Unit Distributions        | Lease Component Excel ID,Activation Group Name,Number Of Units                                                                                                                                       |
      | LC - LSE Terms & Conditions    | Lease Component Excel ID,Term Type,Expense Category Display ID,Name,Amount,Payment Frequency,Amount Frequency,Payment Mode,Payment Calculation Mode,First Payment Date,Payment Term End Date         |
      | LC - LSE Vendor Payment Splits | Lease Component Financial Term Excel ID,Partner Display ID,Partner Role Display ID,Percentage,Calculate Taxes,Tax Jurisdiction Display Id,Tax Code Determination Display Id,Tax Exemption Percentage |
    Then Delete the File
    Then User download the Import Template file of "Activation Group"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                                                   |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | AG - LSE Definition,AG - LSE Accounting,AG - LSE Classifications |
    Then User checks the File for Column Exist
      | Sheet Name                  | Column Name                                                                                                                                  |
      | AG - LSE Definition         | Name,Lease Component Excel ID,Transfer of Ownership,Specialized Asset,ROU End Date,FMV / Unit (Reassessed),Salvage Value / Unit (Reassessed) |
      | AG - LSE Accounting         | Contract Rate,Indexation Type (Lease),Indexation Type (Non-lease)                                                                            |
      | AG - LSE Terms & Conditions | Lease Component Financial Term Excel ID,Activation Group Excel ID,Exercise,Indexation Lease Percentage,Indexation Non Lease Percentage       |
      | AG - LSE Classifications    | Confirmed Classification,Useful Life (Year),Useful Life (Month),Accounting Standard Display ID,Activation Group Excel ID                     |
    Then Delete the File
    Then User download the Import Template file of "Unit"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport                      |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Unit,Unit - Cost Center Allocations |
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                                 |
      | Unit                           | Name,Activation Group Excel ID,Activation Date,ROU End Date                 |
      | Unit - Cost Center Allocations | Unit Excel ID,Cost Center Display ID,Allocation Percentage,Main Cost Center |
    Then Delete the File
    Then User download the Import Template file of "Charge"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Charge         |
    Then User checks the File for Column Exist
      | Sheet Name                     | Column Name                                                           |
      | Charge                         | Unit Excel ID,Expense Category Display ID,Name,Amount,Due Date        |
      | CH - LSE Vendor Payment Splits | Charge Excel ID,Partner Display ID,Partner Role Display ID,Percentage |
    Then Delete the File
    Then User download the Import Template file of "Contact"
      | ERP System              | Principal Position | Auto Transition | Final State | SheetsToImport |
      | FINQ8S-300 - FINQ8S-300 | Lessee             | No              | null        | Contact        |
    Then User checks the File for Column Exist
      | Sheet Name | Column Name                                   |
      | Contact    | Name,Position,Email,Phone,Address,Description |
    Then Delete the File

    Examples: 
      | testCaseNumber |
      | NFS-6842       |
