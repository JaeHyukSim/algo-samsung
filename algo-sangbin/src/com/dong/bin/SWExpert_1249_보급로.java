package com.dong.bin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWExpert_1249_보급로 {
	int N;
	int map[][];
	int visited[][];
	int minTime;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };

	public class Location {
		int y;
		int x;
		int time;

		public Location(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		SWExpert_1249_보급로 m = new SWExpert_1249_보급로();
		m.service();
	}

	private void service() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			minTime = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				char[] line = br.readLine().toCharArray();
				for (int c = 0; c < N; c++) {
					map[r][c] = line[c] - '0';
					visited[r][c] = Integer.MAX_VALUE;
				}
			}

			restore();

			System.out.println("#" + t + " " + visited[N-1][N-1]);
		}
	}

	private void restore() {
		Queue<Location> queue = new LinkedList<>();
		queue.offer(new Location(0, 0, 0));
		visited[0][0] = 0;

		while (!queue.isEmpty()) {
			Location temp = queue.poll();

			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];

				if (ny == 0 && nx == 0)
					continue;
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;
				if (visited[ny][nx] <= temp.time + map[ny][nx])
					continue;

				queue.offer(new Location(ny, nx, temp.time + map[ny][nx]));
				visited[ny][nx] = temp.time + map[ny][nx];
			}
		}
	}
}
