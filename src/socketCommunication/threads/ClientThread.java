/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import socketCommunication.Service;
import transfer.RequestObject;
import transfer.ResponseObject;

/**
 *
 * @author veljko
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final Logger LOGGER = Logger.getLogger(ClientThread.class);

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                RequestObject requestObject
                        = (RequestObject) objectInputStream.readObject();
                ResponseObject responseObject = executeRequest(requestObject);
                objectOutputStream.writeObject(responseObject);
            } catch (Exception ex) {
                LOGGER.error(ex);
                return;
            }
        }
    }
    
    public ResponseObject executeRequest(RequestObject requestObject) throws Exception {
        try {
            String operation = (String) requestObject.getOperation();
            Service service
                    = (Service) Class.forName("socketCommunication." + operation).getConstructor().newInstance();
            return service.execute(requestObject);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

}
