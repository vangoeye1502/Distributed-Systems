import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String IPAddress = "localhost";
        Socket requestSocket = new Socket(IPAddress, 7896);

        InputStream in = requestSocket.getInputStream();
        OutputStream out = new FileOutputStream("aangekomenLangsServer.zip");

        byte[] buffer = new byte[1024];
        Int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();
        requestSocket.close();
    }
}