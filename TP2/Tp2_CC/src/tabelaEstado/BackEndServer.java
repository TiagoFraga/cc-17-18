/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelaEstado;

import java.net.InetAddress;

/**
 *
 * @author tiagofraga
 */

public class BackEndServer {
    private int id;
    private InetAddress ip;
    private int porta;
    private String ram;
    private long cpu;
    private String rtt;
    

    public BackEndServer(int i) {
        this.id = i;
        this.ram = "";
        this.cpu = 0;
        this.rtt = "";
        
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

    public synchronized String getRam() {
        return ram;
    }

    public synchronized void setRam(String ram) {
        this.ram = ram;
    }

    public synchronized long getCpu() {
        return cpu;
    }

    public synchronized void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public synchronized String getRtt() {
        return rtt;
    }

    public synchronized void setRtt(String rtt) {
        this.rtt = rtt;
    }
    
    
   
}
