Feature: NFS-12802 Test Case
  NFS-12802: SPB: Journal Voucher- Verify journal voucher details

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "sap-posting-bot" application url

  #TC_Title:Verify journal voucher details
  #Jira_ID:NFS-12802
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2024.R3
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-12802 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Opening Ledger Transactions--------------------------
    Then User creates journal voucher definition tab
      | Name          | Lease Area | Business Area | ERP System | Company | Currency | Document Type | Accounting Standard | Lease Classification | Posting Date | Document Date | Principal Position | Automatic Reversal | Reversal Posting Date | Reversal Document Date | Reversal Reason               | WBS         | Segment | Network                  | Functional Area | Payment Term | Payment Block          | Payment Method | Plant | Internal Order |
      | Text Document | 0001       | BU0001        | FINQ8S-300 | 1000    | CAD      | ML            | IAS                 | Finance              | 2025-01-01   | 2025-12-31    | Lessee             | Yes                | 2025-01-03            | 2025-01-03             | 01-Reversal in current period | TEST_1000_2 | SEG_C   | 000004000000-Network 001 | PT03            | ZB04         | R-Invoice verification | PM-002-PM-02   | 1000  | FINQ8S-300     |
    Then User creates journal voucher entries tab
      | Account Number | Debit Contract | Debit Company | Debit Second | Credit Contract | Credit Company | Credit Second | Cost Center | Profit Center | Vendor         | Tax Jurisdiction | Tax Determination |
      | 0000570022     | 100            | 100           | 100          | NULL            | NULL           | NULL          | 12010       | 120           | NULL           | NULL             | NULL              |
      | 0000200700     | NULL           | NULL          | NULL         | 100             | 100            | 100           | NULL        | NULL          | V1500-DWR Inc. | NULL             | NULL            |
    Then User creates journal voucher additional information tab
      | Note |
      | Test |
    Then User Send To Approval Journal Voucher
    Then User Rework the Journal Voucher
    Then User Approve the Journal Voucher
    Then User Close the Journal Voucher


    Examples:
      | testCaseNumber |
      | NFS-12802      |
