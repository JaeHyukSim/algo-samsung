/**
 * 하고있는중 : 2시간 3분. 다시 처음부터 해야겠다.
 */

package com.dong.bin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWExpert_2382_미생물격리 {
	static int N; // 가로세로 길이
	static int M; // 시간
	static int K; // 미생물 군집 수
	static int[][] map;
	static bug[] bugs;
	static int[] dy = { 0, -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int aliveCnt;

	public static class bug {
		int no;
		int y;
		int x;
		int cnt;
		int dir;

		public bug(int no, int y, int x, int cnt, int dir) {
			this.no = no;
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.dir = dir;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			bugs = new bug[K + 1];
			aliveCnt = 0;

			for (int k = 0; k < K; k++) {
				bugs[k + 1] = new bug(k + 1, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
				map[bugs[k + 1].y][bugs[k + 1].x] = k + 1;
			}

			for (int i = 0; i < M; i++) {
				move();
			}

			for (int i = 1; i <= K; i++) {
				aliveCnt += bugs[i].cnt;
			}

			System.out.println("#" + t + " " + aliveCnt);
		}
	}

	private static void move() {
		Queue<bug> queue = new LinkedList<bug>();

		for (int k = 1; k <= K; k++) {
			bug temp = bugs[k];
			if (temp.cnt == 0)
				continue;

			map[temp.y][temp.x] = 0;

			switch (temp.dir) {
			case 1:
				temp.y--;
				break;
			case 2:
				temp.y++;
				break;
			case 3:
				temp.x--;
				break;
			case 4:
				temp.x++;
				break;
			}

			if (temp.y == 0 || temp.y == N - 1 || temp.x == 0 || temp.x == N - 1) {
				if (temp.dir == 1)
					temp.dir = 2;
				else if (temp.dir == 2)
					temp.dir = 1;
				else if (temp.dir == 3)
					temp.dir = 4;
				else if (temp.dir == 4)
					temp.dir = 3;
				temp.cnt = temp.cnt / 2;
			}

			queue.offer(new bug(k, temp.y, temp.x, temp.cnt, temp.dir));
		}

		while (!queue.isEmpty()) {
			bug temp = queue.poll();
			if (map[temp.y][temp.x] == 0) {
				map[temp.y][temp.x] = temp.no;
				bugs[temp.no].y = temp.y;
				bugs[temp.no].x = temp.x;
				bugs[temp.no].cnt = temp.x;
				bugs[temp.no].dir = temp.dir;
			} else {
				bugs[map[temp.y][temp.x]].cnt += temp.cnt;
				bugs[temp.no].y = 0;
				bugs[temp.no].x = 0;
				bugs[temp.no].cnt = 0;
				bugs[temp.no].dir = 0;
			}
		}
	}
}
