package huawei;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定n个字符串，请对n个字符串按照字典序排列。
 * @author Leijian
 * @date 2020/2/12
 */
public class Main013 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = Integer.parseInt(scanner.nextLine());
		LinkedList linkedList = new LinkedList();
		for (int i = 0; i < num; i++) {
			linkedList.add(scanner.nextLine());
		}
		linkedList.stream().sorted().forEach(v->System.out.println(v));
	}
}
