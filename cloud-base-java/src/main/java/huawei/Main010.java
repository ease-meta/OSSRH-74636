package huawei;

import java.util.Scanner;

/**
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main010 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (!stringBuffer.toString().contains(str.charAt(i) + "")) {
				stringBuffer.append(str.charAt(i));
			}
		}
		System.out.println(stringBuffer.toString().length());
	}
}
