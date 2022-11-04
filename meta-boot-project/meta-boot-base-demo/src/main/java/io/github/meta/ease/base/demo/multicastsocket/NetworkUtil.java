package io.github.meta.ease.base.demo.multicastsocket;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkUtil {
    private final static String LOCAL_IP = "127.0.0.1";
    /**
     * 获取本机有效所有网卡地址
     *
     * @return  List<NetworkInterface> ip列表
     * @throws
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws Exception {
        List<NetworkInterface> localIPlist = new ArrayList<NetworkInterface>();
        Enumeration<NetworkInterface> interfs =
        NetworkInterface.getNetworkInterfaces();
        if (interfs == null) {
            return null;
        }
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    if (!LOCAL_IP.equals(in.getHostAddress())){
                        localIPlist.add(interf);
                    }
                }
            }
        }
        return localIPlist;
    }
 
}