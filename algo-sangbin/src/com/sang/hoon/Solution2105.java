package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


///문제를 이해하는데 걸린 시간 : 2분
// 문제를 갈아 엎은시간 : 1시간 2분
///걸린 시간 : 1시간 53분 6초

//오래 걸린 이유 : 그래프에서 좌표 실수 및 방향에 따른 dfs 실수
//이런 문제는 이렇게 푸는구나 하고 이해중

//오래 걸린 부분 : 망할놈의 그래프


public class Solution2105 {
	private int N;
	private int[][] map;
	private boolean[] isChecked;
	private int Answer;
	private int startx;
	private int starty;
	private int[] dx = {1,-1,-1,1};
	private int[] dy = {1,1,-1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution2105().start();
	}

	private void start() throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			isChecked = new boolean[101];
			Answer = -1;
			
			int max = 0;
			for(int i = 0; i < N; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
					if(map[i][j] > max) max = map[i][j];
				}
			}
			
			for(int i = 0; i < N-1; i++) {
				for(int j = 1; j < N-1; j++) {
					isChecked[map[i][j]] = true;
					startx = j;
					starty = i;
					dfs(i,j,0,1);
					isChecked[map[i][j]] = false;
				}
			}
			
			System.out.println("#"+t+" "+Answer);
		}
	}

	private void dfs(int y, int x, int dir, int count) {
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		//System.out.println(y +" " + x + " " + dir + " " + map[y][x]);
		//print();
		
		if(ny == starty && nx == startx && dir == 3) {
			if(Answer < count) Answer = count;
			return;
		}

		
		if(ny >= 0 && nx >= 0 && ny < N && nx < N && !isChecked[map[ny][nx]]) {
			isChecked[map[ny][nx]] = true;
			dfs(ny, nx, dir, count+1);
			isChecked[map[ny][nx]] = false;
		}
		
		if(dir<3) {
			nx = x+dx[dir+1];
			ny = y+dy[dir+1];
			if(ny == starty && nx == startx && dir == 2) {
				if(Answer < count) Answer = count;
				return;
			}
			
			if(ny >= 0 && nx >= 0 && ny < N && nx < N && !isChecked[map[ny][nx]]) {
				
				isChecked[map[ny][nx]] = true;
				dfs(ny, nx, dir+1, count+1);
				isChecked[map[ny][nx]] = false;
			}
		}
		
	}

	private void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(isChecked[map[i][j]] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
