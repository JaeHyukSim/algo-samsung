package com.dong.bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_4014_활주로건설2 {
	int N;
	int X;
	int[][] map;
	int[][] rmap;
	int count;

	public static void main(String[] args) throws IOException {
		SWExpert_4014_활주로건설2 m = new SWExpert_4014_활주로건설2();
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
			rmap = new int[N][N];
			count = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					rmap[c][r] = map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			process();
			System.out.println("#" + t + " " + count);
		}
	}

	private void process() {
		for (int i = 0; i < N; i++) {
			if (makeRoad(map[i])) // 행 고정처리 효과
				++count;
			if (makeRoad(rmap[i])) // 열 고정처리 효과
				++count;
		}
	}

	// 행 단위로 활주로 건설
	private boolean makeRoad(int[] road) {
		int beforeHeight = road[0]; // 이전칸의 높이
		int size = 1; // 평탄한 지형의 길이

		for (int i = 1; i < N; i++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if (beforeHeight == road[i])
				++size;

			// 2. 현재칸이 이전칸보다 높이가 1 높은 경우 (오르막 경사로 설치 가능한지 체크)
			else if (beforeHeight + 1 == road[i]) {
				if (size < X)
					return false; // 활주로 건설 불가
				beforeHeight++;
				size = 1; // 새로운 높이의 평탄한 지형 길이 1부터 시작

				// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우 (내리막 경사로 설치 가능한지 체크)
			} else if (beforeHeight - 1 == road[i]) {
				int cnt = 0;

				for (int j = i; j < N; j++) {
					if (road[j] != beforeHeight - 1)
						break;
					cnt++; // 이전칸의 높이와 1 차이 낮은 연속된 평탄화 지형의 길이를 카운트
				}
				if (cnt < X)
					return false;

				i += X - 1; // 경사로 다음 칸의 위치로 제어
				beforeHeight--;
				size = 0;

				// 4. 높이가 2 이상 차이나는 경우
			} else
				return false;
		}
		return true;
	}
}
