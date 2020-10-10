package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


///문제를 이해하는데 걸린 시간 : 4분 54초
///걸린 시간 : 46븐 28초

//오래 걸린 이유 : 파이프별로 통과가 가능한 파이프인지 확인을 해야한다.
//아마 지금까지 가장 쉬운 문제가 아닌가 싶다


public class Solution1953 {
	private int N, M, R, C, L;
	private int[][] map;
	private int[][] dx = {{},{0,0,-1,1},{0,0},{-1,1},{0,1},{0,1},{0,-1},{0,-1}};
	private int[][] dy = {{},{-1,1,0,0},{-1,1},{0,0},{-1,0},{1,0},{1,0},{-1,0}};
	private int Answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution1953().start();
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
			R = Integer.parseInt(stt.nextToken());
			C = Integer.parseInt(stt.nextToken());
			L = Integer.parseInt(stt.nextToken());
			
			map = new int[N][M];
			Answer = 0;
			
			for(int i = 0; i < N; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
				}
			}
			
			bfs();
			System.out.println("#"+t+" " +Answer);
		}
	}

	private void bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] isChecked = new boolean[N][M];
		q.offer(new int[] {R,C});
		isChecked[R][C] = true;
		int count = 1; 
		
		for(int t = 1; t < L; t++) {
			int q_size = q.size();
			for(int n = 0; n < q_size; n++) {
				int tmp[] = q.poll();
				int dir = map[tmp[0]][tmp[1]];
				int dir_size = dx[dir].length;
				
				for(int i = 0; i < dir_size; i++) {
					int ny = tmp[0] + dy[dir][i];
					int nx = tmp[1] + dx[dir][i];
					
					if(ny >=0 && nx >= 0 && ny < N && nx < M && !isChecked[ny][nx] && map[ny][nx] != 0) {
						//System.out.print(ny +" " + nx + " ");
						if(!check(dir, i, map[ny][nx])) continue;
						isChecked[ny][nx] = true;
						count++;
						q.offer(new int[] {ny, nx});
					}
				}
 				
			}
			

		}
		
		Answer = count;
		
		
	}

	private boolean check(int dir, int i, int target) {
		int nx = (dx[dir][i]) * -1;
		int ny = (dy[dir][i]) * -1;
		int dir_size = dx[target].length;
		for(int n = 0; n < dir_size; n++) {
			if(nx == dx[target][n] && ny == dy[target][n]) return true;
		}
		
		return false;
	}
}
