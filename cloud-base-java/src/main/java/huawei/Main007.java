package huawei;

import java.util.Scanner;

/**
 *写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *  @author Leijian
 * @date 2020/2/11
 */
public class Main007 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double dd = scanner.nextDouble();
		String str = String.valueOf(dd);
		String[] strings = str.split("[.]");
		if (strings.length>1&&!strings[1].startsWith("0")&&Integer.parseInt(strings[1])>=5){
			System.out.println(1+Integer.parseInt(strings[0]));
		}else {
			System.out.println(Integer.parseInt(strings[0]));
		}
	}
}
