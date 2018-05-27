/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdu;

/**
 *
 * @author tiagofraga
 */
public class PDUResponse {
    private int nr;
    private int id;
    private long cpu;
    private long memory;
    private String url;
    
    public PDUResponse(int nr,int id,long cpu, long memory,String url) {
        this.nr = nr;
        this.id = id;
        this.cpu = cpu;
        this.memory = memory;
        this.url = url;
    }

    public String getPDUResponse() {
        StringBuilder s = new StringBuilder();
        s.append(this.nr + ";");
        s.append(this.id + ";");
        s.append(this.cpu + ";");
        s.append(this.memory + ";");
        s.append(this.url + ";");
        return s.toString();
    }

    public synchronized int getNr() {
        return nr;
    }

    public synchronized void setNr(int nr) {
        this.nr = nr;
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

    public synchronized String getUrl() {
        return url;
    }

    public synchronized void setUrl(String url) {
        this.url = url;
    }
    
    
    
    
}
