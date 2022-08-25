package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2293_동전1 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, K, V; 
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		dp = new int[N+1][K+1];

		for(int n=1;n<=N;n++) { // 모든 동전 가치 확인
			V = Integer.parseInt(input.readLine()); // 가치
			for(int k=1;k<=K;k++) { // 원하는 가치의 합까지 확인
				if(k<V) dp[n][k] = dp[n-1][k]; // 현재 가치보다 확인하는 가치가 작으면 그 전에 확인한 동전 경우의 수 넣어주기
				else if(k==V) dp[n][k]=dp[n-1][k]+1; // 같으면 경우의 수는 그 전에 확인한 동전의 경우의 수에서 한 가지 경우만 추가되므로 +1
				else dp[n][k] = dp[n-1][k]+dp[n][k-V]; // 가치가 더 크면, 그 전에 확인한 동전의 경우의 수와 현재 동전에서의 경우의 수에서 확인하는 가치-현재 가치의 경우의 수 합치기
			}
		}
		System.out.println(dp[N][K]);
	}

}
