package com.ssafy.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도 : O(NlogN)
public class LIS_DP {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(input.readLine());
		int[] arr = new int[N]; // 수
		int[] LIS = new int[N]; // DP:해당 길이를 만족하는 자리에 오는 수의 최솟값

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}

		int size = 0;

		for (int i = 0; i < N; i++) {
			// fromIndex : inclusive(포함)
			// toIndex : inclusive(미포함)
			int pos = Arrays.binarySearch(LIS, 0, size, arr[i]);// LIS에 0-size만큼 바이너리 탐색해서 arr[i]위치 찾음
			if (pos >= 0)
				continue; // 이미 LIS에 포함되어 있으므로 갱신해도 무의미

			int insertPos = Math.abs(pos) - 1;// 맨뒤 또는 기존원소 대체자리
			LIS[insertPos] = arr[i];

			if (insertPos == size)
				size++;// 하나 추가된 위치가 맨 뒤에 추가되었다면, size는 증가
		}
		System.out.println(size);
	}

}
