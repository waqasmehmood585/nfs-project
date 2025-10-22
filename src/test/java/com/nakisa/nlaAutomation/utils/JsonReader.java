package com.nakisa.nlaAutomation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;

import java.io.File;
import java.io.IOException;

public class JsonReader {


  public static void csvTestData(String testCaseId) {
    // JSON file path or JSON string
    String jsonFilePath = "src/test/resources/testDetail/testCaseDetail.json"; // Update with your file path

    try {
      // Parse JSON file into JsonNode
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

      JsonNode testCaseNode = rootNode.get(testCaseId);

      if (testCaseNode != null) {
        MasterHooks.title.set(testCaseNode.get("TC_Title").asText());
        MasterHooks.tcID.set(testCaseNode.get("Jira_ID").asText());
        MasterHooks.tcCategory.set(testCaseNode.get("TC_Category").asText());
        MasterHooks.customers.set(testCaseNode.get("TC_Customers").asText());
        MasterHooks.fixVersion.set(testCaseNode.get("TC_FixVersion").asText());
      } else {
        System.out.println("TestCase ID " + testCaseId + " not found.");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
