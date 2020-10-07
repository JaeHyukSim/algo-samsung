package com.dong.bin;
/**
 * 52분 걸렸음.
 * 오래 걸린 이유 : 내림차순 정렬 메서드가 기억나지 않았다.
 * 뭔가 이렇게 푸는거 아닌거같은데 풀긴 풀었고 한번에 pass돼서 찜찜하다.
 * Char[] -> String 	::: String.valueOf(char[]);
 * 16진수 -> 10진수로 변환	::: Integer.parseInt("A", 16);
 * ArrayList 오름차순 정렬 	::: Collections.sort(ArrayList);
 * ArrayList 내림차순 정렬 	::: Collections.sort(ArrayList, Collections.reversOrder());
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SWExpert_5658_보물상자비밀번호 {
	static int N;
	static int K;
	static char[] numbers;
	static int resultK;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			String numString = sc.nextLine();
			numString = sc.nextLine();
			numbers = numString.toCharArray();

			ArrayList<Integer> num = new ArrayList<>();

			for (int i = 0; i < N / 4; i++) {
				String oneNum = "";
				String twoNum = "";
				String threeNum = "";
				String fourNum = "";
				for (int one = 0; one < N / 4; one++) {
					oneNum = oneNum.concat(String.valueOf(numbers[one]));
				}
				for (int two = N / 4; two < (N / 4) * 2; two++) {
					twoNum = twoNum.concat(String.valueOf(numbers[two]));
				}
				for (int three = (N / 4) * 2; three < (N / 4) * 3; three++) {
					threeNum = threeNum.concat(String.valueOf(numbers[three]));
				}
				for (int four = (N / 4) * 3; four < N; four++) {
					fourNum = fourNum.concat(String.valueOf(numbers[four]));
				}

				if (!num.contains(Integer.parseInt(oneNum, 16)))
					num.add(Integer.parseInt(oneNum, 16));
				if (!num.contains(Integer.parseInt(twoNum, 16)))
					num.add(Integer.parseInt(twoNum, 16));
				if (!num.contains(Integer.parseInt(threeNum, 16)))
					num.add(Integer.parseInt(threeNum, 16));
				if (!num.contains(Integer.parseInt(fourNum, 16)))
					num.add(Integer.parseInt(fourNum, 16));

				char[] nextNumbers = new char[N];
				for (int index = 0; index < N; index++) {
					if (index == N - 1) {
						nextNumbers[0] = numbers[index];
						break;
					}
					char temp = numbers[index];
					nextNumbers[index + 1] = temp;
				}
				for (int index = 0; index < N; index++) {
					numbers[index] = nextNumbers[index];
				}
			}
			Collections.sort(num, Collections.reverseOrder());

			resultK = num.get(K - 1);

			System.out.println("#" + t + " " + resultK);
		}
		sc.close();
	}
}
