Feature: NFS-5177 Test Case
  NFS-5177 - Verify that user is able to create, edit and delete the Batch Profiles

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "user-auxiliary" application url

  #TC_Title:Verify that user is able to create, edit and delete the Batch Profiles
  #Jira_ID:NFS-5177
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Regression @Xray @Core-Functionality
  Scenario Outline: NFS-5177 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
    #--------------------Create Batch Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Batch Job Profiles"-->""
    Then Users create Profile for "Batch"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS | Profit Center | Functional Area | Business Area |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | All        | All           | All     | All              | All         | All         | All | All           | All             | All           |
     #--------------------Edit Batch Profile --------------------------
    Then Users edit the profile for "Batch"
      | Name | Principal Position | Erp System Filter | ERP Systems | Lease Area | Business Unit | Company | Lease Department | Lease Group | Cost Center | WBS                     | Profit Center | Functional Area | Business Area |
      | null | null               | null              | null        | null       | null          | null    | null             | null        | null        | BRANCH -10 - Category A | null          | null            | null          |
     #-------------------Delete Batch Profile --------------------------
    Then User deletes the "Batch" Profile
    #--------------------Create Report Profile --------------------------
    Then Open Hamburger menu & Click On ""-->"Report Profiles"-->""
    Then Users create Profile for "Report"
      | Name             | Principal Position | Erp System Filter | ERP Systems | Calendar Type    | Fiscal Variant | Lease Area | Business Unit | Company                | Lease Department | Lease Group | Cost Center | WBS | Profit Center |
      | <testCaseNumber> | Lessee             | List              | FINQ8S-300  | Regular Calendar | null           | All        | All           | FINQ8S-300 - CA - 1000 | All              | All         | All         | All | All           |
       #--------------------Edit Report Profile --------------------------
    Then Users edit the profile for "Report"
      | Name | Principal Position | Erp System Filter | ERP Systems | Calendar Type | Fiscal Variant | Lease Area | Business Unit    | Company     | Lease Department | Lease Group             | Cost Center   | WBS                     | Profit Center |
      | null | null               | null              | null        | null          | null           | null       | null             | null        | null             | null                    | null          | BRANCH -10 - Category A | null          |
     #--------------------Delete Report Profile --------------------------
    Then User deletes the "Report" Profile

    Examples:
      | testCaseNumber |
      | NFS-5177       |
