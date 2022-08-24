package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 24.
@see
@git
@performance
@category #dp
@note */
public class BJ_1149_RGB거리{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int [][] cost;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(input.readLine());
		
		cost = new int[N+1][3];
		 
		for(int n=1;n<N+1;n++) {
			tokens = new StringTokenizer(input.readLine());
			int R = Integer.parseInt(tokens.nextToken());
			int G = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			
			cost[n][0] = Math.min(cost[n-1][1],cost[n-1][2])+R; // 현재 집을 R로 선택했을 시, 그 전 집은 R과 겹치면 안되므로 G, B 중 min값 선택해서 더해줌
			cost[n][1] = Math.min(cost[n-1][0],cost[n-1][2])+G; // G도 동일
			cost[n][2] = Math.min(cost[n-1][0],cost[n-1][1])+B; // B도 동일
		}
		// 위 과정을 모든 집을 반복하여 R,G,B를 선택했을 때 최솟값을 고려하여 각각의 최종 값을 구한다 
		Arrays.sort(cost[N]);  // 그 중 가장 작은 값 출력
		System.out.println(cost[N][0]);
		
	}
}