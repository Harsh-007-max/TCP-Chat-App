package SocketPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminalSize {
  public int GetTerminalWidth() {
    try {
      ProcessBuilder colBuilder = new ProcessBuilder("zsh", "-c", "echo $COLUMNS");
      Process colProcess = colBuilder.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(colProcess.getInputStream()));
      int width = Integer.parseInt(reader.readLine());

      return width;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 100;
  }

  public static int GetTerminalHeight() {
    try {
      ProcessBuilder rowBuilder = new ProcessBuilder("zsh", "-c", "tput lines");
      Process rowProcess = rowBuilder.start();
      BufferedReader readerLines = new BufferedReader(new InputStreamReader(rowProcess.getInputStream()));
      int height = Integer.parseInt(readerLines.readLine());
      return height;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 100;
  }

  public void clearScreen() {
    try {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void updateScreen(String name) {
    clearScreen();
    System.out.println(name);
    int lineCount=3;
    for (int i = GetTerminalWidth(); i > 0; i--)
      System.out.print("_");
    for (String message : Chat.getMessageList()) {
      System.out.print(message);
      lineCount++;
    }
    for(int i=0;i<(GetTerminalHeight()-lineCount);i++){
      System.out.println("\n");
    }
    System.out.print("+");
    for (int i = GetTerminalWidth()-2; i > 0; i--)
      System.out.print("-");
    System.out.print("+");
  }

}
