Feature: NFS-6560 Test Case
  NFS-6560: SAP Sync Job: schedule sync jobs-Verify user should be able to schedule Sync Job to run Hourly

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "sap-sync-bot" application url

  #TC_Title:Verify user should be able to schedule Sync Job
  #Jira_ID:NFS-6560
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4
  @Regression @Xrays @SapSyncBot
  Scenario Outline: NFS-6560 Test Case
    #--------------------Reading Test Case Excel--------------------------
    Given Reading test case inputs from excel file "<testCaseNumber>"
   #Then Open Hamburger menu & Click On "Sync Profiles"-->""-->""
    Then Verify user is able to create sap sync profile with settings and name "<testCaseNumber>"
      |Override Default Language  |Select All  | Override Partner Table Name  |
      |English                    |   Yes      |  Partner                     |
   # Then Open Hamburger menu & Click On "Scheduled Jobs"-->""-->""
    Then User creates a SAP Sync schedule Job with details
     |Global Sync Profile |Schedule Option | Hour        | Date    | Week Days | Month | Months Name |
     | NFS-6560           |   Hourly       |    2        | null    | null     | null   | null        |
    Then User click on "Disable" button to disable the scheduled job
    Then User delete the Sap sync schedule job


    Examples:
      | testCaseNumber |
      | NFS-6560       |
