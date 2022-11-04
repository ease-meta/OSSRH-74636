package io.github.meta.ease.base.demo.multicastsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class ServiceNode2 {

    public static int port = 8110;

    public static String multicastGroup = "228.0.0.4";

    public static void main(String[] args) throws Exception {

        try {
            InetAddress inetAddress = InetAddress.getByName("10.21.0.16");
            MulticastSocket multicastSocket = new MulticastSocket(null);
            multicastSocket.setReuseAddress(true);
            // bind to receive interface
            multicastSocket.bind(new InetSocketAddress(port));
            //multicastSocket.setTimeToLive(multicastConfig.getMulticastTimeToLive());
            multicastSocket.setInterface(inetAddress);
            multicastSocket.joinGroup(InetAddress.getByName(multicastGroup));
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

                multicastSocket.receive(dp);

                String s = new String(dp.getData(), 0, dp.getLength());

                System.out.println("receive from node:" + s);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}