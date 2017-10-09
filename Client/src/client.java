import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String IPAddress = "localhost";
        Socket requestSocket = null;
        try {
            requestSocket = new Socket(IPAddress, 7896);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream in = requestSocket.getInputStream();
        OutputStream out = new FileOutputStream("aangekomenLangsServer.zip");

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            try {
                out.write(buffer, 0, bytesRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in.close();
            out.close();
            requestSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}