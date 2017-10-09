import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(7899);

            while(true) {
                System.out.println("Waiting ...");
                Socket openedConnection = socket.accept();
                ConnectionThread temp = new ConnectionThread(openedConnection);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
