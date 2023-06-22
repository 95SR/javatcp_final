package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class RequestHandler extends Thread{
    private PrintWriter pr;
   

    public RequestHandler(PrintWriter printWriter){
       
            this.pr = printWriter;
       
    }

    @Override
    public void run() {
        String toServer;
        while(true){
        Scanner scanner = new Scanner(System.in);
        toServer = scanner.nextLine();
        pr.println(toServer);
        if(toServer.equals("bye")){
            scanner.close();
            break;
        }


        }

       
    }

}

class RespondHandler extends Thread{
    private BufferedReader br;
    

    public RespondHandler(BufferedReader bufferedReader){
        br = bufferedReader;
    }

    @Override
    public void run() {
        String fromServer;
        System.out.println("trying to run");
        System.out.println("Enter username: ");
        try {
            while(true){
               
            fromServer= br.readLine();
           
            if(fromServer == null){
                System.exit(1);
                break;
            }
            
            System.out.println(fromServer);

            
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}