/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteUDP;

import com.sun.management.ThreadMXBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
        this.id = 0;
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
        Scanner ler = new Scanner(System.in);
        System.out.println("->Put a number for the Agent Identification: ");
        this.id = ler.nextInt();
        System.out.println("WAITING FOR PROBES...");
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
                
                ThreadMXBean atual = (ThreadMXBean) ManagementFactory.getThreadMXBean();
                this.cpu = atual.getThreadCpuTime(Thread.currentThread().getId());
                this.memory = atual.getThreadAllocatedBytes(Thread.currentThread().getId());
                PDUResponse pdu = new PDUResponse (this.id,this.cpu,this.memory);
                
                String pduResponse = pdu.getPDU();
                sendData = pduResponse.getBytes();
                
                InetAddress endereco = InetAddress.getByName("localhost");
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, endereco, 1234);
                senderSocket.send(sendPacket);
                
                System.out.println("SEND PDU RESPONSE TO MONITOR");
                
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