/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author tiagofraga
 */
public class AgenteUDP {
    
    private int id;
    private int port;
    private InetAddress multicast;
    private long tempo;
    private long cpu;
    private long memory;
   
    private AgenteUDP(String string, int porta) throws UnknownHostException {
        this.port = porta;
        this.multicast = InetAddress.getByName(string);
        this.tempo = 0;
        this.cpu = 0;
        this.memory = 0;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getPort() {
        return port;
    }

    public synchronized void setPort(int port) {
        this.port = port;
    }

    public synchronized InetAddress getMulticast() {
        return multicast;
    }

    public synchronized void setMulticast(InetAddress multicast) {
        this.multicast = multicast;
    }

    public synchronized long getTempo() {
        return tempo;
    }

    public synchronized void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public synchronized long getCpu() {
        return cpu;
    }

    public synchronized void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public synchronized long getMemory() {
        return memory;
    }

    public synchronized void setMemory(long memory) {
        this.memory = memory;
    }

    
    
    private void startAgenteUDP() {
        System.out.println("**************** WELCOME UDP AGENT********************************");
        
        byte[] buffer = new byte[1024];
        
        try (MulticastSocket listenerSocket = new MulticastSocket(this.port)){
            listenerSocket.joinGroup(this.multicast);
      
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buffer, buffer.length);
                listenerSocket.receive(msgPacket);
                
                String msg = new String(buffer, 0, buffer.length);
                System.out.println("UDP AGENT received: " + msg);
                
                DatagramSocket senderSocket = new DatagramSocket();
                byte[] sendData = new byte[1024];
                
                String frase = "AQUI CRL";
                sendData = frase.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.multicast, this.port);
                senderSocket.send(sendPacket);
                
                System.out.println("ENVIEI O MONITOR PO CRL !");
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.setProperty("java.net.preferIPv4Stack", "true");
        AgenteUDP a = new AgenteUDP("239.8.8.8",8888);
        a.startAgenteUDP();
    }

    
    
}
