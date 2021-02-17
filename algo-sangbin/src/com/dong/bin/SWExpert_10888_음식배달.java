package com.dong.bin;

import java.util.ArrayList;
import java.util.Scanner;

public class SWExpert_10888_음식배달 {
	int N;
	int[][] map;
	boolean[] selected;
	int minCost;
	ArrayList<House> food;
	ArrayList<House> house;

	public static void main(String[] args) {
		SWExpert_10888_음식배달 m = new SWExpert_10888_음식배달();
		m.start();
	}

	private class House {
		int y;
		int x;

		private House(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private void start() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			minCost = Integer.MAX_VALUE;
			food = new ArrayList<>();
			house = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
					if (map[r][c] == 1)
						house.add(new House(r, c));
					else if (map[r][c] > 1)
						food.add(new House(r, c));
				}
			}

			int size = food.size();
			selected = new boolean[size];

			search(0);

			System.out.println("#" + t + " " + minCost);
		}
		sc.close();
	}

	private void search(int index) {

		boolean check = false;
		for (int i = 0; i < selected.length; i++) {
			if (selected[i])
				check = true;
		}

		if (check) {
			int result = 0;
			for (int j = 0; j < food.size(); j++)
				if (selected[j])
					result += map[food.get(j).y][food.get(j).x];
			for (int i = 0; i < house.size(); i++) {
				int dist = 0;
				int minDist = Integer.MAX_VALUE;
				for (int j = 0; j < food.size(); j++) {
					if (selected[j]) {
						dist = distance(house.get(i), food.get(j));
						if (minDist > dist)
							minDist = dist;
					}
				}
				result += minDist;
			}

			if (minCost > result)
				minCost = result;
		}

		if (index >= selected.length)
			return;

		selected[index] = true;
		search(index + 1);
		if (selected.length == 1)
			return;
		selected[index] = false;
		search(index + 1);
	}

	private int distance(House a, House b) {
		return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
	}
}
