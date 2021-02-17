package com.dong.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_4014_활주로건설 {
	int N;
	int X;
	int[][] map;
	int count;

	public static void main(String[] args) throws IOException {
		SWExpert_4014_활주로건설 m = new SWExpert_4014_활주로건설();
		m.service();
	}

	private void service() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			count = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			process();
			System.out.println("#" + t + " " + count);
		}
	}

	private void process() {
		for (int i = 0; i < N; i++) {
			if (makeRoadByRow(i))
				++count;
		}
		for (int i = 0; i < N; i++) {
			if (makeRoadByCol(i))
				++count;
		}
	}

	// 행 단위로 활주로 건설
	private boolean makeRoadByRow(int y) {
		int beforeHeight = map[y][0]; // 이전칸의 높이
		int size = 1; // 평탄한 지형의 길이

		for (int x = 1; x < N; x++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if (beforeHeight == map[y][x])
				++size;

			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			else if (beforeHeight + 1 == map[y][x]) {
				if (size < X)
					return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1; // 새로운 높이의 평탄한 지형 길이 1부터 시작

				// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if (beforeHeight - 1 == map[y][x]) {
				int cnt = 0;

				for (int i = x; i < N; i++) {
					if (map[y][i] != beforeHeight - 1)
						break;
					cnt++; // 이전칸의 높이와 1 차이 낮은 연속된 평탄화 지형의 길이를 카운트
				}
				if (cnt < X)
					return false;

				x += X - 1; // 경사로 다음 칸의 위치로 제어
				beforeHeight--;
				size = 0;

				// 4. 높이가 2 이상 차이나는 경우
			} else
				return false;
		}
		return true;
	}

	// 열단위로 활주로 건설
	private boolean makeRoadByCol(int x) {
		int beforeHeight = map[0][x]; // 이전칸의 높이
		int size = 1; // 평탄한 지형의 길이

		for (int y = 1; y < N; y++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if (beforeHeight == map[y][x])
				++size;

			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			else if (beforeHeight + 1 == map[y][x]) {
				if (size < X)
					return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1; // 새로운 높이의 평탄한 지형 길이 1부터 시작

				// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if (beforeHeight - 1 == map[y][x]) {
				int cnt = 0;

				for (int i = y; i < N; i++) {
					if (map[i][x] != beforeHeight - 1)
						break;
					cnt++; // 이전칸의 높이와 1 차이 낮은 연속된 평탄화 지형의 길이를 카운트
				}
				if (cnt < X)
					return false;

				y += X - 1; // 경사로 다음 칸의 위치로 제어
				beforeHeight--;
				size = 0;

				// 4. 높이가 2 이상 차이나는 경우
			} else
				return false;
		}
		return true;
	}
}
