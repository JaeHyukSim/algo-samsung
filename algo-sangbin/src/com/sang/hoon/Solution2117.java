package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


///문제를 이해하고 설계하는데 걸린 시간 : 18분 18초
// 코드를 작성 완료하고 디버깅을 시작한 시간 : 32분 51초
///문제를 푼 시간 : 1시간 05분 45초

/*
 * 1. 완탐 방법 : 모든 배열을 돌면서 1~k의 최대크기만큼 이익 계산 ( <- 이익계산 X, 집 갯수 O)
 * 2. 시간 복잡도 계산 : N * N * N * (bfs = 4 * (n(n-1)/2))  == 약 6천만 - 3초라 충분
 * 3. 과정 넘버링
 *  가. 배열만큼 돈다
 *  나. 한 좌표를 1~K 최대 크기만큼 bfs
 *  다. bfs - for(0~k-1까지 퍼지기) 
 *         -  집있으면 세주고
 *         -  이익금 반환
 *  라. 이익금이 0 이상이면 집 반환
 */


//오래 걸린 이유 : 출력을 해야하는 것을 잘못 알고 있었음(최대 이익 출력인줄...)
//			   K값의 범위가 나와있지 않아서, 어디서부터 어떻게 얼만큼 설정해야하는지 고민
//			   k값의 범위를 찾았지만 오류가 있었음 (N+1만큼 검사해야 했음...;;;)

//오래 걸린 부분 : 망할놈의 디버깅 ㅠㅠ


public class Solution2117 {
	private int N, M, house;
	private int[][] map;
	private boolean[][] isChecked;
	private int[] dy = {-1,1,0,0};
	private int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution2117().start();
	}

	private void start() throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			stt = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stt.nextToken());
			M = Integer.parseInt(stt.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
				}
			}
			
			int Answer = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 1; k <= N+1; k++) {
						isChecked = new boolean[N][N];
						if(bfs(i, j, k) >= 0 && Answer < house) {
							//if(house == 399) print();
//							System.out.println(house);
							Answer = house;
						}
					}
				}
			}
			
			System.out.println("#" +t + " " + Answer );
			
		}
	}

	private void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(isChecked[i][j] +"\t");
			}
			System.out.println();
		}
	}

	private int bfs(int y, int x, int k) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {y,x});
		isChecked[y][x] = true;
		boolean[][] c = new boolean[N][N];		
		
		house = 0;
		for(int rutin = 0; rutin < k; rutin++) {
			int size = q.size();
			for(int n = 0; n < size; n++) {
				int[] tmp = q.poll();
				
				if(map[tmp[0]][tmp[1]] == 1) {
					house++;
					c[tmp[0]][tmp[1]] = true;
				}	
				
				for(int i = 0; i < 4; i++) {
					int ny = tmp[0] + dy[i];
					int nx = tmp[1] + dx[i];
					
					if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if(isChecked[ny][nx]) continue;
					
					isChecked[ny][nx] = true;
					q.offer(new int[] {ny, nx});
					
				}
			}
		}
//	System.out.println(house + " " + ((k*k) + (k-1) * (k-1) + " " + ((house * M) - ((k*k) + (k-1) * (k-1)))) );
		return ((house * M) - ((k*k) + (k-1) * (k-1))); 
		
	}

}
