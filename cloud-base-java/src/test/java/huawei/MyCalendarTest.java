package huawei;

import org.junit.jupiter.api.Test;

/**
 * @author Leijian
 * @date   2020/2/19
 */
class MyCalendarTest {

	@Test
	void book() {
		MyCalendar cal = new MyCalendar();
		System.out.println(cal.book(10, 20)); // returns true
		System.out.println(cal.book(50, 60)); // returns true
		System.out.println(cal.book(10, 40)); // returns true
		System.out.println(cal.book(10, 40)); // returns true
		System.out.println(cal.book(5, 15)); // returns false
		System.out.println(cal.book(5, 10)); // returns true
		System.out.println(cal.book(25, 55)); // returns false
	}
}