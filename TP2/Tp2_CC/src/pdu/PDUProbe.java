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
public class PDUProbe {
    private int id;
    private String msg;

    public PDUProbe(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }
  
      public String getPDUProbe() {
        StringBuilder s = new StringBuilder();
        s.append(this.msg + ";");
        s.append(this.id + ";");
        return s.toString();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
