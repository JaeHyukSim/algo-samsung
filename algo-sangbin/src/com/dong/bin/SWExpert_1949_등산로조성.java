/**
 * 2시간 34분 걸림
 * 오래 걸린 이유 : 그냥 다음 가는 높이를 지금보다 1만 줄이면 되는거로 했는데 그렇게 하니까 테스트케이스 51개중 32개만 맞았다고 했다.
 * 			     그걸 80번째줄 for문 안에 넣어서 처리하게 하니 해결됐다..
 */

package com.dong.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_1949_등산로조성 {
	static int N;
	static int K;
	static int[][] map;
	static int[][] visited;
	static int maxLength;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int blac = Integer.MIN_VALUE;

	public static class Location {
		int y;
		int x;
		int hight;

		public Location(int y, int x, int hight) {
			this.y = y;
			this.x = x;
			this.hight = hight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new int[N][N];
			blac = Integer.MIN_VALUE;
			maxLength = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					blac = Math.max(blac, map[r][c]);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (blac == map[r][c]) {
						make(r, c, false, 1);
					}
				}
			}
			System.out.println("#" + t + " " + maxLength);
		}
	}

	private static void make(int startY, int startX, boolean dig, int length) {
		visited[startY][startX] = 1;
		for (int i = 0; i < 4; i++) {
			int ny = startY + dy[i];
			int nx = startX + dx[i];

			if (ny >= 0 && nx >= 0 && ny < N && nx < N && visited[ny][nx] == 0) {
				if (map[ny][nx] < map[startY][startX]) {
					make(ny, nx, dig, length + 1);
				} else if (map[ny][nx] - K < map[startY][startX] && !dig) {
					for (int k = 1; k <= K; k++) {
						map[ny][nx] -= k;
						if (map[ny][nx] < map[startY][startX])
							make(ny, nx, true, length + 1);
						map[ny][nx] += k;
					}
				}
			}
		}
		visited[startY][startX] = 0;
		maxLength = Math.max(length, maxLength);
	}
}
