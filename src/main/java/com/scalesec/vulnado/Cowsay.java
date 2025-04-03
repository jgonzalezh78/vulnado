package com.scalesec.vulnado;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
private Cowsay() {}

  private Cowsay() {}
public class Cowsay {
  public static String run(String input) {
Logger logger = Logger.getLogger(Cowsay.class.getName());
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay \"" + input.replaceAll("\"", "\\\"") + "\"";
    Logger logger = Logger.getLogger(Cowsay.class.getName());
    processBuilder.command("bash", "-c", cmd.trim());

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "An error occurred while executing the command", e);
    }
    return output.toString();
  }
}
