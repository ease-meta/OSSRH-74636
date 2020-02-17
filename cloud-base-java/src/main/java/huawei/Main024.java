package huawei;

import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main024 {

	public static void main(String[] args) {
		char c1 = 'a';
		System.out.printf((int)c1+"");
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			StringBuffer builder = new StringBuffer();
			String str = s.nextLine();
			for (int i = 0; i < 26; i++) {
				char c = (char) (i + 'A');
				for (int j = 0; j < str.length(); j++) {
					char sc = str.charAt(j);
					if (c == sc || c == sc - 32) {
						builder.append(sc);
					}
				}
			}
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
					builder.insert(i, c);
				}
			}
			System.out.println(builder.toString());
		}

	}
}
