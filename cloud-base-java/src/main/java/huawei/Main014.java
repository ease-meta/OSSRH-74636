package huawei;

import java.util.Scanner;

/**
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 * @author Leijian
 * @date 2020/2/12
 */
public class Main014 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		String str = Integer.toBinaryString(num);
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i)+"").equals("1")) {
				count++;
			}
		}
		System.out.println(count);
	}
}
