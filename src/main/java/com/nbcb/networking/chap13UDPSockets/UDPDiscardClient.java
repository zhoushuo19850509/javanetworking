package com.nbcb.networking.chap13UDPSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPDiscardClient {
    public static final int DEFAULT_PORT = 8099;
    public static final String DEFAULT_SERVER = "localhost";

    public static void main(String[] args) {
        System.out.println("start client!");
        /**
         * host
         */
        String hostName = DEFAULT_SERVER;

        /**
         * port
         */
        int port = DEFAULT_PORT;


        try {

            InetAddress server = InetAddress.getByName(hostName);
            /**
             * Datagram socket
             */
            DatagramSocket theSocket = new DatagramSocket();

            /**
             * Read from user input
             */
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));


            /**
             * send data from input to the server continuously
             */
            while(true){


                String readLine = userInput.readLine();
                byte[] data = readLine.getBytes("UTF-8");

                DatagramPacket packet =
                        new DatagramPacket(data, data.length, server, port);

                theSocket.send(packet);

            }

            /**
             * send sth to the server
             */

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
