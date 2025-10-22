Feature: NFS-5175 Test Case
  NFS-2079: Code-Coverage - Create MLA with all Mandatory and non-mandatory fields.

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Code-Coverage - Create MLA with all Mandatory and non-mandatory fields.
  #Jira_ID:NFS-5175
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xrays @Coverage
  Scenario Outline: NFS-5175 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Master Agreement Level--------------------------
    Then User creates Master Agreement
      | Year | Lease Area               | Business Unit                 | Asset Class        | MLA Name | valid From | Valid To   | Target Value | Agreement Group | Currency              | Legal Jurisdiction | Company Code           | lease Department                  | Lease Group                | Signing Person | Place of Signature | Date of Signature | MLA Description        |
      | 2025 | 0001 - Global Lease Area | BU0001 - Global Business Unit | IAC0002 - PROPERTY | NFS-5175 | 2024-01-01 | 2024-12-31 | 5175         | 02 - Agreement  | CAD - Canadian Dollar | NFS-5175           | 1000 - CA - FINQ8S-300 | DE001 - Global Leasing Department | LG001 - Global Lease Group | Nakisa         | MLA                | 2025-01-01        | NFS-5175-Code-Coverage |
    Then User selects partner role and partner under MLA Partner page Level
    Then User deletes the Partner "4" at "Master Agreement Inception" level
    #--------------------add Contact at MLA Partners--------------------------
    Then User add Contact on Contract Partners
      | Partner Number | Name  | Position | Email       | Phone | Address  | Description    |
      | 1              | User1 | SQA      | @nakisa.com | 9999  | Montreal | No Description |
      | 1              | User2 | SQA      | @nakisa.com | 9229  | Montreal | No Description |
      | 3              | User3 | SSQA     | @nakisa.com | 1111  | Montreal | No Description |
    #--------------------Delete Contact at Contract Partners--------------------------
    Then User delete the contact "1" from the Partner "1"
     #--------------------Edit Contact at MLA Partners--------------------------
    Then User edit Partner contact details
      | Partner Number | Name  | Position | Email       | Phone | Address   | Description |
      | 1              | User5 | SQA5     | null        | 5555  | Montreal5 | null        |
      | 3              | null  | null     | @nakisa.com | 6666  | Toronto6  | null        |
    Then user delete partners "1,2"
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow with a message "MLA has been approved"
    Then User sends the "Master Agreement" for "mla-callback-btn" workflow transition
    Then User delete the "Master Agreement"
    Then User tries to create Master Agreement
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level--------------------------
    And User creates Contract
      | Principal Position | Business Unit                 | Company Code           | Name     |
      | Lessee             | BU0001 - Global Business Unit | 1000 - CA - FINQ8S-300 | NFS-5175 |
    Then User answers all the questions
    Then enters data under Contract Definition page
      | External CT Ref | Internal CT Ref | Validity From | Validity To | Lease Type                | Contract Category | Amendment Date | Currency              | Form of Lease | Description         | Joint Venture | Lease Department                  | Lease Group                | Signing Person | Signature Place | Date Of Signature | Group 1               | Group 2      | Group 3 | Group 4 |
      | 5175            | 5175            | 2024-01-01    | 2025-12-31  | 02 - Fixed Lease Contract | 01 - Budgeted     | 2024-12-31     | CAD - Canadian Dollar | Full          | NFS-5175 - Coverage | Gross Lease   | DE001 - Global Leasing Department | LG001 - Global Lease Group | Nakisa         | Canada          | 2024-12-31        | 1 - Standard Contract | 1 - Standard | 5175    | 5175    |
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User fills Accounting Tab of Contract
      | Functional Area             | Business Area               | Segment           | Network               | Track Cost | Order Type                 | Internal Order              | Payment Terms | Payment Block       | Payment Method |
      | 0002 - Functional area 0002 | Z001 - Nakisa Business area | SEG_D - Segment 4 | 4000002 - Network 003 | True       | ZLEA - SLAN Lease Contract | 000000100020 - 171000100000 | 0011 - 0011   | P - Payment request | PM-001 - PM-01 |
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow with a message "Contract has been approved"
    Then User sends the "Contract" for "contract-callback-btn" workflow transition
    Then User delete the "Contract"
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
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow with a message "Lease Component has been approved"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User click on the "Lease Component" with Name "NFS-5175"
    And User sends the "Lease Component" for "lc-callback-btn" workflow transition
    Then User delete the "Lease Component"
    #--------------------Lease Component Level--------------------------
    And User tries to create Lease Component
    Then User enters data under Lease Component Definition page
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User sends the Data in Accounting tab of Activation Group
      | Use IBR Rate | Embedded Derivative | Functional Area             | Business Area               | Segment           | Track Cost | Internal Order Type        | Purchase Organization | Purchase order          | Network               | Internal Order              |
      | Yes          | Yes                 | 0004 - Functional area 0004 | Z001 - Nakisa Business area | NAKSEG1 - Segment | Yes        | ZLEA - SLAN Lease Contract | 1000 - Nakisa Canada  | 4500000002 - 4500000002 | 4000002 - Network 003 | 000000100020 - 171000100000 |
    Then User enters data under the carry over balance page in Activation Group
    Then User clicks on all fields in AG
    #--------------------User Auxiliary Profile --------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Report Profile (For Coverage) --------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | Fiscal Variant | 001        | BU0001        | 1000    | DE001            | LG001       | 0000012010  | All | Profit        |
    Then User search the records in the "Report" Profile
      | Name             | Principal Position | ERP Systems | Calendar Type | Fiscal Variant | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS  | Profit Center |
      | <testCaseNumber> | null               | null        | null          | null           | 001        | BU0001        | 1000    | DE001            | LG001       | 0000012010  | null | Profit        |
      #--------------------Batch Profile (For Coverage)--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | 001        | BU0001        | 1000    | DE001            | LG001       | 0000012010  | All | 0000000120    | 0001            | 001           |

    Examples:
      | testCaseNumber |
      | NFS-5175       |
