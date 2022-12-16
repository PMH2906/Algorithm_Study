package com.ssafy.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Floyd {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static final int INF = 9999999;
	static int N, dist[][];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());
		dist = new int[N][N];
		
		for(int i=0;i<N;i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=0;j<N;j++) {
				dist[i][j]=Integer.parseInt(tokens.nextToken());
				if(i!=j && dist[i][j]==0) { // 자기 자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 넣기
					dist[i][j]=INF;
				}
				
			}
		}
		// 출발지 -> 경유지 -> 목적지로 3중 루프 돌리면 오답
		// 경유지 -> 출발지 -> 목적지로 3중 루프 돌려야 정답
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
				for(int j=0;j<N;j++) {
					if(i==j||k==j) continue; // 출발지가 목적지와 같거나, 경유지가 목적지와 같으면 패스 
					if(dist[i][j]>dist[i][k]+dist[k][j]) {
						dist[i][j]=dist[i][k]+dist[k][j];
					}
				}
			}
		}
		
		// 각 목적지까지 가장 빠른 거리
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(dist[i][j]+"\t");
			}
			System.out.println();
		}
		
	}

}
/**
 4
0 2 0 15
0 0 10 4
3 0 0 0
0 0 7 0
 **/
