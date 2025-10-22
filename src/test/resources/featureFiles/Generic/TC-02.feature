Feature: TC-02 Test Case
  User logins into the application with valid credentials in-order to perform the test case

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Schedules for Accounting Test Case TC-02
  #Jira_ID:NFS-8898
  #TC_Category:Engine/Core/Accounting
  #TC_Customers:walmartNUAT
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Engine/Core/Accounting @walmartNUAT
  Scenario Outline: TC-02 Test Case
    #--------------------Reading Test Case Excel--------------------------
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
    And User add new term and condition at "Inception" level
    And User sends the "Lease Component" for "lc-send-to-approval-btn" workflow transition
    And User sends the "Lease Component" for "lc-approve-btn" workflow transition
    #--------------------Activation Group Level--------------------------
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1"
    Then User enters the Accounting tab of Activation Group
    Then User enters the "Inception" Consumer Price Index Values of Activation Group
    Then User enters the Terms and Conditions tab of Activation Group
    Then User index the Term and Condition number "1"
    Then User Completes the Activation Group Unit List
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Inception" documents
    Then User clicks on "GAAP" standard to validate "Inception" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Inception" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Inception" level
     #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-01-01" to "2022-05-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-01-01" to "2022-05-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-01-01" to "2022-05-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-01-01" to "2022-05-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Inception" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Inception" level
    #--------------------User Auxiliary Profile--------------------------
    Then User opens the "user-auxiliary" application url
    #--------------------Batch Posting Profile & Job--------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All                | All         | All | All           | All             | All           |
    Then User opens the "nakisa-financial-suite" application url
    Then Open Hamburger menu & Click On "Batch Management"-->"Mass Indexation"-->"Indexation Jobs"
    Then User tries to create Indexation Posting Job
      | Batch Size | Principal Position | Modification Date Type | Modification Date | Indexation Type (Lease) | Index Level Type (Lease) | New Index Level (Lease) | Reference Date Type (Lease) | Reference Date (Lease) | GAAP Indexation Treatment | Indexation Date Type (Lease) | Indexation Date (Lease) | Indexation Type (Non-Lease) | Index Level Type (Non-Lease) | New Index Level (Non-Lease) | Reference Date Type (Non-Lease) | Reference Date (Non-Lease) | Indexation Date Type (Non-Lease) | Indexation Date (Non-Lease) | Posting Date Type | Posting Date | Document Date | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories | Open Drafts | Entity Type      |
      | 1000       | Lessee             | User Defined           | 2022-06-01        | CPI Local               | Rate                     | 110                     | null                        | null                   | Enforce Non-Lease         | Modification Date            | null                    | null                        | null                         | null                        | null                            | null                       | null                             | null                        | Modification Date | null         | null          | All                | null                 | All                 | null           | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
      #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Opens the Draft Event--------------------------
    Then User Click on Activation Group "Mass Indexation By Task" Level
    #--------------------Activation Group Indexation Event-------------------------- Without Batch
    #Then User tries to create AG Event at "AG Event-1" Level
    #Then User enters the Accounting tab of Activation Group
    #Then User enters the "AG Event-1" Consumer Price Index Values of Activation Group
    #And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    #And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    #And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    #And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    #And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-IN" level
#     #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-06-01" to "2022-12-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2022-06-01" to "2022-12-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-06-01" to "2022-12-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2022-06-01" to "2022-12-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event1-IN" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event1-IN" level
    #--------------------Mass Indexation-----------------------------------
    Then Open Hamburger menu & Click On "Batch Management"-->""-->"Indexation Jobs"
    Then User tries to create Indexation Posting Job
      | Batch Size | Principal Position | Modification Date Type | Modification Date | Indexation Type (Lease) | Index Level Type (Lease) | New Index Level (Lease) | Reference Date Type (Lease) | Reference Date (Lease) | GAAP Indexation Treatment | Indexation Date Type (Lease) | Indexation Date (Lease) | Indexation Type (Non-Lease) | Index Level Type (Non-Lease) | New Index Level (Non-Lease) | Reference Date Type (Non-Lease) | Reference Date (Non-Lease) | Indexation Date Type (Non-Lease) | Indexation Date (Non-Lease) | Posting Date Type | Posting Date | Document Date | Asset Class Filter | Internal Asset Class | CPI Category Filter | CPI Categories | Open Drafts | Entity Type      |
      | 1000       | Lessee             | User Defined           | 2023-01-01        | CPI Local               | Rate                     | 95                      | null                        | null                   | Enforce Non-Lease         | Modification Date            | null                    | null                        | null                         | null                        | null                            | null                       | null                             | null                        | Modification Date | null         | null          | All                | null                 | All                 | null           | Yes         | Activation Group |
    Then the "Batch" job should be completed with status "Done"
      #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #    #--------------------Opens the Draft Event--------------------------
    Then User Click on Activation Group "Mass Indexation By Task" Level
    #--------------------Activation Group Indexation Event-------------------------- without Batch
    #Then User tries to create AG Event at "AG Event-2" Level
    #Then User enters the Accounting tab of Activation Group
    #Then User enters the "AG Event-2" Consumer Price Index Values of Activation Group
    #And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    #And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    #And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    #And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    #And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "Indexation" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-IN" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-IN" level
#     #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-05-31"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-01-01" to "2023-05-31"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-05-31"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-01-01" to "2023-05-31"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event2-IN" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event2-IN" level
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-3" Level
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "1,2"
    Then User index the Term and Condition number "1,2"
    Then User makes the Rou End Date Change To "2024-06-30"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event3-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event3-LM" level
#     #--------------------Schedule Posting--------------------------
#    Then User clicks on "IAS" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2024-06-30"
#    Then User clicks on "GAAP" Standard to "Post" the "Accrual & Depreciation" of "AllColumns" Schedules from "2023-06-01" to "2024-06-30"
    #--------------------Schedule Posting--------------------------
    Then User clicks on "IAS" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-06-01" to "2024-06-30"
    Then User clicks on "GAAP" Standard to "Post" the "Payment" of "Liability" Schedules from "2023-06-01" to "2024-06-30"
    #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event3-LM" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event3-LM" level
#    #--------------------Lease End & Close WorkFlow State-------------------------
#    Then User sends the "Activation Group" for "ag-lease-end-btn" workflow transition
#    Then User sends the "Activation Group" for "ag-close-btn" workflow transition
#    #--------------------All Column Schedule Validation After Close--------------------------
#    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "AG-Close" level
#    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "AG-Close" level

    Examples: 
      | testCaseNumber |
      | TC-02          |
