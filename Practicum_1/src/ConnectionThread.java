import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ConnectionThread extends Thread {
    Socket socket;

    public ConnectionThread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    public void run() {
        try {
            BufferedReader inputVanClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outputNaarClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
