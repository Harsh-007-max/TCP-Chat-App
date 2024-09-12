package SocketPackage;
import java.io.*;
import java.net.*;
import java.util.*;

public class MyServer {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    try {
      ServerSocket ss = new ServerSocket(1209);
      Socket s = ss.accept();
      DataInputStream din = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String  My_message = "", My_name = "", Remote_name = "";
      System.out.println("Enter name: ");
      My_name = sc.nextLine();
      dout.writeUTF(My_name);
      System.out.println("Send name is: "+My_name);
      while (Remote_name.equals("")) {
        if (!My_name.equals("")) {
          Remote_name = din.readUTF();
          if (!Remote_name.equals("")) {
            System.out.println("Remote Name: " + Remote_name);
          }
        }
      }
      System.out.println("Name loop ends here");
      SendMSG sendThread = new SendMSG(br, dout, My_message, My_name,Remote_name);
      ReceiveMSG receiveThread = new ReceiveMSG(din, Remote_name);
      sendThread.join();
      receiveThread.join();
      while(true){
        if(!(sendThread.isAlive() && receiveThread.isAlive())){
          break;
        }
        Thread.sleep(100);
      }
      ss.close();
      sc.close();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
    }
  }
}
