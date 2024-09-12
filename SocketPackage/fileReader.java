package SocketPackage;

public class fileReader {
  public static void readSlangs(){

  }
  public static void writerSlang(){
  }
  public static void handleSlangCommand(String command){
    String[] commandArgs= command.split(" ");
    String slangName = commandArgs[0];
    String slangMeaning = commandArgs[0];
    addNewSlang(slangName,slangMeaning);
  }
  private static void addNewSlang(String slangName,String slangMeaning){

  }
  
}
