/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagofraga
 */
public class MonitorUDP_Receiver implements Runnable {
    
    private MonitorUDP atual;
    
    public MonitorUDP_Receiver(MonitorUDP monitor) {
        this.atual = monitor;
    }

    @Override
    public void run() {
        System.out.println("-> CREATED MONITOR LISTENER");
        try {
            DatagramSocket receiverSocket = new DatagramSocket(1234);
            byte[] buffer = new byte[1024];
            
            
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                receiverSocket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + msg);
                
                InetAddress endereco = receivePacket.getAddress();
                System.out.println("IPADRRESS: " + endereco);
                  
                int port = receivePacket.getPort();
                System.out.println("PORT : " + port);

            }
            
       
        } catch (SocketException ex) {
            Logger.getLogger(MonitorUDP_Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonitorUDP_Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
