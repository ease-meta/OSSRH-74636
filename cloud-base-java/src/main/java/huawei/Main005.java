package huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main005 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()){
			String str = scanner.nextLine();
			long num = Long.parseLong(str.substring(2),16);
			System.out.println(num);
		}
	}
}
