/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import java.net.InetAddress;

/**
 *
 * @author tiagofraga
 */

public class InfoServidor {
    private int lastProbe;
    private String url;
    private InetAddress ip;
    private int porta;
    private long ram;
    private long cpu;
    private long rtt;
    

    public InfoServidor(int lastProbe,long cpuResponse, long memoryResponse, int port, InetAddress endereco,String url) {
        this.lastProbe = lastProbe;
        this.cpu = cpuResponse;
        this.ram = memoryResponse;
        this.porta = port;
        this.ip = endereco;
        this.url = url;
    }

    public synchronized int getLastProbe() {
        return lastProbe;
    }

    public synchronized void setLastProbe(int lastProbe) {
        this.lastProbe = lastProbe;
    }

    public synchronized  String getUrl() {
        return url;
    }

    public synchronized void setUrl(String url) {
        this.url = url;
    }

    public synchronized InetAddress getIp() {
        return ip;
    }

    public synchronized void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public synchronized int getPorta() {
        return porta;
    }

    public synchronized void setPorta(int porta) {
        this.porta = porta;
    }

    public synchronized long getRam() {
        return ram;
    }

    public synchronized void setRam(long ram) {
        this.ram = ram;
    }

    public synchronized long getCpu() {
        return cpu;
    }

    public synchronized void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public synchronized long getRtt() {
        return rtt;
    }

    public synchronized void setRtt(long rtt) {
        this.rtt = rtt;
    }
    
    
   
}
