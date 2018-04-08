/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelaEstado;

import java.util.HashMap;

/**
 *
 * @author tiagofraga
 */
public class TabelaEstado {
    private HashMap<Integer,BackEndServer> tabela;
    
    public TabelaEstado(int nrAgentesUDP) {
        this.tabela = new HashMap<Integer,BackEndServer>();
        for(int i=0;i<nrAgentesUDP;i++){
            BackEndServer beServer = new BackEndServer(i+1);
            this.tabela.put(i, beServer);
        }
    }

    public synchronized HashMap<Integer, BackEndServer> getTabela() {
        return tabela;
    }

    public TabelaEstado(HashMap<Integer, BackEndServer> tabela) {
        this.tabela = tabela;
    }
    
    
}
