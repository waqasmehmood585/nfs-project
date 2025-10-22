Feature: NFS-16150 Test Case
  NFS-16150: Golden: Verify that User can copy the MLA, Contract and LC

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that User can copy the MLA, Contract and LC
  #Jira_ID:NFS-16150
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2025.R2
  @Regression @Xray @CopyFunctionality
  Scenario Outline: NFS-16150 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
     #------------------------Copy MLA----------------------------------
     #--------------------Master Agreement Level--------------------------
    Then User creates Master Agreement
      | Year | Lease Area               | Business Unit                 | Asset Class        | MLA Name  | valid From | Valid To | Target Value | Agreement Group | Currency | Legal Jurisdiction | Company Code           | lease Department                  | Lease Group                | Signing Person | Place of Signature | Date of Signature | MLA Description |
      | 2025 | 0001 - Global Lease Area | BU0001 - Global Business Unit | IAC0002 - PROPERTY | NFS-16150 | null       | null     | null         | null            | null     | null               | 1000 - CA - FINQ8S-300 | DE001 - Global Leasing Department | LG001 - Global Lease Group | null           | null               | null              | null            |
    Then User selects partner role and partner under MLA Partner page Level
    Then User copy the Master Agreement with Name as "NFS-16150-Copy" and Lease area ""
    Then User validate the fields of Master Agreement with the following data
      | Asset Class        | Business Unit                 | Company Code           | Lease Department                  | Lease Group                |
      | IAC0002 - PROPERTY | BU0001 - Global Business Unit | 1000 - CA - FINQ8S-300 | DE001 - Global Leasing Department | LG001 - Global Lease Group |
    Then User copy the Master Agreement with Name as "NFS-16150-Copy2" and Lease area "0002 - 0002"
    Then User validate the fields of Master Agreement with the following data
      | Asset Class | Business Unit | Company Code | Lease Department | Lease Group |
      | null        | ""            | ""           | ""               | ""          |
    Then User opens the "nakisa-financial-suite" application url
    Given User is on "master-agreement-tab" Landing page
    Then User selects a record from "Master Agreement" landing page to copy
    Then User opens the "nakisa-financial-suite" application url
      #-------------------Copy Contract---------------------------------
      # --------------------Master Agreement Level--------------------------
    Then User creates Master Agreement
      | Year | Lease Area               | Business Unit | Asset Class | MLA Name         | valid From | Valid To | Target Value | Agreement Group | Currency | Legal Jurisdiction | Company Code | lease Department | Lease Group | Signing Person | Place of Signature | Date of Signature | MLA Description |
      | 2025 | 0001 - Global Lease Area | null          | null        | <testCaseNumber> | null       | null     | null         | null            | null     | null               | null         | null             | null        | null           | null               | null              | null            |
    Then User sends the "Master Agreement" for "mla-send-to-approval-btn" workflow transition
    Then User sends the "Master Agreement" for "mla-approve-btn" workflow transition
    #--------------------Contract Level--------------------------
    And User tries to create Contract
    Then User answers all the questions
    Then User enters data under Contract Definition page
    Then User selects partner role and partner under Contract Partner page at "Inception" Level
    Then User fills the Accounting Tab of Contract
    Then User copy the Contract with Name as "NFS-16150-Copy-1" and Company Code as ""
    Then User validate the fields of Contract with the following data
      | Business Unit                 | Company Code           |
      | BU0001 - Global Business Unit | 1000 - CA - FINQ8S-300 |
    Then User click on Partners and Accounting Page to validate the contract data
    Then User copy the Contract with Name as "NFS-16150-Copy-2" and Company Code as "1005 - CA - FINQ8S-300"
    Then User validate the fields of Contract with the following data
      | Business Unit                 | Company Code           |
      | BU0001 - Global Business Unit | 1005 - CA - FINQ8S-300 |
    Then User click on Partners and Accounting Page to validate the contract data
    Then User opens the "nakisa-financial-suite" application url
    Given User is on "contract-tab" Landing page
    Then User selects a record from "Contract" landing page to copy
    Then User opens the "nakisa-financial-suite" application url
    #---------------------Copy Lease Component-------------------------
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
    Then User enters the carry over balance tab of lease component
    Then User enters data under the carry over balance page
    And User add new term and condition at "Inception" level
    Then User copy the Lease Component with Name as "NFS-16150-Copy-1" and Terms & Conditions "No"
    Then User validate the fields of "Lease Component" with the data
      | Terms and Conditions |
      | Yes                  |
    Then User copy the Lease Component with Name as "NFS-16150-Copy-2" and Terms & Conditions "Yes"
    Then User validate the fields of "Lease Component" with the data
      | Terms and Conditions |
      | No                   |
    Then User opens the "nakisa-financial-suite" application url
    Given User is on "lease-component-tab" Landing page
    Then User selects a record from "Lease Component" landing page to copy


    Examples:
      | testCaseNumber |
      | NFS-16150      |
