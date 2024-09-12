package SocketPackage;
import java.io.*;

class SendMSG extends Thread {
  BufferedReader br;
  DataOutputStream dout;
  String My_message, My_name,remote_name;
  TerminalSize ts;
  SendMSG(BufferedReader br, DataOutputStream dout, String My_message, String My_name,String remote_name) {
    this.My_message = My_message;
    this.My_name = My_name;
    this.br = br;
    this.dout = dout;
    this.start();
    this.ts = new TerminalSize();
    this.remote_name = remote_name;
  }

  public void run() {
    try {
      while (true) {
        this.My_message = this.br.readLine();
        Chat.addMessage("\n"+this.My_message+"\n");
        this.dout.writeUTF(My_message);
        this.dout.flush();
        ts.updateScreen(this.remote_name);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
