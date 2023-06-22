package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    // fields
    public BufferedReader br;
    public PrintWriter pr;
    public String userName;
    public Socket socket;
    private SocketManager scManager;

    // constructor
    public ClientHandler(Socket clientSocket, SocketManager socketManager) {
        socket = clientSocket;
        scManager = socketManager;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pr = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // run
    @Override
    public void run() {
        String fromCLient;
        System.out.println("enter run");

        try {
            // get the userName
            System.out.println("waiting for username");
            userName = br.readLine();
            System.out.println(userName);
            while (true) {
                fromCLient = br.readLine();
                System.out.println(fromCLient);
                if (fromCLient.equals("bye")) {
                    
                    scManager.removeSocket(this);
                    break;
                }
                scManager.sendMessage(this, fromCLient);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {


            try {
                socket.close();
                pr.close();
                br.close();
                //retun null to client
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
