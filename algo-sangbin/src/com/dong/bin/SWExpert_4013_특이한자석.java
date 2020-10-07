package com.dong.bin;
/**
 * 1시간 3분 걸림
 * 오래 걸린 이유 : index값 오타 하나를 찾지 못해서(wheel[3][2]->wheel[3][6]) 헤맸다. 아무래도 깔끔하지 못하게 짜서 그렇다. 
 */


import java.util.Scanner;

public class SWExpert_4013_특이한자석 {
	static int K;
	static int[][] wheel = new int[4][8];
	static int[] score = { 1, 2, 4, 8 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			K = sc.nextInt();
			int sum = 0;

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					wheel[i][j] = sc.nextInt(); // 0이면 N극, 1이면 S극
				}
			}

			for (int k = 0; k < K; k++) {
				int wheelNum = sc.nextInt();
				int direction = sc.nextInt(); // 1이면 시계, -1이면 반시계

				checkTurn(wheelNum, direction);
			}

			for (int i = 0; i < 4; i++) {
				if (wheel[i][0] == 1)
					sum += score[i];
			}

			System.out.println("#" + t + " " + sum);
		}
		sc.close();
	}

	private static void checkTurn(int wheelNum, int direction) {
		if (wheelNum == 1) {
			if (wheel[0][2] != wheel[1][6]) {
				if (wheel[1][2] != wheel[2][6]) {
					if (wheel[2][2] != wheel[3][6]) {
						if (direction == 1)
							turn(3, -1);
						else
							turn(3, 1);
					}
					if (direction == -1)
						turn(2, -1);
					else
						turn(2, 1);
				}
				if (direction == 1)
					turn(1, -1);
				else
					turn(1, 1);
			}
			if (direction == -1)
				turn(0, -1);
			else
				turn(0, 1);
		} else if (wheelNum == 2) {
			if (wheel[0][2] != wheel[1][6]) {
				if (direction == 1)
					turn(0, -1);
				else
					turn(0, 1);
			}
			if (wheel[1][2] != wheel[2][6]) {
				if (wheel[2][2] != wheel[3][6]) {
					if (direction == -1)
						turn(3, -1);
					else
						turn(3, 1);
				}
				if (direction == 1)
					turn(2, -1);
				else
					turn(2, 1);
			}
			if (direction == 1)
				turn(1, 1);
			else
				turn(1, -1);
		} else if (wheelNum == 3) {
			if (wheel[1][2] != wheel[2][6]) {
				if (wheel[0][2] != wheel[1][6]) {
					if (direction == 1)
						turn(0, 1);
					else
						turn(0, -1);
				}
				if (direction == -1)
					turn(1, 1);
				else
					turn(1, -1);
			}
			if (wheel[2][2] != wheel[3][6]) {
				if (direction == -1)
					turn(3, 1);
				else
					turn(3, -1);
			}
			if (direction == 1)
				turn(2, 1);
			else
				turn(2, -1);
		} else if (wheelNum == 4) {
			if (wheel[2][2] != wheel[3][6]) {
				if (wheel[1][2] != wheel[2][6]) {
					if (wheel[0][2] != wheel[1][6]) {
						if (direction == -1)
							turn(0, 1);
						else
							turn(0, -1);
					}
					if (direction == 1)
						turn(1, 1);
					else
						turn(1, -1);
				}
				if (direction == -1)
					turn(2, 1);
				else
					turn(2, -1);
			}
			if (direction == 1)
				turn(3, 1);
			else
				turn(3, -1);
		}

	}

	private static void turn(int wheelNum, int direction) {
		int[] nextWheel = new int[8];
		if (direction == -1) {
			for (int i = 7; i >= 0; i--) {
				int temp = wheel[wheelNum][i];
				if (i == 0) {
					nextWheel[7] = temp;
					continue;
				}
				nextWheel[i - 1] = temp;
			}
		} else {
			for (int i = 0; i < 8; i++) {
				int temp = wheel[wheelNum][i];
				if (i == 7) {
					nextWheel[0] = temp;
					continue;
				}
				nextWheel[i + 1] = temp;
			}
		}
		for (int i = 0; i < 8; i++) {
			wheel[wheelNum][i] = nextWheel[i];
		}
	}
}