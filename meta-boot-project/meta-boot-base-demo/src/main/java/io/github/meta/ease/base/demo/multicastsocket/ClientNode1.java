package io.github.meta.ease.base.demo.multicastsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static io.github.meta.ease.base.demo.multicastsocket.ServiceNode2.multicastGroup;
import static io.github.meta.ease.base.demo.multicastsocket.ServiceNode2.port;

public class ClientNode1 {


    public static void main(String[] args) throws Exception {

        try {
            InetAddress group = InetAddress.getByName(multicastGroup);

            MulticastSocket mss = null;
            mss = new MulticastSocket(port);

            mss.getNetworkInterface();
            mss.joinGroup(group);

            while (true) {
                String message = "Hello from node1";
                System.out.println(message);
                byte[] buffer = message.getBytes();

                DatagramPacket dp = new DatagramPacket(buffer, buffer.length,
                        group, port);

                mss.send(dp);

                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}