/**
 * 걸린 시간 : 1시간 40분
 * 오래 걸린 이유 : 어떻게 하면 다음방향으로 바꾼 후에 그 방향으로 쭉 갈지 짜는것이 어려웠다.
 */

package com.dong.bin;

import java.util.Scanner;

public class SWExpert_2105_디저트카페 {
	static int N;
	static int[][] map;
	static int maxDisert;
	static int[][] visited;
	static int[] dy = { -1, 1, 1, -1 }; // 대각선 사방탐색 : 우상, 우하, 좌하, 좌상
	static int[] dx = { 1, 1, -1, -1 }; // 대각선 사방탐색 : 우상, 우하, 좌하, 좌상
	static boolean[] disert = new boolean[101];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];
			maxDisert = -1;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					dfs(r, c, r, c, 0, 0);
				}
			}
			System.out.println("#" + t + " " + maxDisert);
		}
	}

	private static void dfs(int y, int x, int startY, int startX, int direction, int disertCnt) {
		if (y == startY && x == startX && direction == 3) {
			maxDisert = Math.max(maxDisert, disertCnt);
			return;
		}

		for (int i = direction; i < 4 && i <= direction + 1; i++) { // 현재방향까지 쭉 가고 다음방향으로 틀면 끝. 단, 4방향만이니까 3까지만
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N || disert[map[ny][nx]])
				continue;
			disert[map[ny][nx]] = true;
			dfs(ny, nx, startY, startX, i, disertCnt + 1);
			disert[map[ny][nx]] = false;
		}
	}
}
