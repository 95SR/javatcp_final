package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    //fields
    private static final int PORT = 3434;

    //method
    public void startServer(int port, SocketManager socketManager){
        try {
             ServerSocket serverSocket = new ServerSocket(port);
             CmdHandler cmd = new CmdHandler(socketManager);
             cmd.start();

            while(true){
                 System.out.println("Accepting client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client entered");

            ClientHandler clientThread = new ClientHandler(clientSocket, socketManager);
            clientThread.start();

            socketManager.addSocket(clientThread);

            }
           
           



            //thread에 전달

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        SocketManager socketManager = new SocketManager();
        mainServer.startServer(PORT, socketManager);

        
    }
}
