import java.io.*;
import java.net.*;

public class ConnectionThread extends Thread {
    Socket connection;

    public ConnectionThread(Socket socket) {
        this.connection = socket;
        this.start();
    }

    public void run() {
        try {
            System.out.println("[CONNECTION ESTABLISHED]");
            File file = new File("test.jpg");
            InputStream in = new FileInputStream(file);
            OutputStream out = connection.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            System.out.println("[TRANSFERING FILE]");
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                System.out.print(".");
            }
            System.out.println("[FILE SENT SUCCESFULLY]");
            System.out.println("TERMINATING CONNECTION ... " + "\n\n");
            out.close();
            in.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
