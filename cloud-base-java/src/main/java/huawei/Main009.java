package huawei;

import java.util.Scanner;

/**
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main009 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		String str = String.valueOf(num);
		String finals = "";
		for (int i = 0; i < str.length(); i++) {
			if (!finals.contains(String.valueOf(str.charAt(str.length() - 1 - i)))) {
				finals = finals + String.valueOf(str.charAt(str.length() - 1 - i));
			}
		}
		System.out.println(finals);
	}
}
