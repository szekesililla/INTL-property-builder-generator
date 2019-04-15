package com.szl;

import java.io.*;

public class ReadFromFile {

  public static StringBuilder readFromFile() throws Exception{
    StringBuilder content = new StringBuilder();

    try(BufferedReader br = new BufferedReader(new FileReader("text"))) {
      String line = br.readLine();

      while (line != null) {
        content.append(line);
        content.append(System.lineSeparator());
        line = br.readLine();
      }
    }
    return content;
  }
}
