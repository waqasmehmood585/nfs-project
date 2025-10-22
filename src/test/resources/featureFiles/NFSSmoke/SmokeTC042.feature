Feature: SmokeTC042 Test Case
  NFS-10863 SmokeTC # 42: Perform Sap External postings (Simple Without GVI)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC042 - Perform Sap External postings (Simple Without GVI)
  #Jira_ID:NFS-10863
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P5
  Scenario Outline: SmokeTC042 Test Case
    #--------------------Reading Test Case Excel-------------------------- #Simple without GVI
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Master Agreement Level--------------------------
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
    #Then User enters the carry over balance tab of lease component
    #Then User enters data under the carry over balance page
    And User add new term and condition at "Inception" level
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
       #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
     #--------------------Batch Profile--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    #--------------------Operational Postings Job--------------------------
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                       | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Post             | Erp System's Default | Accrual,Depreciation,Payment,Charge | Open             | User Defined   | 2022-01-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------SAP Posting Bot Profile & Job--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
   Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Journal Document Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal and External" status of "Journals" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal and External" status of "Journals" is "Posted"
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal and External" status of "Charge-1" is "Posted"
    Then User clicks on "GAAP" standard to validate "Internal and External" status of "Charge-1" is "Posted"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal and External" status of "AllColumns" is "Posted"
    Then User clicks on "GAAP" schedule to validate "Internal and External" status of "AllColumns" is "Posted"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal and External" status of "Liability" is "Posted"
    Then User clicks on "GAAP" schedule to validate "Internal and External" status of "Liability" is "Posted"
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->"Operational Postings"-->"Operational Jobs"
    Then User tries to create Batch Posting Job
      | Batch Size | Principal Position | Transaction Type | Reversal Reason      | Journal Types                       | Posting Statuses | From Date Type | From Date  | To Date Type | To Date    | Posting Date Type     | Open Drafts | Entity Type      |
      | 1000       | Lessee             | Reversal         | Erp System's Default | Accrual,Depreciation,Payment,Charge | Open             | User Defined   | 2022-01-01 | User Defined | 2023-12-31 | Schedule Period Dates | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
    #--------------------SAP Posting Bot Profile & Job--------------------------
    Then User opens the "sap-posting-bot" application url
    Then Open Hamburger menu & Click On "SAP Posting Bot"-->"Posting Job"-->"SAP Posting Profiles"
   Then Create SAP Posting Profile for System "FINQ8S-300" and Company "1000" and Accounting Standard "All"
    Then Open Hamburger menu & Click On ""-->""-->"SAP Posting Jobs"
    Then create SAP Posting job with Posting Statuses "Open" & batch size "1000"
    Then User opens the "nakisa-financial-suite" application url
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Charge Status--------------------------
    Then User clicks on "IAS" standard to validate "Internal and External" status of "Charge-1" is "Reversed"
    Then User clicks on "GAAP" standard to validate "Internal and External" status of "Charge-1" is "Reversed"
    #--------------------All Column Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal and External" status of "AllColumns" is "Reversed"
    Then User clicks on "GAAP" schedule to validate "Internal and External" status of "AllColumns" is "Reversed"
    #--------------------Liability Schedule Status--------------------------
    Then User clicks on "IAS" schedule to validate "Internal and External" status of "Liability" is "Reversed"
    Then User clicks on "GAAP" schedule to validate "Internal and External" status of "Liability" is "Reversed"

    Examples:
      | testCaseNumber |
      | SmokeTC042     |
