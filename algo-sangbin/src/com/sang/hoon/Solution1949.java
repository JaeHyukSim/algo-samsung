package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


///문제를 이해하는데 걸린 시간 : 1분 5초
//디버깅을 시작한 시간 : 30분 30초
///걸린 시간 : 2시간 35분 10초

//오래 걸린 이유 : 그냥 그래프가 싫은가..
//디버깅이 너무 오래걸려요..

//오래 걸린 부분 : 망할놈의 디버깅 ㅠㅠ


public class Solution1949 {
	private int N, K;
	private int[][] map;
	private boolean[][] isChecked;
	private int Answer;
	private int[] dx = {0,0,-1,1};
	private int[] dy = {-1,1,0,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution1949().start();
	}

	private void start() throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			stt = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stt.nextToken());
			K = Integer.parseInt(stt.nextToken());
			map = new int[N][N];
			isChecked = new boolean[N][N];
			Answer = 1;
			
			int max = 0;
			for(int i = 0; i < N; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
					if(map[i][j] > max) max = map[i][j];
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == max) {
						isChecked[i][j] = true;
						dfs(i, j, 1, 1);
						isChecked[i][j] = false;
					}
				}
			}
			
			System.out.println("#"+t+" " + Answer);
			
		}
		
	}

	private void dfs(int y, int x, int distroy, int deep) {
		if(Answer < deep) Answer = deep;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(ny >=0 && nx >= 0 && ny < N && nx < N && !isChecked[ny][nx]) {
				
				if(map[ny][nx] < map[y][x]) {
					
					isChecked[ny][nx] = true;
					dfs(ny, nx, distroy, deep+1);
					isChecked[ny][nx] = false;
				}else if(distroy == 1){
					
					if(map[ny][nx] - K < map[y][x]) {
						int temp = map[ny][nx];
						isChecked[ny][nx] = true;
						map[ny][nx] = map[y][x] - 1; 
						dfs(ny, nx, distroy-1, deep+1);
						map[ny][nx] = temp;
						isChecked[ny][nx] = false;
					}
					
				}
			}
			
		}
	}

	private void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(isChecked[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
