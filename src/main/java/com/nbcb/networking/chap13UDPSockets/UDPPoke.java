package com.nbcb.networking.chap13UDPSockets;

import java.io.IOException;
import java.net.*;

public class UDPPoke {


    public static final int BUFFER_SIZE = 4096;
    public static final int TIME_OUT = 30000;
    private DatagramSocket socket;
    private DatagramPacket outgoing;

    /**
     * constructor
     * @param host
     * @param port
     */
    public UDPPoke(InetAddress host, int port) throws SocketException {

        outgoing = new DatagramPacket(new byte[1], 1, host, port);
        socket = new DatagramSocket(0);
        socket.connect(host, port);
        socket.setSendBufferSize(TIME_OUT);
    }

    /**
     * 随便发送一个空的字节给服务端，
     * 然后接收服务端的返回内容
     * @return
     */
    public byte[] poke() throws IOException {
        socket.send(outgoing);

        byte[] response = null;

        /**
         * 初始化来自服务端的Datagram Packet
         */
        DatagramPacket incoming = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

        /**
         * 接收来自服务端的消息
         */
        socket.receive(incoming);
        int numBytes = incoming.getLength();

        /**
         * 返回来自服务端的消息
         */
        System.arraycopy(incoming, 0 , response, 0, numBytes);

        return response;

    }

    public static void main(String[] args) {
        System.out.println("start invoke poke()");

        String host = "www.baidu.com";
        int port = 80;
        InetAddress address = null;
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            UDPPoke poker = new UDPPoke(address, port);
            byte[] response = poker.poke();

            String result = new String(response, "UTF-8");
            System.out.println(result);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
