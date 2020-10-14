/**
 * 걸린 시간 : 2시간 43분
 * 오래 걸린 이유 : 아무래도 이렇게 푸는게 아닌거같아서 다른 방법을 생각하는데 오래걸렸고 그렇게 하지 못해서 그냥 난잡하게 풀었다.
 */

package com.dong.bin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_1953_탈주범검거 {
	static int N;
	static int M;
	static int R;
	static int C;
	static int L;
	static int map[][];
	static boolean visited[][];
	static int stateCnt;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static class State {
		int y;
		int x;

		public State(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			stateCnt = 0;

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			bfs();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (visited[r][c])
						stateCnt++;
				}
			}

			System.out.println("#" + t + " " + stateCnt);
		}
		sc.close();
	}

	private static void bfs() {
		Queue<State> queue = new LinkedList<>();
		queue.offer(new State(R, C));
		visited[R][C] = true;

		int time = 1;
		while (!queue.isEmpty()) {
			if (time >= L)
				break;
			int queueStep = queue.size();
			for (int step = 0; step < queueStep; step++) {

				State temp = queue.poll();

				if (map[temp.y][temp.x] == 1)
					for (int i = 0; i < 4; i++) {
						int ny = temp.y + dy[i];
						int nx = temp.x + dx[i];

						if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx])
							continue;
						if (map[ny][nx] == 0)
							continue;
						if (i == 0)
							if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
						if (i == 1)
							if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
						if (i == 2)
							if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
						if (i == 3)
							if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
					}

				if (map[temp.y][temp.x] == 2)
					for (int i = 0; i < 2; i++) {
						int ny = temp.y + dy[i];
						int nx = temp.x + dx[i];

						if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx])
							continue;
						if (map[ny][nx] == 0)
							continue;
						if (i == 0)
							if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
						if (i == 1)
							if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
								queue.offer(new State(ny, nx));
								visited[ny][nx] = true;
							}
					}

				if (map[temp.y][temp.x] == 3) {
					int ny = temp.y + dy[2];
					int nx = temp.x + dx[2];

					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
					
					ny = temp.y + dy[3];
					nx = temp.x + dx[3];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}

				if (map[temp.y][temp.x] == 4) {
					int ny = temp.y + dy[0];
					int nx = temp.x + dx[0];

					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}

					ny = temp.y + dy[3];
					nx = temp.x + dx[3];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}

				if (map[temp.y][temp.x] == 5) {
					int ny = temp.y + dy[1];
					int nx = temp.x + dx[1];

					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}

					ny = temp.y + dy[3];
					nx = temp.x + dx[3];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}

				if (map[temp.y][temp.x] == 6) {
					int ny = temp.y + dy[1];
					int nx = temp.x + dx[1];

					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}

					ny = temp.y + dy[2];
					nx = temp.x + dx[2];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}

				if (map[temp.y][temp.x] == 7) {
					int ny = temp.y + dy[0];
					int nx = temp.x + dx[0];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
					
					
					ny = temp.y + dy[2];
					nx = temp.x + dx[2];
					if (!(ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)) {
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
							queue.offer(new State(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}
			}
			time++;
		}
	}
}
