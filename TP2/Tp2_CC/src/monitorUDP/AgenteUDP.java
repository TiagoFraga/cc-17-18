/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagofraga
 */
public class AgenteUDP implements Runnable {
    
    private int id;
    private int port;
    private InetAddress multicast;
    private InetAddress localhost;
    private DatagramSocket agenteSocket;
    private byte[] agenteSendData;
    private byte[] agenteReceiveData;
    private DatagramPacket agenteSendPacket;
    private DatagramPacket agenteReceivePacket;
    private long tempo;
    
    private long cpu;
    

    public AgenteUDP(int i, int port, InetAddress multicast) throws SocketException, UnknownHostException {
        this.id = i;
        this.port = port;
        this.multicast = multicast;
        this.localhost = InetAddress.getByName("localhost");
        this.agenteSocket = new DatagramSocket();
        this.agenteReceiveData = new byte[1024];
        this.agenteReceiveData = new byte[1024];
        this.tempo = 0;
    }

    @Override
    public void run() {
        System.out.println("AgenteUDP"+this.id+" is running !!!");
        while(true){
            this.agenteReceivePacket = new DatagramPacket(this.agenteReceiveData,this.agenteReceiveData.length);
            try {
                this.agenteSocket.receive(this.agenteReceivePacket);
            } catch (IOException ex) {
                Logger.getLogger(AgenteUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String probe = new String(this.agenteReceivePacket.getData());
            
            if(probe.equals("probe")){
         
                this.tempo = this.id *1000;
                try {
                    Thread.sleep(this.tempo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AgenteUDP.class.getName()).log(Level.SEVERE, null, ex);
                }

                ThreadMXBean atual = ManagementFactory.getThreadMXBean();
                this.cpu = atual.getThreadCpuTime(Thread.currentThread().getId());

                PDUResponse pduResponse = new PDUResponse (this.id,this.cpu);
                this.agenteSendData = pduResponse.getPDU().getBytes();
                this.agenteSendPacket = new DatagramPacket(this.agenteSendData,this.agenteSendData.length,this.localhost,this.port);


                try {
                    this.agenteSocket.send(this.agenteSendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(AgenteUDP.class.getName()).log(Level.SEVERE, null, ex);
                }


                this.agenteReceivePacket = new DatagramPacket(this.agenteReceiveData,this.agenteReceiveData.length);

                try {
                    this.agenteSocket.receive(this.agenteReceivePacket);
                } catch (IOException ex) {
                    Logger.getLogger(AgenteUDP.class.getName()).log(Level.SEVERE, null, ex);
                }

                String message = new String(this.agenteReceivePacket.getData());
                System.out.println("AgenteUDP"+this.id+" receive: "+message+"-> from MonitorUDP");
                System.out.println("AgenteUDP"+this.id+" loading...");
            }else{
                System.out.println("AgenteUDP"+this.id+" loading...");
            }
        }
    }
    
}
