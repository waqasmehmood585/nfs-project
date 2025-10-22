Feature: NFS-8896 Test Case
  Verify Report for Disclosure Test Case TC-DR-02

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify Report for Disclosure Test Case TC-DR-02
  #Jira_ID:NFS-8896
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
#  @Regression @Xray @DR
  Scenario Outline: NFS-8896 Test Case
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
    Then User exercise the Term and Condition number "1,2"
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
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Activation Group Terms & Conditions Reassessment Event--------------------------
    Then User tries to create AG Event at "AG Event-1" Level
    And User makes the Rou End Date Change To "2023-06-30"
    Then User enters the Terms and Conditions tab of Activation Group
    Then User exercise the Term and Condition number "2"
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------Journal Document Validation--------------------------
    Then User clicks on "IAS" standard to validate "LeaseModification" documents
    Then User clicks on "GAAP" standard to validate "LeaseModification" documents
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event1-LM" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event1-LM" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event1-LM" level
    #--------------------Search With ID--------------------------
    Then User Search "Activation Group" with ID
    #--------------------Activation Group Casualty Event--------------------------
    Then User tries to create AG Event at "AG Event-2" Level
    Then User enters the Terms and Conditions tab of Activation Group
    And User sends the "Activation Group" for "ag-generate-schedules-btn" workflow transition
    And User sends the "Activation Group" for "ag-send-to-assessment-btn" workflow transition
    And User sends the "Activation Group" for "ag-approve-btn" workflow transition
    And User sends the "Activation Group" for "ag-confirm-classification-btn" workflow transition
    And User sends the "Activation Group" for "ag-activate-btn" workflow transition
    #--------------------All Column Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AllColumns" schedule view at "Event2-CA" level
    And User clicks on "GAAP" standard to validate "AllColumns" schedule view at "Event2-CA" level
    #--------------------Liability Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "Liability" schedule view at "Event2-CA" level
    And User clicks on "GAAP" standard to validate "Liability" schedule view at "Event2-CA" level
     #--------------------Asset Transition Schedule Validation--------------------------
    And User clicks on "IAS" standard to validate "AssetTransition" schedule view at "Event2-CA" level
    And User clicks on "GAAP" standard to validate "AssetTransition" schedule view at "Event2-CA" level
#    #--------------------Disclosure Reports Profile & Job--------------------------
#    Then Open Hamburger menu & Click On "Reporting Module"-->""-->"Disclosure Jobs"
#    Then User create Disclosure Report "Job" for "Disclosure Report-1"
#    And User Download and Validate "IAS" standard "Asset Roll Forward" Report
#    And User Download and Validate "IAS" standard "Cash Flow" Report
#    And User Download and Validate "IAS" standard "Expense" Report
#    And User Download and Validate "IAS" standard "Lease Liability" Report
#    And User Download and Validate "IAS" standard "Maturity Analysis" Report
#    #And User Download and Validate "IAS" standard "Non Lease Charge Expense" Report
#    And User Download and Validate "IAS" standard "Weighted Avg Discount Rate" Report
#    And User Download and Validate "IAS" standard "Weighted Avg Lease Term" Report
#     #--------------------Disclosure Reports Profile & Job--------------------------
#    Then Open Hamburger menu & Click On "Reporting Module"-->""-->"Disclosure Profiles"
#    Then User create "Disclosure Report-2" profile for "Disclosure Reports"
#    Then Open Hamburger menu & Click On "Reporting Module"-->""-->"Disclosure Jobs"
#    Then User create Disclosure Report "Job" for "Disclosure Report-2"
#    And User Download and Validate "GAAP" standard "Asset Roll Forward" Report
#    And User Download and Validate "GAAP" standard "Cash Flow" Report
#    And User Download and Validate "GAAP" standard "Expense" Report
#    And User Download and Validate "GAAP" standard "Lease Liability" Report
#    And User Download and Validate "GAAP" standard "Maturity Analysis" Report
#    #And User Download and Validate "GAAP" standard "Non Lease Charge Expense" Report
#    And User Download and Validate "GAAP" standard "Weighted Avg Discount Rate" Report
#    And User Download and Validate "GAAP" standard "Weighted Avg Lease Term" Report


    Examples:
      | testCaseNumber |
      | NFS-8896       |
