package huawei;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main008 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = Integer.parseInt(scanner.nextLine());
		SortedMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < num; i++) {
			String string = scanner.nextLine();
			String[] strings = string.split("\\s+");
			int key = Integer.parseInt(strings[0]);
			int value = Integer.parseInt(strings[1]);
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + value);
			} else {
				map.put(key, value);
			}
		}
		map.forEach((k,v)->{
			System.out.println(k+" "+v);
		});
	}
}
