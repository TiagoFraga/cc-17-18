/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagofraga
 */
public class Cliente {
    
      
    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1",12345);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            
            String response = in.readLine();
            
           
            
            File teste = new File("/Users/tiagofraga/Desktop/teste.html");

            FileWriter fw = new FileWriter(teste);
            fw.write(response);
            fw.close();
            
            Desktop.getDesktop().browse(teste.toURI());
           
            System.out.println("Abri o browser, vou sair");
            socket.shutdownInput();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
