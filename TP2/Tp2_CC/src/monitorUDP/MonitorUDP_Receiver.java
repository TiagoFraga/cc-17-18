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
import java.util.HashMap;
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
            HashMap<Integer,InfoServidor> tabela = this.atual.getTabela();
            
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                receiverSocket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                String[] campos = msg.split(";");
                int nrResponse = Integer.parseInt(campos[0]);
                int idResponse = Integer.parseInt(campos[1]);
                long cpuResponse = Long.parseLong(campos[2]);
                long memoryResponse = Long.parseLong(campos[3]);
                String url = campos[4];
                InetAddress endereco = receivePacket.getAddress();
                int port = receivePacket.getPort();
                
                if(tabela.containsKey(idResponse)){
                    InfoServidor info = tabela.get(idResponse);
                    info.setLastProbe(nrResponse);
                    info.setCpu(cpuResponse);
                    info.setRam(memoryResponse);
                    info.setPorta(port);
                    info.setIp(endereco);
                    info.setUrl(url);
                    
                }else{
                    InfoServidor info = new InfoServidor(nrResponse,cpuResponse,memoryResponse,port,endereco,url);
                    tabela.put(idResponse, info);
                }
            }
            
       
        } catch (SocketException ex) {
            Logger.getLogger(MonitorUDP_Receiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MonitorUDP_Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
