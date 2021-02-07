package com.algo.all.muti;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'countPerms' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts INTEGER n
	 * as parameter.
	 */

	public static int countPerms(int n) {
		long[][] ans = new long[n][5];
		int modulNum = 1000000007;

		for (int i = 0; i < 5; i++) {
			ans[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			ans[i][0] = ans[i - 1][1] + ans[i - 1][2] + ans[i - 1][4];
			ans[i][1] = ans[i - 1][0] + ans[i - 1][2];
			ans[i][2] = ans[i - 1][1] + ans[i - 1][3];
			ans[i][3] = ans[i - 1][2];
			ans[i][4] = ans[i - 1][2] + ans[i - 1][3];

			for (int j = 0; j < 5; j++) {
				ans[i][j] %= modulNum;
			}
		}

		long sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += ans[n - 1][i];
		}
		return (int) (sum % modulNum);
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int result = Result.countPerms(n);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
