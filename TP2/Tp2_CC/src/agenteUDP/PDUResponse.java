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
    private long cpu;
    private long memory;
    
    public PDUResponse(long cpu, long memory) {
        this.cpu = cpu;
        this.memory = memory;
    }

    public String getPDU() {
        StringBuilder s = new StringBuilder();
        s.append(this.cpu + ";");
        s.append(this.memory + ";");
        return s.toString();
    }

    public long getCpu() {
        return cpu;
    }

    public void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }
    
    
    
}
