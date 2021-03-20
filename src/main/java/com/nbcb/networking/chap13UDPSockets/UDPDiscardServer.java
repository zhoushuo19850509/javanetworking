package com.nbcb.networking.chap13UDPSockets;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPDiscardServer {

    public static final int DEFAULT_PORT = 8099;
    public static final int MAX_PACKET_SIZE = 8099;

    public static void main(String[] args) {
        System.out.println("start server");


        int port = DEFAULT_PORT;
        byte[] buffer = new byte[MAX_PACKET_SIZE];


        try {
            /**
             * Datagram socket
             */
            DatagramSocket server = new DatagramSocket(port);
            System.out.println("the server start listening on port: " + port);

            /**
             * Datagram packet
             */
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);


            /**
             * the server receive the content from the client(s)
             */
            while(true){

                server.receive(packet);

                /**
                 * translate the packet to String
                 */
                String str = new String(packet.getData(), 0 , packet.getLength(), "UTF-8");
                System.out.println(packet.getAddress() + " on port: " + port + " says: " + str );
                packet.setLength(buffer.length);

            }



        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
