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