package huawei;


import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Leijian
 * @date 2020/2/11
 */
public class Main003 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			int num = scanner.nextInt();
			TreeMap treeMap = new TreeMap();
			for (int i = 0; i <num ; i++) {
				int num1 = scanner.nextInt();
				treeMap.put(num1,num1);
			}
			treeMap.forEach((k,v)->System.out.println(k));
		}
	}
}
