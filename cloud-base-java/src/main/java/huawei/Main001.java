package huawei;

import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/11
 */
public class Main001 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = "";
		while (scanner.hasNextLine()){
			str = scanner.nextLine();
			str = str.substring(str.lastIndexOf(" ")+1,str.length());
			System.out.println(str.length());
		}
	}
}
