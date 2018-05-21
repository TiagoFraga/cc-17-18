/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteUDP;

/**
 *
 * @author tiagofraga
 */
public class PDUResponse {
    private int id;
    private long cpu;
    private long memory;
    
    public PDUResponse(int id,long cpu, long memory) {
        this.id = id;
        this.cpu = cpu;
        this.memory = memory;
    }

    public String getPDU() {
        StringBuilder s = new StringBuilder();
        s.append(this.id + ";");
        s.append(this.cpu + ";");
        s.append(this.memory + ";");
        return s.toString();
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
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
    
    
    
}
