Feature: SmokeTC002 Test Case
  NFS-10857 SmokeTC # 02: Contract Event (With GVI)

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:SmokeTC002 - Contract Event (With GVI)
  #Jira_ID:NFS-10857
  #TC_Category:Smoke
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
  @Smoke @lessee @NFSSmoke-P1
  Scenario Outline: SmokeTC002 Test Case
    #--------------------Reading Test Case Excel-------------------------- #GVI
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
    Then User deletes the Partner "4" at "Contract Inception" level
    Then User fills the Accounting Tab of Contract
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Contract Event Level Add New Partners--------------------------
    Then User adds a New Contract Event "Add Partners"
    Then User selects partner role and partner under Contract Partner page at "Contract Event-1" Level
#    Then User verify that for GVI Contract User should not be able to Add "Lessor" Partners
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Contract Event Level Replace Partners--------------------------
    Then User adds a New Contract Event "Replace Partners"
    Then User Replace Partner "1" to "Canadian Manager" with Replacement Date "2023-08-24"
    Then User Replace Partner "4" to "TEST VENDOR NY" with Replacement Date "2023-08-24"
#    Then User verify that for GVI Contract User should not be able to Replace "Lessor" Partners
    Then User sends the "Contract" for "contract-send-to-approval-btn" workflow transition
    Then User sends the "Contract" for "contract-approve-btn" workflow transition
    #--------------------Add Contact at Contract Partners--------------------------
    Then User add Contact on Contract Partners
      | Partner Number | Name  | Position | Email       | Phone | Address  | Description    |
      |              1 | User1 | SQA      | @nakisa.com |  9999 | Montreal | No Description |
      |              1 | User2 | SQA      | @nakisa.com |  9229 | Montreal | No Description |
      |              3 | User3 | SSQA     | @nakisa.com |  1111 | Montreal | No Description |
      |              5 | User4 | Dev      | @nakisa.com |  2121 | Toronto  | No Description |
    #--------------------Edit Contact at Contract Partners--------------------------
    Then User edit Partner contact details
      | Partner Number | Name  | Position | Email       | Phone | Address   | Description |
      |              1 | User5 | SQA5     | null        |  5555 | Montreal5 | null        |
      |              5 | null  | null     | @nakisa.com |  6666 | Toronto6  | null        |
    #--------------------Delete Contact at Contract Partners--------------------------
    Then User delete the contact "1" from the Partner "1"

    Examples: 
      | testCaseNumber |
      | SmokeTC002     |
