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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LocalDateTimeUtilsTest {

	@Test
	void localDateNow() {
		log.debug("{}", LocalDateTimeUtils.localDateNow());
	}

	@Test
	void localDateTimeNow() {
		log.debug("{}", LocalDateTimeUtils.localDateTimeNow());
	}

	@Test
	void yearMonth() {
		log.debug("{}",LocalDateTimeUtils.yearMonth());
	}

	@Test
	void isLeapYear() {
		log.debug("{}",LocalDateTimeUtils.isLeapYear(LocalDateTimeUtils.localDateNow()));
	}

	@Test
	void convertDateToLDT() {
	}

	@Test
	void convertLDTToDate() {
	}

	@Test
	void formatTime() {
	}

	@Test
	void testFormatTime() {
	}

	@Test
	void getMilliByTime() {
	}

	@Test
	void getSecondsByTime() {
	}

	@Test
	void testFormatTime1() {
	}

	@Test
	void formatNow() {
	}

	@Test
	void getSystemMillis() {
	}

	@Test
	void plus() {
		//LocalDateTimeUtils.plus()
	}

	@Test
	void minu() {
	}

	@Test
	void betweenTwoTime() {
	}

	@Test
	void getDayStart() {
	}

	@Test
	void getDayEnd() {
	}

	@Test
	void compare() {
	}

	@Test
	void getMonthDay() {
	}
}