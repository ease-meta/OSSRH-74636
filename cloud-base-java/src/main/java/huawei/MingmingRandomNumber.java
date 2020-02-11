package huawei;

import org.ehcache.shadow.org.terracotta.offheapstore.storage.HalfStorageEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Leijian
 * @date 2020/2/11
 */
public class MingmingRandomNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		while (scanner.hasNext()) {
			n = scanner.nextInt();
			HashMap sortedMap = new HashMap();
			for (int i = 0; i < n; i++) {
				int num = ThreadLocalRandom.current().nextInt(1, 1000);
				sortedMap.putIfAbsent(num, num);
			}
			LinkedList linkedList = new LinkedList();
			sortedMap.forEach((k,v)->linkedList.add(k));
			System.out.println("sortedMap = " + linkedList.stream().sorted().collect(Collectors.toList()));
			linkedList.toArray();
		}
	}
}
