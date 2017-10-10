import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String IPAddress = "localhost";
        Socket requestSocket = null;
        try {
            System.out.println("CONNECTING ... ");
            requestSocket = new Socket(IPAddress, 7896);
            System.out.println("[CONNECTION ESTABLISHED]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream in = requestSocket.getInputStream();
        OutputStream out = new FileOutputStream("aangekomenLangsServer.jpg");

        byte[] buffer = new byte[1024];
        int bytesRead;
        System.out.println("[DOWNLOADING FILE]");
        while ((bytesRead = in.read(buffer)) != -1) {
            try {
                out.write(buffer, 0, bytesRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("[DOWNLOAD COMPLETE]");
            System.out.println("DISCONNECTING ... ");
            in.close();
            out.close();
            requestSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}