/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import agenteUDP.AgenteUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 *
 * @author tiagofraga
 */
public class MonitorUDP {
    
    private HashMap<Integer,InfoServidor> tabela;
    private InetAddress multicast;
    private int port;
    private long tempo;
    
    private MonitorUDP(String string, int porta) throws UnknownHostException {
        this.multicast = InetAddress.getByName(string);
        this.port = porta;
        this.tabela = new HashMap<Integer,InfoServidor>();
        this.tempo = 0;
    }

    public synchronized HashMap<Integer, InfoServidor> getTabela() {
        return tabela;
    }

    public synchronized void setTabela(HashMap<Integer, InfoServidor> tabela) {
        this.tabela = tabela;
    }

    public synchronized InetAddress getMulticast() {
        return multicast;
    }

    public synchronized void setMulticast(InetAddress multicast) {
        this.multicast = multicast;
    }

    public synchronized int getPort() {
        return port;
    }

    public synchronized void setPort(int port) {
        this.port = port;
    }

    public synchronized long getTempo() {
        return tempo;
    }

    public synchronized void setTempo(long tempo) {
        this.tempo = tempo;
    }

    
    
    
    private void startMonitorUDP() throws InterruptedException {
        System.out.println("**************** WELCOME UDP MONITOR********************************");
        

        try (DatagramSocket serverSocket = new DatagramSocket()) {
            Thread receiver = new Thread( new MonitorUDP_Receiver(this));
            receiver.start();
            while(true){
                String probe = "PROBE";
                DatagramPacket probePacket = new DatagramPacket(probe.getBytes(),probe.getBytes().length, this.multicast, this.port);
                serverSocket.send(probePacket);
                System.out.println("PROBE SENDEND");
                Thread.sleep(3000);
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        MonitorUDP a = new MonitorUDP("239.8.8.8",8888);
        a.startMonitorUDP();
       
    }
     
     
     
}
