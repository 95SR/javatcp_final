package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {
    private final int PORT = 3434;
    private InetAddress ia;
    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter pr;

    public void startClient(int PORT, InetAddress ia){
        try {
            clientSocket = new Socket(ia, PORT);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           pr = new PrintWriter(clientSocket.getOutputStream(), true);

            RequestHandler reqThread = new RequestHandler(pr);
            reqThread.start();

            RespondHandler resThread = new RespondHandler(br);
            resThread.start();

            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 


    }
    public static void main(String[] args) {
        MainClient mainClient = new MainClient();

        try {
            mainClient.ia = InetAddress.getLocalHost();

             mainClient.startClient(mainClient.PORT, mainClient.ia);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       

        
    }
}
