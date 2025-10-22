Feature: NFS-6595 Test Case
  NFS-6595: Verify that user is able to create a Custom Dashboard and it is visible in Hamburger menu

  Background: User needs to login into the application before performing the test case
    Given User logins into the NCP cockpit with correct credentials
    Then User opens the "nakisa-financial-suite" application url

  #TC_Title:Verify that user is able to create a Custom Dashboard and it is visible in Hamburger menu
  #Jira_ID:NFS-6595
  #TC_Category:Regression
  #TC_Customers:None
  #TC_FixVersion:Nakisa 2023.R4 & Earlier
#  @Regression @Xray @Core-Functionality
  Scenario: NFS-6595 Test Case
    Given User opens the Dashboards from Hamburger Menu
    And User creates a new Dashboard Page
      | Title  | Description | Dashboard Type | Remove Description | Enable/Disable list | Dashboard Data           |
      | Nakisa | Dash-1      | Dashboard      | No                 | Yes                 | Master Agreement Dataset |
    Then User creates a new "Pie" Chart
      | Title     | Title Size | Subtitle | Subtitle Size | Alignment | Multiple Axis Checkbox | Add Axis | Axis Name   | Axis Field                    | Operation Field | Operation | Add Filter | Filter Field         | Field Value |
      | Pie Chart |         15 | Pie      |            11 | Left      | Yes                    | Yes      | Axis1,Axis2 | Company Code,Business Unit | Created By      | Count     | Yes        | Status,Currency Code | Active,CAD  |
    Then User creates a new "Line" Chart
      | Title      | Title Size | Subtitle | Subtitle Size | Alignment | Axis Name | Axis Field    | Stack Chart Checkbox | Stack Name | Stack Field | Operation Field | Operation | Add Filter | Filter Field | Field Value |
      | Line Chart |         15 | Line     |            11 | Right     | Axis-Line | Currency Code | Yes                  | Stack1     | Created By  | Business Unit   | Count     | No         | Status       | Define      |
    Then User creates a new "Bar" Chart
      | Title     | Title Size | Subtitle | Subtitle Size | Alignment | Axis Name | Axis Field | Stack Chart Checkbox | Stack Name | Stack Field     | Operation Field | Operation      | Add Filter | Filter Field  | Field Value |
      | Bar Chart |         14 | Bar      |            10 | Left      | Axis-Bar  | Created By | No                   | Stack1     | Company Code | Currency Code   | Count Distinct | No         | Business Unit | BU0001      |
    Then User creates a new "Circular Bar" Chart
      | Title              | Title Size | Subtitle     | Subtitle Size | Alignment | Axis Name        | Axis Field | Stack Chart Checkbox | Stack Name | Stack Field | Operation Field | Operation      | Add Filter | Filter Field                | Field Value |
      | Circular Bar Chart |         14 | Circular Bar |            10 | Right     | Axis-CircularBar | Created By | Yes                  | Stack1     | Status      | Currency Code   | Count Distinct | Yes        | Business Unit,Currency Code | BU0001,CAD  |
    Then User creates a new "Bubble" Chart
      | Title        | Title Size | Subtitle | Subtitle Size | Alignment | Main Axis Name | Main Axis Field | Second Axis Name | Second Axis Field | Operation Field | Operation | Add Filter | Filter Field | Field Value |
      | Bubble Chart |         14 | Bubble   |            10 | Right     | Axis-Bubble    | Currency Code   | Axis-Bubble-2nd  | Company Code   | Business Unit   | Count     | No         | Status       | Active      |
    Then User creates a new "Circular Bubble" Chart
      | Title                 | Title Size | Subtitle        | Subtitle Size | Alignment | Main Axis Name | Main Axis Field | Second Axis Name | Second Axis Field | Operation Field | Operation | Add Filter | Filter Field    | Field Value |
      | Circular Bubble Chart |         14 | Circular Bubble |            10 | Left      | Axis-Bubble    | Business Unit   | Axis-Bubble-2nd  | Currency Code     | Status          | Count     | No         | Company Code |        1000 |
    Then User creates a new "Square" Chart
      | Title        | Title Size | Subtitle | Subtitle Size | Alignment | Main Axis Name | Main Axis Field | Second Axis Name | Second Axis Field | Operation Field | Operation | Add Filter | Filter Field    | Field Value |
      | Square Chart |         14 | Square   |            10 | Left      | Axis-Square    | Business Unit   | Axis-Square-2nd  | Currency Code     | Status          | Count     | No         | Company Code |        1000 |
    Then User creates a new "Radar" Chart
      | Title       | Title Size | Subtitle | Subtitle Size | Alignment | Axis Name  | Axis Field          | Add Operation | Operation Field        | Operation            | Add Filter | Filter Field     | Field Value  |
      | Radar Chart |         14 | Radar    |            10 | Left      | Axis-Radar | Master Agreement ID | Yes           | Company Code,Status    | Count Distinct,Count | No         | Currency Code    | CAD          |
    Then User creates a new "Parallel" Chart
      | Title          | Title Size | Subtitle | Subtitle Size | Alignment | Axis Name     | Axis Field    | Add Operation | Operation Field        | Operation   | Add Filter | Filter Field  | Field Value |
      | Parallel Chart |         16 | Parallel |            13 | Center    | Axis-Parallel | Currency Code | Yes           | Status,Company Code | Count,Count | No         | Business Unit | BU0001      |
    Then User creates a new "Sunburst" Chart
      | Title          | Title Size | Subtitle | Subtitle Size | Alignment | Multiple Axis Checkbox | Add Axis | Axis Name   | Axis Field                    | Operation Field | Operation | Add Filter | Filter Field         | Field Value |
      | Sunburst Chart | null       | Sunburst | null          | Left      | Yes                    | Yes      | Axis1,Axis2 | Company Code,Business Unit | Created By      | Count     | Yes        | Status,Currency Code | Active,CAD  |
    #    Then User creates a new "Theme River" Chart
    #      | Title             | Title Size | Subtitle       | Subtitle Size | Alignment | Time Axis Name  |Time Axis Field    |  Value Axis Name   | Value Axis Field  | Operation Field | Operation  | Add Filter | Filter Field    | Field Value |
    #      | Theme River Chart |         14 | Theme River    |            10 | Left      | Axis-Time       | Business Unit      | Axis-Value-2nd    | Currency Code      | Status          | Count      | No         | Company Code | 1000        |
    Then User creates a new "Map" Chart
      | Title     | Title Size | Subtitle | Subtitle Size | Alignment | Geo Point | Multiple Axis Checkbox | Add Axis | Axis Name   | Axis Field                    | Add Operation | Operation Field | Operation      | Add Filter | Filter Field | Field Value                  |
      | Map Chart |         14 | Map      |            10 | Left      | Axis      | Yes                    | Yes      | Axis1,Axis2 | Company Code,Business Unit | No            | Currency Code   | Count Distinct | No         | Status   | Active |
    Then User creates a new "Info Card" Chart
      | Title           | Title Size | Subtitle  | Subtitle Size | Alignment | Content                    | Add Operation | Operation Field | Operation      | Add Filter | Filter Field | Field Value       |
      | Info Card Chart | null       | Info Card | null          | Right     | This is an Info Card Chart | No            | Currency Code   | Count Distinct | No         | Company Code   | 1000 |
    Then User creates a new "Metric" Chart
      | Title           | Title Size | Subtitle  | Subtitle Size | Alignment | Description                | Percentage Checkbox | Operation Field      | Operation            | Complement Checkbox | Add Filter | Filter Field | Field Value                  |
      | Info Card Chart | null       | Info Card | null          | Right     | This is an Info Card Chart | Yes                 | Currency Code,Status | Count Distinct,Count | Yes                 | Yes        | Business Unit   | BU0001 |
    Then User creates a new "Pivot Table" Chart
      | Title             | Title Size | Subtitle    | Subtitle Size | Alignment | Data Size | Data Name            | Data Field           | Column Size | Column Name                   | Column Field                  | Add Values | Values Name     | Values Field | Values Operation | Add Filter | Filter Field | Field Value      |
      | Pivot Table Chart | null       | Pivot Table | null          | Right     |         2 | Business Unit,Status | Business Unit,Status |           2 | Currency Code,Company Code | Currency Code,Company Code | Yes        | Company Code | Created By   | Count            | No         | Status   | Active     |
    Then User creates a new "Pivot Table Row" Chart
      | Title                 | Title Size | Subtitle        | Subtitle Size | Alignment | Data Size | Data Name            | Data Field           | Add Filter | Filter Field | Field Value   |
      | Pivot Table Row Chart | null       | Pivot Table Row | null          | Right     |         2 | Business Unit,Status | Business Unit,Status | No         | Company Code   | 1000 |
    Then User deletes the Dashboard Page
