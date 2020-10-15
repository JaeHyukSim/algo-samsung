package com.dong.bin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_5656_벽돌깨기 {
	int N;
	int W;
	int H;
	int[][] map;
	int minBricks;
	int[] dy = { -1, 1, 0, 0 };
	int[] dx = { 0, 0, -1, 1 };
	Queue<Brick> queue = new LinkedList<>();

	public class Brick {
		int y;
		int x;
		int bomb;

		public Brick(int y, int x, int bomb) {
			this.y = y;
			this.x = x;
			this.bomb = bomb;
		}
	}

	public static void main(String[] args) {
		SWExpert_5656_벽돌깨기 m = new SWExpert_5656_벽돌깨기();
		m.start();
	}

	private void start() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			map = new int[H][W];
			minBricks = Integer.MAX_VALUE;

			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			nCheck(0, map);

			System.out.println("#" + t + " " + minBricks);
		}
	}

	private void nCheck(int ballCnt, int[][] cMap) {
		if (ballCnt == N) {
			int bricks = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (cMap[r][c] != 0)
						bricks++;
				}
			}
			minBricks = Math.min(bricks, minBricks);
			return;
		}

		for (int i = 0; i < W; i++) {
			int[][] copyMap = getCopyMap(cMap);
			dropBall(i, copyMap);
//			printMap(copyMap);
			nCheck(ballCnt + 1, copyMap);
		}
	}

	private int[][] getCopyMap(int[][] originMap) {
		int[][] copyMap = new int[H][W];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				copyMap[r][c] = originMap[r][c];
			}
		}
		return copyMap;
	}

	private void printMap(int[][] cMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				System.out.print(cMap[r][c] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private void dropBall(int x, int[][] cMap) {
		for (int r = 0; r < H; r++) {
			if (cMap[r][x] != 0) {
				brickBreak(r, x, cMap);
				return;
			}
		}
	}

	private void brickBreak(int y, int x, int[][] cMap) {
		queue.offer(new Brick(y, x, cMap[y][x]));
		cMap[y][x] = 0;

		while (!queue.isEmpty()) {
			Brick temp = queue.poll();

			for (int i = 1; i < temp.bomb; i++) {
//				System.out.println(temp.bomb);
				for (int j = 0; j < 4; j++) {
					int ny = temp.y + i * dy[j];
					int nx = temp.x + i * dx[j];
//					System.out.println("ny nx : " + ny + " " + nx);

					if (ny < 0 || nx < 0 || ny >= H || nx >= W)
						continue;
					if (cMap[ny][nx] == 0)
						continue;
					queue.offer(new Brick(ny, nx, cMap[ny][nx]));
					cMap[ny][nx] = 0;
//					printMap(cMap);
//					System.out.println("깼당");
				}
			}
		}
		downBricks(cMap);
	}

	private void downBricks(int[][] cMap) {
		for (int c = 0; c < W; c++) {
			int point = H - 1;
			for (int r = H - 1; r >= 0; r--) {
				if (cMap[r][c] != 0) {
					int temp = cMap[r][c];
					cMap[r][c] = 0;
					cMap[point--][c] = temp;
				}
			}
		}
	}
}
