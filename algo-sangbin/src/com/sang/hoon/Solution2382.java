package samsung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


///문제를 이해하는데 걸린 시간 : 5분 7초
// 디버깅을 시작한 시간 : 45분 10초
///걸린 시간 : 51분 57초

//오래 걸린 이유 : 문제가 3개가 합쳐질때를 설명해주지 않아 헷갈렸음
//이런 문제는 이렇게 푸는구나 하고 이해중

//오래 걸린 부분 : 문제의 이해


public class Solution2382 {
	class Bio{
		int dir;
		int size;
	}
	
	private int N, M, K;
	private Bio[][] map;
	private int[] dx = {0,0,0,-1,1};
	private int[] dy = {0,-1,1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution2382().start();
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
			K = Integer.parseInt(stt.nextToken());
			
			map = new Bio[N][N];
			
			Queue<int[]> q = new LinkedList<>();
			for(int i = 0; i < K; i++) {
				stt = new StringTokenizer(br.readLine());
				Bio b = new Bio();
				int y = Integer.parseInt(stt.nextToken());
				int x = Integer.parseInt(stt.nextToken());
				b.size = Integer.parseInt(stt.nextToken());
				b.dir = Integer.parseInt(stt.nextToken());
				
				map[y][x] = b;
				q.offer(new int[] {y,x});
			}
			
			
			for(int time = 0; time< M; time++) {
				Bio[][] tMap = new Bio[N][N];
				int[][] max = new int[N][N];
				
				int qSize = q.size();
				for(int n = 0; n < qSize; n++) {
					int[] tmp = q.poll();
					Bio b = map[tmp[0]][tmp[1]];
					int ny = tmp[0]+dy[b.dir];
					int nx = tmp[1]+dx[b.dir];
					
					if((nx == 0 || nx == N-1) || (ny == 0 || ny == N-1)) {
						b.size /= 2;
						if(b.dir == 1 || b.dir == 2) b.dir = (b.dir == 1 ? 2 : 1);
						else b.dir = (b.dir == 3 ? 4 : 3);
					}
					
					if(tMap[ny][nx] != null) {
						Bio f = new Bio();
						if(b.size > max[ny][nx]) {
							max[ny][nx] = b.size;
							f.dir = b.dir;
						} 
						else f.dir = tMap[ny][nx].dir;
						f.size = b.size + tMap[ny][nx].size;
						b = f;
						tMap[ny][nx] = b;
					}else {
						max[ny][nx] = b.size;
						tMap[ny][nx] = b;
						q.offer(new int[] {ny, nx});
					}
//					
//					for(int i = 0; i < N; i++) {
//						for(int j=0 ; j < N; j++) {
//							System.out.print(max[i][j] +"\t");
//						}
//						System.out.println();
//					}
//					System.out.println();
				}
				
				map = tMap;
				//print();
			}
			
			int Answer = 0;
			while(!q.isEmpty()) {
				int[] tmp = q.poll();
				Answer += map[tmp[0]][tmp[1]].size;
			}
			
			System.out.println("#"+t+" "+Answer);
		}
	}

	private void print() {
//		for(int i = 0; i < N; i++) {
//			for(int j=0 ; j < N; j++) {
//				if(map[i][j] == null) System.out.print("null\t");
//				else System.out.print(map[i][j].size+"\t");
//			}
//			System.out.println();
//		}
//		System.out.println();

	}
}
