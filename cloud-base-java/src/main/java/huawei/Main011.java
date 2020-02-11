package huawei;

import java.util.Scanner;

/**
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * <p>
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main011 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			stringBuffer.append(str.charAt(i));
		}
		System.out.println(stringBuffer.reverse().toString());
	}
}
