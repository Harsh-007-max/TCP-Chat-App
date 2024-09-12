package SocketPackage;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Chat {
  public static final List<String> messageList= Collections.synchronizedList(new ArrayList<String>());
  public static void addMessage(String message){
    synchronized(messageList){
      messageList.add(message);
    }
  }
  public static List<String> getMessageList(){
    return new ArrayList<String>(messageList);
  }

}
