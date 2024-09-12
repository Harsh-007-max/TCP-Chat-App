package SocketPackage;

import java.net.*;
import java.io.*;
import java.util.Scanner;

class MyClient {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Socket s;
    try {
      System.out.println("Enter IP Address of server: ");
      String remote_ip = sc.nextLine();
      System.out.println("Enter server Port: ");
      int remote_port = sc.nextInt();
      s = new Socket(remote_ip, remote_port);
      DataInputStream din = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String My_message = "", Remote_name = "", My_name = "";
      System.out.print("Enter name: ");
      while (Remote_name.equals("")) {
        My_name = sc.nextLine();
        if (!My_name.equals("")) {
          dout.writeUTF(My_name);
          Remote_name = din.readUTF();
          if (!Remote_name.equals("")) {
            System.out.println("Received Name: " + Remote_name);
          }
        }
      }
      System.out.println("My name is: " + My_name);
      SendMSG sendThread = new SendMSG(br, dout, My_message, My_name,Remote_name);
      ReceiveMSG receiveThread = new ReceiveMSG(din, Remote_name);
      sendThread.join();
      receiveThread.join();
      sc.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
