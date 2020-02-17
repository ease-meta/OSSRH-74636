package huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.nextLine();
			if (isLengthQualified(str) &&
					isContentQualified(str) &&
					!hasDuplicatedString(str))
				System.out.println("OK");
			else
				System.out.println("NG");
		}
		sc.close();
	}

	public static boolean isLengthQualified(String s) {
		return s.length() > 8;
	}

	public static boolean isContentQualified(String s) {
		int count = 0;
		String[] str = {"[a-z]", "[A-Z]", "[0-9]", "[^a-zA-Z0-9]"};
		for (int i = 0; i < str.length; i++) {
			Pattern p = Pattern.compile(str[i]);
			Matcher m = p.matcher(s);
			if (m.find())
				count++;
		}
		return count >= 3;

	}

	public static boolean hasDuplicatedString(String s) {
		for (int i = 0; i < s.length() - 3; i++) {
			if (s.substring(i + 3).contains(s.substring(i, i + 3)))
				return true;
		}
		return false;
	}
}
