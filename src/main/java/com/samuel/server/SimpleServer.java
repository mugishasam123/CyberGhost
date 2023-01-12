package main.java.com.samuel.server;


import java.io.IOException;
import java.net.ServerSocket;




public class SimpleServer {

   
    public static void main(String[] args) throws IOException {


        try {
            try (ServerSocket serverSocket = new ServerSocket(8443)) {
                boolean listening = true;

                   while(listening){
                    new ServerThread(serverSocket.accept()).start();
                   }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
