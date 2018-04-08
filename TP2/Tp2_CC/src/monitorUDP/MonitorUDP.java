/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import java.io.IOException;
import tabelaEstado.TabelaEstado;
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
public class MonitorUDP implements Runnable {
    
    private int backEndServers;
    private TabelaEstado tabela;
    private InetAddress multicast;
    private int port;
    private DatagramSocket monitorSocket;
    private byte[] monitorReceiveData;
    private byte[] monitorSendData;
    private DatagramPacket monitorSendPacket;
    private DatagramPacket monitorReceivePacket;
    private long tempo;
    
    
    public MonitorUDP(int nrAgentesUDP, TabelaEstado tabela) throws UnknownHostException, SocketException {
        this.backEndServers = nrAgentesUDP;
        this.tabela = tabela;
        this.multicast = InetAddress.getByName("239.8.8.8");
        this.port = 8888;
        this.monitorSocket = new DatagramSocket(this.port);
        this.monitorReceiveData = new byte[1024];
        this.monitorSendData = new byte[1024];
        this.tempo = 0;
    }

    @Override
    public void run() {
        System.out.println("MonitorUDP is running !");
        Thread[] agentesUDP = new Thread [this.backEndServers];
        for(int i=0;i<this.backEndServers;i++){
            try {
                agentesUDP[i] = new Thread (new AgenteUDP(i+1, this.port,this.multicast));
            } catch (SocketException ex) {
                Logger.getLogger(MonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Loading AgenteUDP"+(i+1)+"...");
        }
        
        for(int i=0;i<this.backEndServers; i++){
            agentesUDP[i].start();
        }
        while(true){
            this.tempo = this.backEndServers *1000;
            try {
                Thread.sleep(this.tempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String probe = "probe";
            this.monitorSendData = probe.getBytes();
            this.monitorSendPacket = new DatagramPacket(this.monitorSendData,this.monitorSendData.length);
            
            try {
                this.monitorSocket.send(this.monitorSendPacket);
            } catch (IOException ex) {
                Logger.getLogger(MonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            blablabla
            
            
        }
       
    }
    
}
