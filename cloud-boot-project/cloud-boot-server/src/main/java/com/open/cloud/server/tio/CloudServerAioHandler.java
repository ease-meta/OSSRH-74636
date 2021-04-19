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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.core.utils.ByteBufferUtils;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * @author Leijian
 * @date 2020/4/11
 */
public class CloudServerAioHandler implements ServerAioHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(CloudServerAioHandler.class);
    private static final String EMPTY_RESPONSE = "Galaxy server is starting!";
    private static final String DEFAULT_SERVICE_INVOKER_ID = "serviceInvoker";

    private String msgParser;

    public CloudServerAioHandler(String msgParser) {
        this.msgParser = msgParser;
    }

    public String getMsgParser() {
        return msgParser;
    }

    public void setMsgParser(String msgParser) {
        this.msgParser = msgParser;
    }

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    8个字节，存储消息体的长度
     * 消息体结构：
     */
    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength,
                         ChannelContext channelContext) throws AioDecodeException {
        if (readableLength < MessagePacket.HEADER_LENGHT) {
            return null;
        }
        //一次解析出所有包
        MessagePacket messagePacket = new MessagePacket();
        byte[] contentBytes = ByteBufferUtils.readBytes(buffer, readableLength);
        messagePacket.setBody(contentBytes);
        return messagePacket;
    }

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        MessagePacket sendMessage = (MessagePacket) packet;
        ByteBuffer byteBuffer = ByteBuffer.wrap(sendMessage.getBody());
        return byteBuffer;
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        MessagePacket sendMessage = new MessagePacket();
        try {
            MessagePacket messagePacket = (MessagePacket) packet;
            byte[] contentBytes = messagePacket.getBody();
            byte[] lengthBytes = new byte[8];
            System.arraycopy(contentBytes, 0, lengthBytes, 0, 8);
            String length = new String(lengthBytes, MessagePacket.CHARSET);
            int contentLength = Integer.parseInt(length);
            byte[] bodyBytes = new byte[contentLength];
            System.arraycopy(contentBytes, 8, bodyBytes, 0, contentLength);
            String inMsg = new String(bodyBytes, MessagePacket.CHARSET);
            String out = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<Message>\n"
                    + "    <Head>\n" + "        <rejCode>000000</rejCode>\n"
                    + "        <rejMsg>XXX</rejMsg>\n" + "    </Head>\n" + "</Message>";
            sendMessage.setBody(out.getBytes(MessagePacket.CHARSET));
            Tio.send(channelContext, sendMessage);
        } catch (Exception e) {
            String out = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<Message>\n"
                    + "    <Head>\n" + "        <rejCode>999999</rejCode>\n"
                    + "        <rejMsg>XXX</rejMsg>\n" + "    </Head>\n" + "</Message>";
            sendMessage.setBody(out.getBytes(MessagePacket.CHARSET));
            Tio.send(channelContext, sendMessage);
        }
    }
}
