package com.dong.bin;

import java.io.*;
import java.util.*;
 
public class SWExpert_2383_점심식사시간 {
    static int P, S;
    static int answer;
     
    static void ft_solve(int[] temp, int p, ArrayList<Integer> PC, ArrayList<Integer> PR, 
            ArrayList<Integer> SC, ArrayList<Integer> SR, ArrayList<Integer> SL) {
        if (p == P) {
            int time = 0;
             
            for (int s = 0; s < S; s++) { 
                ArrayList<Integer> list = new ArrayList<>();
                 
                for (int i = 0; i < P; i++) {
                    if (temp[i] == s) { // 계단까지의 거리 
                        list.add(Math.abs(PR.get(i) - SR.get(s)) + Math.abs(PC.get(i) - SC.get(s)));
                    }
                }
                 
                Collections.sort(list);
                 
                int stairLen = SL.get(s); // 계단 길이 
                int size = list.size();
                 
                for (int i = 3; i < size; i++) { // 한 계단에 한 번에 최대 3명까지만 이용 가능
                    if (list.get(i) - list.get(i - 3) < stairLen) { // 아직 이용 불가
                        list.set(i, stairLen + list.get(i - 3)); // 이용 시간 조정
                    }
                }
                 
                time = (size > 0 && list.get(size - 1) + stairLen + 1 > time)? list.get(size - 1) + stairLen + 1 : time;
            }
             
            answer = Math.min(answer, time);
            return;
        }
         
        for (int i = 0; i < S; i++) { // 어떤 계단 사용할지
            temp[p] = i;
            ft_solve(temp, p + 1, PC, PR, SC, SR, SL);
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(bf.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(bf.readLine());
             
            ArrayList<Integer> PR= new ArrayList<>();
            ArrayList<Integer> PC= new ArrayList<>();
            ArrayList<Integer> SR= new ArrayList<>();
            ArrayList<Integer> SC= new ArrayList<>();   
            ArrayList<Integer> SL = new ArrayList<>();
             
            answer = Integer.MAX_VALUE;
             
            for (int i = 0; i < N; i++) {
                st =new StringTokenizer(bf.readLine());
                 
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                     
                    switch(value) {
                    case 0:
                        break;
                    case 1:
                        PC.add(i);
                        PR.add(j);
                        break;
                    default : 
                        SC.add(i);
                        SR.add(j);
                        SL.add(value);
                    }
                }
            }
             
            P = PC.size(); // 사람 수 
            S = SC.size(); // 계단 수 
             
            ft_solve(new int[P], 0, PC, PR, SC, SR, SL);
             
            System.out.println("#" + tc + " " + answer);
        }
    }
}