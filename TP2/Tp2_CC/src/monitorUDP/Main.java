/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitorUDP;

import tabelaEstado.TabelaEstado;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagofraga
 */
public class Main {
    
    public static void main(String args[]) throws UnknownHostException, SocketException{
        
        System.out.println("************************** WELCOME ***********************************");
        System.out.println("********************* ReverseProxy v1.0 ******************************");
        int nrAgentesUDP;
        Scanner ler = new Scanner(System.in);
        System.out.println("How Many BackEnd Servers do you want ?");
        nrAgentesUDP = ler.nextInt();
        TabelaEstado tabela = new TabelaEstado(nrAgentesUDP);
        Thread monitor = new Thread (new MonitorUDP(nrAgentesUDP,tabela));
        System.out.println("Loading Monitor .......");
        monitor.start();
        try {
            monitor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
}
