/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeudp;

import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception{
       
       System.out.println("IM SERVER");
       
       DatagramSocket serverSocket = new DatagramSocket(9876);
            
       byte[] receiveData = new byte[1024];
       byte[] sendData = new byte[1024];
            
       while(true){
           DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
           serverSocket.receive(receivePacket);
           
           String sentence = new String( receivePacket.getData());
                  
           System.out.println("RECEIVED: " + sentence);
                  
           InetAddress IPAddress = receivePacket.getAddress();
           
           System.out.println("IPADRRESS: " + IPAddress);
                  
           int port = receivePacket.getPort();
           
           System.out.println("PORT : " + port);
               
           String capitalizedSentence = sentence.toUpperCase();
           
           System.out.println("CAPITALIZED SENTENCE: "+ capitalizedSentence);
           
           sendData = capitalizedSentence.getBytes();
           
           System.out.println("SEND DATA: " +sendData);
           
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  
           serverSocket.send(sendPacket);
               
       }
      }
}