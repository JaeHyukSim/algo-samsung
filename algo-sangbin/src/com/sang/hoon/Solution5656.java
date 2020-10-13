package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


///문제를 이해하고 설계하는데 걸린 시간 : 14분 18초
//코드를 작성 완료하고 디버깅을 시작한 시간 : 44분 37초
///문제를 푼 시간 : 1시간 00분 39초

/*
* 1. 완탐 방법 
* 		처음 생각한 방법 : 가로를 돌면서 공을 한번씩 때려본다 -> 때린 곳이 1보다 크면 4방향으로 지우면서 진행 -> 이때, 1보다 크면 dfs(재귀) -> 아래로 정렬 -> 가장 작은 값의 map 을 가지고 반복;
* 		과정 넘버링을 하다가 깨닳아버린 상훈 (문제시작한지 10분정도)
* 		나중에 생각한 방법 : 내가 때릴수 있는 모든 x좌표 얻어옴(중복 순열) -> 그거대로 때린다 -> 때린 곳이 1보다 크면 4방향으로 지우면서 진행 -> 이때, 1보다 크면 dfs(재귀) -> 아래로 정렬 -> 남은 블럭 갯수 새서 min값 반환
* 
* 2. 시간 복잡도 계산 : W^N(순열) * W*H(dfs) = 약 370만 
* 	(이렇게 구하는게 맞는지 모르겠습니다.)
* 3. 과정 넘버링
*  가. 중복 순열
*  나. for N
*        지우기(dfs)
*        내리기()
*  다. 남은 블럭 세기 
*  라. 제일 작은 블럭 갯수 반환
*/

//오래 걸린 이유 : 내리는 개념에 대해 미숙 
//			  dropdown()함수 구현 실수


public class Solution5656 {
	private int N, W, H;
	private int[][] map;
	private int[] num;
	private int[] dx = {0,0,-1,1};
	private int[] dy = {-1,1,0,0};
	private int Answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution5656().start();
	}

	private void start() throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			stt = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stt.nextToken());
			W = Integer.parseInt(stt.nextToken());
			H = Integer.parseInt(stt.nextToken());
			map = new int[H][W];
			num = new int[N];
			Answer = Integer.MAX_VALUE;
			
			for(int i = 0; i < H; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j< W; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
				}
			}
			
			permutation(0);
			
			System.out.println("#"+t+" " + Answer);
		}
		
	}

	private void permutation(int cnt) {
		if(cnt == N) {
			int[][] tmp_map = new int[H][W];
			for(int i = 0; i < H; i++) {
				for(int j = 0; j< W; j++) {
					tmp_map[i][j] = map[i][j];
				}
			}
//			print();
			for(int i = 0; i < N; i++) {
				dropdown(num[i]);
//				print();
				replace();
//				print();
			}
			
			int min = count();
			if(Answer > min) Answer = min;
			map = tmp_map;
			return;
		}
		
		for(int i = 0; i < W; i++) {
			num[cnt] = i;
			permutation(cnt+1);
		}
		
	}

	private void print() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j< W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private int count() {
		int c = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j< W; j++) {
				if(map[i][j] != 0) c++;
			}
		}
		
		return c;
	}

	private void replace() {
		for(int j = 0; j < W; j++) {
			int index = H-1;
			for(int i = H-1; i>=0; i--) {
				if(map[i][j] != 0) {
					map[index--][j] = map[i][j];
				}
			}
			
			for(int i = index; i>=0; i--) {
				map[i][j] = 0;
			}
			
		}
	}

	private void dropdown(int target) {
		for(int i = 0; i < H; i++) {
			if(map[i][target] != 0) {
				if(map[i][target] == 1) map[i][target] = 0;
				else dfs(i, target, map[i][target]);
				break;
			}
		}
	}

	private void dfs(int y, int x, int value) {
		map[y][x] = 0;
		for(int i = 0; i <4; i++) {
			int ny = y;
			int nx = x;
			for(int c = 1; c < value; c++) {
				ny += dy[i];
				nx += dx[i];
				
				if(ny < 0 || nx < 0 || ny >= H || nx >= W) break;
				if(map[ny][nx] == 0) continue;
				if(map[ny][nx] == 1) {
					map[ny][nx] = 0;
					continue;
				}
				
				dfs(ny, nx, map[ny][nx]);
			}
		}
		
	}
}
