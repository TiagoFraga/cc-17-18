/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelaEstado;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author tiagofraga
 */
public class TabelaEstado {
    
    private HashMap<Integer,BackEndServer> tabela;
    private Lock lockTabela;
    
    public TabelaEstado(int nrAgentesUDP) {
        this.tabela = new HashMap<Integer,BackEndServer>();
        for(int i=0;i<nrAgentesUDP;i++){
            BackEndServer beServer = new BackEndServer(i+1);
            this.tabela.put(i, beServer);
        }
        this.lockTabela = new ReentrantLock();
    }

    public synchronized HashMap<Integer, BackEndServer> getTabela() {
        return tabela;
    }
    
    public synchronized void refresh(int id,InetAddress endereço,int porta,long cpu,long memory){
       BackEndServer be = this.tabela.get(id);
       be.setIp(endereço);
       be.setPorta(porta);
       be.setCpu(cpu);
       be.setRam(memory);
       
    }
    
}
