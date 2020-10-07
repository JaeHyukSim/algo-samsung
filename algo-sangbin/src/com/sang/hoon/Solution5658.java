package com.sang.hoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


///������ �����ϴµ� �ɸ� �ð� : 4�� 58��
// ������� ������ �ð� : 54�� 1��
///�ɸ� �ð� : 1�ð� 3�� 22��

//���� �ɸ� ���� : ������ �����ߴٰ� �����ϰ� �ٷ� ����� ���µ�, ���� ���̸鼭 �����ð��� ���� ������� ����.
// �����ð��� ������鼭 �ڵ�� ������ �ȵ�
// �� ������ �ڷᱸ���� �������� �ܼ� �������� �����ص� ������ ����ϴ� �ð��� �ʹ� �����.

//���� �ɸ� �κ� : �ڷᱸ�� ����, Comparable ����, 55��° ��


public class Solution5658 {
	private int N, K;
	private char[] map;
	private ArrayList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution5658().start();
	}

	private void start() throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("./src/com/sang/hoon/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++ ) {
			stt = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stt.nextToken());
			K = Integer.parseInt(stt.nextToken());
			map = new char[N*2];
			list = new ArrayList<Integer>();
			
			String tmp = br.readLine();
			
			for(int i = 0; i < N; i++) {
				map[i] = tmp.charAt(i);
			}
			for(int i = 0; i < N; i++) {
				map[N+i] = map[i];
			}
			
			int start = 0;
			int dir = N/4;
			while(start < dir) { // ���� ���踦 �� ���ؼ� �ϵ��ڵ� -> ����
				for(int i = 0; i < 4; i++) {
					int cur = N/4 - 1;
					int sum = 0;
					for(int j = dir*i+start; j < dir*(i+1)+start; j++) {
						int p = 0;
						if(map[j] >= 'A' && map[j] <= 'F') {
							p = 10 + (map[j]-'A'); 
						}else {
							p = map[j] - '0';
						}
//						System.out.print(Math.pow(16, cur) * p +" ");
						sum += Math.pow(16, cur--) * p;
					}
//					System.out.println(sum);
					list.add(sum);
				}
				start++;
			}
			
			Collections.sort(list, (Integer o1,Integer o2) -> {return (o1 - o2)*-1;} );
			
//			for(int i = 0; i< list.size(); i++) {
//				System.out.println(list.get(i));
//			}
			
			int count = 0;
			int memory = -1;
			start = 0;
			while(true) {
				if(count == K || start >= list.size()) break;
				
				int temp = list.get(start++);
				if(temp == memory) continue;
				
				memory = temp;
				count++;
			}
			
			System.out.println("#"+t +" " + memory);
		}
		
	}

}
