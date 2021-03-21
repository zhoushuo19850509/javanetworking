package com.nbcb.networking.chap14MulticastSockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSniffer {
    public static void main(String[] args) {

        /**
         * (group)address and port
         */
        String groupName = "all-systems.mcast.net";
        InetAddress ia = null;
        int port = 4000;
        try {
            ia = InetAddress.getByName(groupName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        /**
         * multicast socket and join the group
         */
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(ia);

            /**
             * receive the datagram packet from the group continuously
             */
            while(true){
                byte[] buffer = new byte[100];
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

                socket.receive(dp);
                String s = new String(dp.getData());
                System.out.println("msg from group : [" + s + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
