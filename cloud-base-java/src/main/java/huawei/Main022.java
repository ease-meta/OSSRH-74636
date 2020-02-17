package huawei;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main022 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			Map<Character, Integer> map = new LinkedHashMap<>();
			char[] arr = scanner.nextLine().toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if (map.containsKey(arr[i]))
					map.put(arr[i], map.get(arr[i]) + 1);
				else
					map.put(arr[i], 1);
			}
			Collection<Integer> ci = map.values();
			int index = Collections.min(ci);
			StringBuilder sb = new StringBuilder("");
			for (char c : arr) {
				if (map.get(c) != index)
					sb.append(c);
			}
			System.out.println(sb);
		}
	}
}
