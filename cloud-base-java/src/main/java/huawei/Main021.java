package huawei;

import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main021 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			System.out.println(scanner.nextInt() / 2);
		}
	}
}
