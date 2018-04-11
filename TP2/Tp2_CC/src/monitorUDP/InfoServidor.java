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
    private int id;
    private InetAddress ip;
    private int porta;
    private long ram;
    private long cpu;
    private long rtt;
    

    public InfoServidor(int i) {
        this.id = i;
        this.ip = null;
        this.porta = 0;
        this.ram = 0;
        this.cpu = 0;
        this.rtt = 0;
        
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
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
