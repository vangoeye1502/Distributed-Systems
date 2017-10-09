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
            File file = new File("test.zip");
            InputStream in = new FileInputStream(file);
            OutputStream out = connection.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            in.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
