/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author tiagofraga
 */
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String host = "www.google.com";
        Socket socket = new Socket(host, 80);
        String request = "GET / HTTP/1.0\r\n\r\n";
        OutputStream os = socket.getOutputStream();
        os.write(request.getBytes());
        os.flush();

        InputStream is = socket.getInputStream();
        int ch;
        while( (ch=is.read())!= -1)
            System.out.print((char)ch);
        socket.close();         }
    
}
