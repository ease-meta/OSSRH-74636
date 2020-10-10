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
package com.example.demo.rocketmq.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

	@Input("input1")
	SubscribableChannel input1();

	@Input("input2")
	SubscribableChannel input2();

	@Input("input3")
	SubscribableChannel input3();

	@Input("input4")
	SubscribableChannel input4();

	@Input("input5")
	PollableMessageSource input5();

}
