/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.common.utils;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author Leijian
 * @date   2020/2/7
 */
public class NetUtils {

    private static InetAddress localAddress;

    static {
        try {
            localAddress = getLocalInetAddress();
        } catch (SocketException e) {
            throw new RuntimeException("fail to get local ip.");
        }
    }

    /**
     * @return java.net.InetAddress
     * @Author leijian
     * @Description //TODO
     * @Date 2019/3/28 9:18
     * @Param []
     **/
    private static InetAddress getLocalInetAddress() throws SocketException {
        // enumerates all network interfaces
        Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();

        while (enu.hasMoreElements()) {
            NetworkInterface ni = enu.nextElement();
            if (ni.isLoopback()) {
                continue;
            }

            Enumeration<InetAddress> addressEnumeration = ni.getInetAddresses();
            while (addressEnumeration.hasMoreElements()) {
                InetAddress address = addressEnumeration.nextElement();

                // ignores all invalidated addresses
                if (address.isLinkLocalAddress() || address.isLoopbackAddress() || address.isAnyLocalAddress()) {
                    continue;
                }

                return address;
            }
        }

        throw new RuntimeException("No validated local address!");
    }

    /**
     * @return java.lang.String
     * @Author leijian
     * @Description //TODO
     * @Date 2019/3/28 9:13
     * @Param []
     **/
    public static String getLocalAddress() {
        return localAddress.getHostAddress();
    }

    public static String getHostName() {
        return localAddress.getHostName();
    }
}