package com.ssafy.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간복잡도 : O(N**2)
public class LIS_DP_이진탐색 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(input.readLine());
		int[] arr = new int[N]; // 수
		int[] LIS = new int[N]; // DP:각 원소를 끝으로하는 LIS값

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		int max = 0;
		for (int i = 0; i < N; i++) { // 앞쪽부터 모든 원소기준으로 자신을 끝으로하는 LIS 계산
			LIS[i] = 1; // 자신을 끝으로 하는 길이를 우선 1로 최솟값 잡기!
			for (int j = 0; j < i; j++) { // 현재 원소 앞 원소 체크
				if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			max = Math.max(max, LIS[i]); // 최대 LIS 구하기
		}
		System.out.println(max);
	}

}
