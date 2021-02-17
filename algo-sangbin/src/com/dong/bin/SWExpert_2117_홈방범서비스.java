package com.dong.bin;

import java.util.Scanner;

public class SWExpert_2117_홈방범서비스 {
	int N;
	int map[][];
	int M;
	int maxHome;

	public static void main(String[] args) {
		SWExpert_2117_홈방범서비스 m = new SWExpert_2117_홈방범서비스();
		m.start();
	}

	private void start() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			maxHome = 1;
			
			for (int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			for(int k = 2; k <= N+1; k++) {
				for (int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						service(r, c, k);
					}
				}
			}
			
			System.out.println("#" + t + " " + maxHome);
		}
		sc.close();
	}

	private void service(int y, int x, int width) {
		int homeCnt = 0;
		int end = width*2-1;
		int startY = y - (width - 1);
		int startX = x - (width - 1);
		int pay = 0;
		
		for(int i = 0; i < end; i++) {
			for(int j = 0; j < end; j++) {
				if(j >= Math.abs(end/2 - i) && j < end - Math.abs(end/2 - i)) {
					int ny = startY + i;
					int nx = startX + j;
					if(ny >= 0 && nx >= 0 && ny < N && nx < N && map[ny][nx] == 1)
						homeCnt++;
				}
			}
		}
		
		if (homeCnt > maxHome) {
			pay = width * width + (width-1) * (width-1);
			if (pay <= homeCnt * M)
				maxHome = homeCnt;
		}
	}
}
