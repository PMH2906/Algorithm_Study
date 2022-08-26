package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_외판원순회2 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, ans;
	static int [][] costInfo;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		ans = Integer.MAX_VALUE;
		costInfo = new int[N][N];
		for(int r=0;r<N;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) costInfo[r][c]=Integer.parseInt(tokens.nextToken());
		}
		
		for(int start=0;start<N;start++) {
			boolean[] visited = new boolean[N];
			visited[start]=true;
			dfs(start, start, visited, 0,0);
		}
		System.out.println(ans);
	}
	private static void dfs(int start, int from, boolean[] visited, int cost, int visitedCnt) {
		if(visitedCnt==N-1) {
			if(costInfo[from][start]!=0) {
				ans = Math.min(cost+costInfo[from][start], ans);				
			}
			return;
		}
		for(int to=0;to<N;to++) {
			if(costInfo[from][to]!=0&&!visited[to]) {
				visited[to] = true;
				dfs(start, to, visited, cost+costInfo[from][to],visitedCnt+1);
				visited[to] = false;
			}
		}
	}

}
