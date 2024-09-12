package SocketPackage;
import java.io.*;

class ReceiveMSG extends Thread {
  DataInputStream din;
  String Remote_name,Remote_message;
  TerminalSize ts;
  int TerminalWidth;
  ReceiveMSG(DataInputStream din,String Remote_name) {
    this.din = din;
    this.Remote_name = Remote_name;
    this.start();
    ts = new TerminalSize();
  }

  public void run(){
    try{
    while(true){
        this.Remote_message = this.din.readUTF();
        TerminalWidth = ts.GetTerminalWidth();
        TerminalWidth-=this.Remote_message.length();
        String Space = "";
        for(int i =0;i<TerminalWidth;i++){
          Space+=" ";
        }
        String message = "\n"+Space+this.Remote_message+"\n";
        Chat.addMessage(message);
        ts.updateScreen(Remote_name);
    }
    }catch(Exception e){
      System.out.println(Remote_name+" left the chat");
      System.exit(0);
    }
  }
}
