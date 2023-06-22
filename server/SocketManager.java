package server;

import java.io.IOException;
import java.util.ArrayList;

public class SocketManager {
    //socket 관리, 추가 제거 메시지 보내기

    //fields
    private ArrayList<ClientHandler> clientSockets = new ArrayList<ClientHandler>();

    //method
    public void addSocket(ClientHandler thread){
        clientSockets.add(thread);

    }

    public void removeSocket(ClientHandler thread){
        
        clientSockets.remove(thread);
        sendToAll(thread.userName + " left the chat");
    }

    public void sendMessage(ClientHandler thread, String message){
       
        for (ClientHandler client : clientSockets) {
            if(!thread.equals(client)){
                client.pr.println(client.userName + ": " + message);
            }
        }

    }

    public void sendToAll(String message){
        
        for (ClientHandler client : clientSockets) {
            client.pr.println("SERVER: " + message);
        }

    }

    public void disconnectSocket(){
        for (ClientHandler client : clientSockets) {
            try {
                client.socket.close();
                client.pr.close();
            client.br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }



}
