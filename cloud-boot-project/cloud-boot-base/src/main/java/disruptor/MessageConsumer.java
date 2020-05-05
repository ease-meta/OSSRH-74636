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
package disruptor;/*
package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.stream.IntStream;

public class MessageConsumer {

public static void main(String[] args) {
String message = "Hello Disruptor!";
int ringBufferSize = 1024;//必须是2的N次方
Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(), ringBufferSize, new MessageThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
//这里用的是单一生成者，如果是多生成者的话是另一种模式，自己的类实现WorkHandler接口，
//然后这边调用    disruptor.handleEventsWithWorkerPool(new MessageEventHandler());
disruptor.handleEventsWith(new MessageEvenHandler3());
disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
RingBuffer<MessageEvent> ringBuffer = disruptor.start();
MessageEventProducer producer = new MessageEventProducer(ringBuffer);
IntStream.range(0, 20).forEach(x -> {
producer.onData(x + message);
});
}
}*/

