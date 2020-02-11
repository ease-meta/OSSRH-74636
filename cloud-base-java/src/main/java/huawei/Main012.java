package huawei;


import java.util.Scanner;

/**
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 * @author Leijian
 * @date 2020/2/12
 */
public class Main012 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		System.out.println(reverse(str).trim());
	}

	/**
	 * 反转句子
	 *
	 * @param sentence 原句子
	 * @return 反转后的句子
	 */
	public static String reverse(String sentence) {
		String[] strings = sentence.split(" ");
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = strings.length - 1; i >= 0; i--) {
			stringBuffer.append(strings[i]).append(" ");
		}
		return stringBuffer.toString();
	}
}
