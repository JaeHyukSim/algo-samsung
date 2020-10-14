/**
 * 걸린 시간 : 18분 30초 (상훈이 보이나? 보고 반성해라.)
 * 빠르게 푼 이유 : 완전탐색에 대해 알게 되었고, 다시 푸니까 훨씬 쉬웠다.
 */

package com.dong.bin;

import java.util.Scanner;

public class SWExpert_1949_등산로조성2 {
	static int N;
	static int K;
	static int[][] map;
	static int[][] visited;
	static int blanc;
	static int maxLength;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];
			blanc = Integer.MIN_VALUE;
			maxLength = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
					blanc = Math.max(blanc, map[r][c]);
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int k = 1; k <= K; k++) {
						map[r][c] -= k;
						searchBlanc();
						map[r][c] += k;
					}
				}
			}
			System.out.println("#" + t + " " + maxLength);
		}
		sc.close();
	}

	private static void searchBlanc() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == blanc) {
					dfs(r, c, 1);
				}
			}
		}
	}

	private static void dfs(int y, int x, int length) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && ny < N && nx >= 0 && nx < N && visited[ny][nx] == 0 && map[y][x] > map[ny][nx]) {
				maxLength = Math.max(maxLength, length + 1);
				dfs(ny, nx, length + 1);
			}
		}
	}
}
