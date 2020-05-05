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
package com.open.cloud.server.tio;

import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;

/**
 * @author Leijian
 * @date 2020/4/12
 */
public class CloudListen implements ServerAioListener {
    @Override
    public boolean onHeartbeatTimeout(ChannelContext channelContext, Long interval,
                                      int heartbeatTimeoutCount) {
        System.out.println("onHeartbeatTimeout");
        return true;
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected,
                                 boolean isReconnect) throws Exception {
        System.out.println("onAfterConnected");
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize)
                                                                                            throws Exception {
        System.out.println("onAfterDecoded");
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes)
                                                                                      throws Exception {
        System.out.println("onAfterReceivedBytes");
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess)
                                                                                                throws Exception {
        System.out.println("onAfterSent");
        Tio.close(channelContext, "");
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost)
                                                                                       throws Exception {
        System.out.println("onAfterHandled");
    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark,
                              boolean isRemove) throws Exception {
        System.out.println("onBeforeClose");
    }
}
