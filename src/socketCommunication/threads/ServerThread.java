/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 *
 * @author veljko
 */
public class ServerThread extends Thread {
    
    private ServerSocket serverSocket;
    private Logger LOGGER = Logger.getLogger(ServerThread.class);
    
    public ServerThread() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException ex) {
            LOGGER.fatal(ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
                LOGGER.info("New client connected.");
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }            
        }
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
}
