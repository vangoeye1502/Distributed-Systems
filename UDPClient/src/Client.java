import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;

public class Client {

    public static void main(String[] args) throws Exception {

        System.out.println("[INITIATING CLIENT PROGRAM]");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("aangekomenData.jpg"));
        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket binnenGekomenPakket = null;
        byte[] binnenGekomenData = new byte[1024];
        String bericht = "Give me your file!";

        /** Verzenden van pakket om server te laten
            weten dat de file aangevraagd wordt **/
        System.out.println("[CREATING REQUEST]");
        DatagramPacket requestPakket =
                new DatagramPacket(bericht.getBytes(), bericht.getBytes().length, InetAddress.getLocalHost(), 9876);
        clientSocket.send(requestPakket);
        System.out.println("[REQUEST SEND]");

        /** loop om verstuurde data van server binnen te halen en weg te schrijven
            naar de harde schijf **/
        byte[] fin = "FIN".getBytes();
        int i = 0;
        clientSocket.setSoTimeout(2000);
        do {
            try {
                binnenGekomenPakket = new DatagramPacket(binnenGekomenData, binnenGekomenData.length);
                clientSocket.receive(binnenGekomenPakket);
                i++;
                System.out.print(".");
                out.write(binnenGekomenPakket.getData());
            } catch (SocketTimeoutException e) {
                break;
            }
        } while(!Arrays.equals(binnenGekomenPakket.getData(), fin));

        System.out.println("\n" + "[FILE SAVED]");
        System.out.println("PACKETS RECEIVED: " + i + "\n");
        out.close();
        clientSocket.close();
    }


}
