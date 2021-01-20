/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package huawei;

import java.util.Scanner;

/**
 * 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 主件	附件
 * 电脑	打印机，扫描仪
 * 书柜	图书
 * 书桌	台灯，文具
 * 工作椅	无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * 设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
 * v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 * 请你帮助王强设计一个满足要求的购物单。
 *
 * @author Leijian
 * @date 2020/2/12
 */
public class Main015 {
	/**
	 * 背包算法
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(" ");
		int n = Integer.parseInt(strings[0]);
		int m = Integer.parseInt(strings[1]);
		int[] p = new int[m];
		int[] v = new int[m];
		int[] q = new int[m];
		for (int i = 0; i < m; i++) {
			String[] TEMP = scanner.nextLine().split(" ");
			v[i] = Integer.parseInt(TEMP[0]);
			p[i] = Integer.parseInt(TEMP[1]) * v[i];
			q[i] = Integer.parseInt(TEMP[2]);
		}
		System.out.println(getMaxValue(v, p, q, m, n));
	}

	/**
	 * @param val    价钱
	 * @param weight 物品重要程度
	 * @param q      是主见还是附件
	 * @param n      总物品个数
	 * @param w      总钱数
	 * @return
	 */
	public static int getMaxValue(int[] val, int[] weight, int[] q, int n, int w) {
		int[][] dp = new int[n + 1][w + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= w; j++) {
				if (q[i - 1] == 0) { // 主件
					if (weight[i - 1] <= j) // 用j这么多钱去买 i 件商品 可以获得最大价值
						dp[i][j] = Math
								.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + val[i - 1]);
				} else { //附件
					if (weight[i - 1] + weight[q[i - 1]] <= j) //附件的话 加上主件一起算
						dp[i][j] = Math
								.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + val[i - 1]);
				}
			}
		}
		return dp[n][w];
	}
}
