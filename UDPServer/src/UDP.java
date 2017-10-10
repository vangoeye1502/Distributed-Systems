import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


class UDPServer
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = null;
        byte[] ack = "ACKT".getBytes();
        try {
            serverSocket = new DatagramSocket(9876);
            byte[] binnenGekomenData = new byte[1024];
            byte[] teZendenData = new byte[1024];

            while (true) {
                System.out.println("[WAITING FOR CLIENT REQUEST]");
                DatagramPacket binnenGekomenPakket = new DatagramPacket(binnenGekomenData, binnenGekomenData.length);
                serverSocket.receive(binnenGekomenPakket);
                System.out.println("[CLIENT REQUEST RECEIVED]");
                String bericht = new String(binnenGekomenPakket.getData());
                System.out.println("BERICHT: " + bericht);

                InetAddress IPAddress = binnenGekomenPakket.getAddress();
                int port = binnenGekomenPakket.getPort();
                File file = new File("test.jpg");
                InputStream in = new FileInputStream(file);
                System.out.println("[SENDING REQUESTED FILE]");

                int i = 0;
                while (in.read(teZendenData) != -1) {
                    DatagramPacket sendPacket =
                            new DatagramPacket(teZendenData, teZendenData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                    System.out.print(".");
                    i++;
                }
                System.out.println("\n" + "PACKETS SEND: " + i + "\n");

                /** De String 'FIN' dient om een volledige overdracht van het bestand te melden
                 aan de client, zodat die client vervolgens zijn socket kan sluiten **/

                System.out.println("[TERMINATING CONNECTION]" + "\n\n" + "---------------------------");
                byte[] fin = "FIN".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(fin, fin.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                serverSocket.close();
        }
    }
}

