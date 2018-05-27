/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverseProxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import monitorUDP.InfoServidor;
import reverseProxy.Server_Worker;

/**
 *
 * @author tiagofraga
 */
public class ReverseProxy implements Runnable {
    
    private HashMap<Integer,InfoServidor> tabela;
    private int port;
    private ServerSocket serverSocket;

    public ReverseProxy(HashMap<Integer, InfoServidor> tabela) {
        this.tabela = tabela;
        this.port = 12345;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.port);

            while(true) {
		System.out.println("# Reverse Proxy # -> Server is running waiting for a new connections...");
		Socket socket = serverSocket.accept();
		
                System.out.println("# Reverse Proxy # -> Connection received! Create worker thread to handle connection.");
		Thread t = new Thread(new Server_Worker(this, socket));
		t.start();			
			
            }
        
        } catch (IOException e) {
            System.out.println("Error accepting connection: " + e.getMessage());
        }
        
    }
    
    
   
    
    public synchronized InfoServidor escolhaServidor(){
        int menor = -1;
        InfoServidor infoMenor = null;
        for(Integer i: this.tabela.keySet()){
            if(menor == -1){
                menor = i;
                infoMenor = this.tabela.get(i);
            }
            else{
                long cpuMenor = infoMenor.getCpu();
                long ramMenor = infoMenor.getRam();
                long cpuAtual = this.tabela.get(i).getCpu();
                long ramAtual = this.tabela.get(i).getRam();
                if(ramAtual <= ramMenor && cpuAtual<= cpuMenor){
                    menor = i;
                    infoMenor = this.tabela.get(i);
                }
            }
            
        }
        return infoMenor;
    }
    
}
