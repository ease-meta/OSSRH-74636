package huawei;

import java.util.Scanner;

/**
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 * <p>
 * 最后一个数后面也要有空格
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main006 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			long num = Long.parseLong(str);
			System.out.println(getResult(num));
		}
	}

	public static String getResult(long ulDataInput) {
		StringBuilder stringBuilder = new StringBuilder();
		int index = 2;
		while (index <= ulDataInput) {
			if (ulDataInput % index == 0) {
				if (index == ulDataInput) {
					stringBuilder.append(index).append(" ");
					break;
				} else {
					stringBuilder.append(index).append(" ");
					ulDataInput = ulDataInput / index;
				}
			} else {
				index++;
			}
		}
		return stringBuilder.toString();
	}
}
