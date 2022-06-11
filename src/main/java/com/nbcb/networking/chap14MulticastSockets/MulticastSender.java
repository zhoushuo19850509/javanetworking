package com.nbcb.networking.chap14MulticastSockets;

import com.nbcb.networking.util.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 这个代码，通过UDP协议，向组播内各个节点发送消息
 */
public class MulticastSender {
    public static void main(String[] args) {

        /**
         * address/port
         */
        String hostName = Constants.BRODCAST_HOST;
        InetAddress ia = null;
        int port = Constants.BRODCAST_PORT;
        try {
            ia = InetAddress.getByName(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte ttl = (byte)1;

        /**
         * data and datagram packet
         */
        byte[] data = "Here's something I want to say".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ia, port);


        /**
         * multicast socket and join the group
         */
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(ia);

            /**
             * send msg to the group
             */
            for (int i = 0; i < 10; i++) {
                socket.send(packet,ttl);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.leaveGroup(ia);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(null != socket){
                socket.close();
            }
        }




    }
}
