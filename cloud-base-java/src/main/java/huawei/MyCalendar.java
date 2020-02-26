package huawei;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Leijian
 * @date 2020/2/19
 */
public class MyCalendar {

	private static ConcurrentHashMap<Long, Book> concurrentHashMap = new ConcurrentHashMap();
	private static TreeSet<Book> treeSet = new TreeSet<>();
	private AtomicLong atomicLong = new AtomicLong();

	public boolean book(final int start, final int end) {
		Book book = new Book(start, end, 1);
		Long index = atomicLong.getAndIncrement();
		concurrentHashMap.forEach((k, v) -> {
			int startTemp = v.getStart();
			int endTemp = v.getEnd();
			int levelTemp = v.getLevel();
			//一致判断
			if (v.equals(book)) {
				book.setLevel(book.getLevel() + 1);
			} else if (start >= startTemp && endTemp >= end) {//区间内
				book.setLevel(book.getLevel() + 1);
			} else if (start <= startTemp && (startTemp < end && end <= endTemp)) { //前区间交集
				book.setLevel(book.getLevel() + 1);
			} else if (end >= endTemp && (startTemp <= start && start <= endTemp)) {//后区间交集
				book.setLevel(book.getLevel() + 1);
			}
		});
		boolean flag = book.getLevel() < 4;
		if (flag) {
			concurrentHashMap.put(index, book);
			treeSet.add(book);
		}
		return flag;
	}

	class Book implements Comparable<Book> {

		private Book() {

		}

		/**
		 * @param start 起始时间
		 * @param end   结束时间
		 * @param level 重预定次数
		 */
		Book(int start, int end, int level) {
			this.start = start;
			this.end = end;
			this.level = level;
		}

		int start, end, level;

		public void setLevel(int level) {
			this.level = level;
		}

		public int getLevel() {
			return level;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getEnd() {
			return end;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getStart() {
			return start;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Book book = (Book) o;
			return start == book.start && end == book.end;
		}

		@Override
		public int compareTo(Book o) {
			if (this.getStart() - o.getStart() == 0) {
				return this.getEnd() - o.getEnd();
			} else {
				return this.getStart() - o.getStart();
			}
		}
	}
}
