package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1405_미친로봇 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static double per[];
	static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean[][] visited;
	static double ans=0.0;
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		per = new double[4];
		for(int i=0;i<4;i++) per[i] = Double.parseDouble(tokens.nextToken())*0.01;
		
		visited = new boolean[29][29];
		dfs(14,14,0,1.0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int cnt, double result) {
		
		if (cnt==N) {
			ans+=result;
			return;
		}
		visited[r][c] =true;
		for(int d=0;d<4;d++) {
			int nx = r+deltas[d][0];
			int ny = c+deltas[d][1];
			
			if(visited[nx][ny]) continue; // 이미 방문한 곳을 또 방문하는 경로는 단순하지 않은 경로이므로 pass
			
			dfs(nx, ny, cnt+1, result*per[d]);
		}
		visited[r][c] =false;
	}

}

