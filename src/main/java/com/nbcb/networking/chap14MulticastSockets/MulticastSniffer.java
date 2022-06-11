package com.nbcb.networking.chap14MulticastSockets;

import com.nbcb.networking.util.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 这个代码，根据UDP协议，接收组播消息
 * 特别备注：对于MulticastSocket组播通信，需要保证组播地址是可访问的
 * 也就是说要保证all-systems.mcast.net地址是能够Ping通的
 * 一般要保证局域网，或者能够连上网
 */
public class MulticastSniffer {
    public static void main(String[] args) {
        System.out.println("Start MulticastSniffer");

        /**
         * (group)address and port
         */
        InetAddress ia = null;
        String groupName = Constants.BRODCAST_HOST;
        int port = Constants.BRODCAST_PORT;
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
