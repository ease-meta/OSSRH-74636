package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main023 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = "";
		while ((s = br.readLine()) != null) {
			int number = Integer.parseInt(s);

			// 处理身高数据
			s = br.readLine();
			String[] nums = s.split(" ");
			int[] arrForw = new int[number];
			int[] arrBack = new int[number];
			for (int i = 0; i < number; ++i) {
				arrForw[i] = Integer.parseInt(nums[i]);
				arrBack[number - 1 - i] = arrForw[i];
			}

			int[] forw = new int[number];
			int[] back = new int[number];
			for (int i = 0; i < number; ++i) {
				forw[i] = 1;
				back[i] = 1;
			}

			calNum(arrForw, forw);
			calNum(arrBack, back);

			int max = 0;
			for (int i = 0; i < number; ++i) {
				forw[i] += back[number - 1 - i];

				if (forw[i] > max)
					max = forw[i];
			}

			System.out.println(number - max + 1);
		}
	}

	public static void calNum(int[] arr, int[] dest) {
		for (int i = 1; i < arr.length; ++i) {
			for (int j = i - 1; j >= 0; --j) {
				if (arr[i] > arr[j] && dest[i] <= dest[j])
					dest[i] = dest[j] + 1;
			}
		}
	}
}