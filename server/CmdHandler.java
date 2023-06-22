package server;

import java.util.Scanner;

public class CmdHandler extends Thread {
    //accept input from server console
    private SocketManager sckManager;

    public CmdHandler(SocketManager socketManager){
        sckManager = socketManager;

    }

    @Override
    public void run() {
        
        while(true){
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        if(cmd.equals("exit")){
            sckManager.sendToAll("SERVER is going to turn off");
            sckManager.disconnectSocket();
            System.exit(1);
            break;
        }

        }

    }
    
}
