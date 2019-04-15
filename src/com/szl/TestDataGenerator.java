package com.szl;

import static com.szl.ReadFromFile.readFromFile;

import java.io.BufferedReader;
import java.io.StringReader;

public class TestDataGenerator {

  public static void main(String[] args) throws Exception {

    System.out.println(generatedTestMethod("getTimeSheetDailyHoursListPropSet", "timeSheetDailyHoursList"));
  }

  private static StringBuilder generatedTestMethod(String methodName, String propSetName) throws Exception{
    StringBuilder method = new StringBuilder();

    method.append("private SiebelPropertySet ").append(methodName).append("() {")
        .append(System.getProperty("line.separator"));
    method.append("\tfinal SiebelPropertySet ").append(propSetName).append(" = new SiebelPropertySet();")
        .append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
    method.append(generatePropertySetters(propSetName)).append(System.getProperty("line.separator"));
    method.append("\treturn ").append(propSetName).append(";").append(System.getProperty("line.separator"));
    method.append("}");

    return method;
  }

  private static StringBuilder generatePropertySetters(String propSetName) throws Exception {
    StringBuilder content = readFromFile();
    BufferedReader bufReader = new BufferedReader(new StringReader(content.toString()));
    StringBuilder result = new StringBuilder();
    String line;

    while ((line = bufReader.readLine()) != null) {
      line = line.replaceAll("(\\.set)[^&]*(Property)", ".setProperty");
      line = line.replaceAll("()[^&]*(\\.)", "\t\t" + propSetName + ".");
      String value = "test " + line.split("\"|'")[1];
      line = line.replaceAll("\"\\).*\\)", "\", \"" + value + "\")");
      result.append(line).append(System.getProperty("line.separator"));
    }
    return result;
  }


}
