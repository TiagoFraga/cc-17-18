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
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {
    
  public static void main(String[] args) throws Exception {
    System.setProperty("java.net.preferIPv4Stack", "true");
    int mcPort = 12345;
    String mcIPStr = "230.1.1.1";
    MulticastSocket mcSocket = null;
    InetAddress mcIPAddress = null;
    mcIPAddress = InetAddress.getByName(mcIPStr);
    mcSocket = new MulticastSocket(mcPort);
    System.out.println("Multicast Receiver running at:"+ mcSocket.getLocalSocketAddress());
    mcSocket.joinGroup(mcIPAddress);

    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

    System.out.println("Waiting for a  multicast message...");
    mcSocket.receive(packet);
    String msg = new String(packet.getData(), packet.getOffset(),packet.getLength());
    System.out.println("[Multicast  Receiver] Received:" + msg);

    mcSocket.leaveGroup(mcIPAddress);
    mcSocket.close();
  }
}
    

