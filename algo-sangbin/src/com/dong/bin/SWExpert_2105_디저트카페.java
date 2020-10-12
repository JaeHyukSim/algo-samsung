package com.dong.bin;

import java.util.Scanner;

public class SWExpert_2105_디저트카페 {
	static int N;
	static int[][] map;
	static int maxDisert;
	static int[][] visited;
	static int[] dy = { -1, 1, 1, -1 }; // 대각선 사방탐색 : 우상, 우하, 좌하, 좌상
	static int[] dx = { 1, 1, -1, -1 }; // 대각선 사방탐색 : 우상, 우하, 좌하, 좌상

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];
			maxDisert = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
//					dfs(r, c, 0);
				}
			}
			System.out.println("#" + t + " " + maxDisert);
		}
	}

	private static void dfs(int y, int x, int direction, int disert) {
		if (direction == 1) { // 우상 
			int ny = y + dy[1];
			int nx = x + dx[1];
			
			if(ny < 0 || ny >= N || nx < 0 || nx >= N)
//				dfs()
			
		}
//		for (int i = 0; i < 4; i++) {
//			int ny = y + dy[i];
//			int nx = x + dx[i];
//			
//			if(ny < 0 || ny >= N || nx < 0 || nx >= N)
//				continue;
//		}
	}
}
