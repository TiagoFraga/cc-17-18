/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testemulticast2;

/**
 *
 * @author tiagofraga
 */

    
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class Servidor {
  
    public static void main(String[] args) throws Exception {
    int mcPort = 12345;
    System.setProperty("java.net.preferIPv4Stack", "true");
    String mcIPStr = "230.1.1.1";
    DatagramSocket udpSocket = new DatagramSocket();

    InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
    byte[] msg = "Hello".getBytes();
    DatagramPacket packet = new DatagramPacket(msg, msg.length);
    packet.setAddress(mcIPAddress);
    packet.setPort(mcPort);
    udpSocket.send(packet);

    System.out.println("Sent a  multicast message.");
    
  
  }
}

