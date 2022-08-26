package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, K;
	static int[][] vSum; // 행 : 배낭의 수/ 열: K까지의 무게
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		vSum = new int[N+1][K+1];
		for(int n=1;n<=N;n++) {
			tokens = new StringTokenizer(input.readLine());
			int W = Integer.parseInt(tokens.nextToken()); // 무게
			int V = Integer.parseInt(tokens.nextToken()); // 가치
			for(int w=0;w<=K;w++) { // K까지의 무게를 모두 확인
				if(w<W)  vSum[n][w] = vSum[n-1][w]; // 확인하는 무게보다 가방의 무게가 가벼우면 현재 가방은 들어가지 못 하므로, 그 전에 확인한 가방의 최대 가치합을 넣어주기
				else vSum[n][w] = Math.max(V+vSum[n-1][w-W], vSum[n-1][w]); // 확인하는 무게가 현재 가방의 무게보다 같거나 크면, 
																			// 현재 가방의 가치와 그 전 가방의 최대 가치합 중 확인하는 무게-현재 가방의 무게의 가치의 합과 그 전 가방의 최대 가치합 중 확인하는 무게의 가치 중 큰 거 선택
			}
		}
		System.out.println(vSum[N][K]);
	}

}
