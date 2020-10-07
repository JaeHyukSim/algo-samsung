package com.sang.hoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


///������ �����ϴµ� �ɸ� �ð� : 8�� 48��
//������� ������ �ð� : 41�� 33��
///�ɸ� �ð� : 59�� 31��

//���� �ɸ� ���� : � �ڷᱸ���� ����ؾ� �ϴ���, �����ϰ� ã�ƺ��ٰ� �ð��� �ʾ�����. ��� ������ �ñ��ϴ�. 
//����뿡 10�йۿ� �ț��ٴ°Ϳ� �ѵ�
//�� ������ �ڷᱸ���� �������� �ܼ� �������� �����ص� ������ ����ϴ� �ð��� �ʹ� �����.

//���� �ɸ� �κ� : �ڷᱸ�� ����(deque, map), ArrayList �迭, ���� ���� ��� ã�ƺ��� 




public class Solution4013 {
	private int K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution4013().start();
	}

	private void start() throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("./src/com/sang/hoon/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;

		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			ArrayList<Integer> list[] = new ArrayList[5];

			K = Integer.parseInt(br.readLine());

			for(int i = 1; i < 5; i++) {
				list[i] = new ArrayList<Integer>();
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++) {
					list[i].add(Integer.parseInt(stt.nextToken()));
				}
			}
			
			for(int k = 0; k < K; k++) {
				stt = new StringTokenizer(br.readLine());
				int target = Integer.parseInt(stt.nextToken());
				int round = Integer.parseInt(stt.nextToken());
				boolean[] isV = new boolean[5];
				
				Queue<int[]> q = new LinkedList<int[]>();
				Queue<int[]> r = new LinkedList<int[]>();
				q.offer(new int[] {target, round});
				r.offer(new int[] {target, round});
				isV[target] = true;
				
				while(!q.isEmpty()) {
					int tmp[] = q.poll();
//					System.out.println(tmp[0] +" " + tmp[1]);

					int dx = tmp[0] - 1;
					
					if(dx > 0 && !isV[dx] && list[tmp[0]].get(6) != list[dx].get(2)) {
						q.offer(new int[] {dx, tmp[1]*-1});
						r.offer(new int[] {dx, tmp[1]*-1});
						isV[dx] = true;
					}
					
					dx = tmp[0] + 1;
					
					if(dx < 5 && !isV[dx] && list[tmp[0]].get(2) != list[dx].get(6)) {
						q.offer(new int[] {dx, tmp[1]*-1});
						r.offer(new int[] {dx, tmp[1]*-1});
						isV[dx] = true;
					}
					
				}
				
				while(!r.isEmpty()) {
					int tmp[] = r.poll();
//					System.out.println(tmp[0] +" " + tmp[1]);
					if(tmp[1] == 1) {
						int temp = list[tmp[0]].get(7);
						for(int i = 7; i > 0; i--) {
							list[tmp[0]].set(i, list[tmp[0]].get(i-1));
						}
						list[tmp[0]].set(0, temp);
					}else {
						int temp = list[tmp[0]].get(0);
						for(int i = 1; i < 8; i++) {
							list[tmp[0]].set(i-1, list[tmp[0]].get(i));
						}
						list[tmp[0]].set(7, temp);
					}
					
//					for(int i = 1; i < 5; i++) {
//						for(int j = 0; j < 8; j++) {
//							System.out.print(list[i].get(j));
//						}
//						System.out.println();
//					}
				}
				
				
			}
			
			int sum = 0;
			for(int i = 1; i < 5; i++) {
				sum += list[i].get(0) * Math.pow(2, i-1);
			}
			System.out.println("#"+testCase+" " +sum);
			
		}
		
		
	}
	
	
}
