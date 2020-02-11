package huawei;

import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/11
 */
public class Main002 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String all = "";
		String one = "";
		char[] ac;
		char temp;
		int num = 0;
		while (s.hasNext()) {
			//s.toUpperCase(),String 转化为大写
			//s.toLowerCase(),String 转化为小写
			//String字符转换，s.toCharArray()与s.charAt(index)
			//char字符转换，String.valueOf(c)转化为String
			all = s.nextLine();
			one = s.nextLine();
			//存放原来所有的
			ac = all.toCharArray();
			//存放要的字符
			//temp=one.charAt(0);
			for (int i = 0; i < ac.length; i++) {
				if (one.equalsIgnoreCase(String.valueOf(ac[i])))
					num++;
			}
			System.out.println(num);
		}
	}
}
