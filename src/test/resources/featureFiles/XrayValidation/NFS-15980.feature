Feature: NFS-15980 Test Case
  NFS-15980:Verify ERP Field Mapping in NFS for code coverage

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify ERP Field Mapping in NFS for code coverage
  #Jira_ID:NFS-15980
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:N2025.R1
  @Regression @Xray
  Scenario Outline: NFS-15980 Test Case
    Then Open Hamburger menu & Click On "Admin"-->"ERP Field Mapping"-->""
    Then User tries to delete the added ERP Field Mapping entry
    Then User tries to create an ERP Field Mapping entry for "Lessee"
      | NLA Table                       | NLA Field                       |
      | Master Agreement                | Master Agreement Display ID     |
      | Contract                        | Indexed Currency                |
      | Contract Accounting             | Fiscal Variant                  |
    Then User tries to create an ERP Field Mapping entry for "Lessor"
      | NLA Table                       | NLA Field                       |
      | Master Agreement                | Master Agreement Display ID     |
      | Contract                        | Indexed Currency                |
      | Contract Accounting             | Fiscal Variant                  |
    Then User validates filters
    Then User tries to delete the added ERP Field Mapping entry

    Examples: 
      | testCaseNumber  |
      | NFS-15980       |
