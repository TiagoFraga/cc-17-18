/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverseProxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitorUDP.InfoServidor;

/**
 *
 * @author tiagofraga
 */
public class Server_Worker implements Runnable {

    
    private ReverseProxy proxy;
    private Socket socket;
    private BufferedWriter out;
    
    
    
    
    public Server_Worker(ReverseProxy aThis, Socket socket) throws IOException {
        this.proxy = aThis;
        this.socket = socket;
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  
    }


    @Override
    public void run() {
        try {
            
            InfoServidor httpServer = this.proxy.escolhaServidor();
            String stringURL = httpServer.getUrl();
            StringBuilder result = new StringBuilder();
            URL url = new URL(stringURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            
            String resultado = result.toString();
            out.write(resultado);
            out.newLine();
            out.flush();
            
            this.socket.shutdownOutput();
            this.socket.close();
            
        } catch (ProtocolException ex) {
            Logger.getLogger(Server_Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server_Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server_Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
