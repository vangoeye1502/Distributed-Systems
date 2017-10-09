import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(7896);

            while(true) {
                Socket connectionSocket = socket.accept();
                ConnectionThread temp = new ConnectionThread(connectionSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
