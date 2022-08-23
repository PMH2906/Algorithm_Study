package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_13023_ABCDE {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, ans;
	static List<Integer> [] nodes;
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		nodes = new LinkedList[N];
		ans=0;
		for(int n=0;n<N;n++) nodes[n] = new LinkedList<Integer>();
		
		for(int m=0;m<M;m++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			nodes[a].add(b);
			nodes[b].add(a);
		}
		

		for(int n=0;n<N;n++) {
			if(dfs(n,0,new boolean[N])) break;
		}
		System.out.println(ans);
	}

	private static boolean dfs(int start, int d, boolean[] visited) {

		if(d==4) {
			ans = 1;
			return true;
		}
		visited[start]=true;
		
		for(int adj: nodes[start]) {
			if(visited[adj]) continue;
			if(dfs(adj,d+1,visited)) return true;
		}
		visited[start] = false;
		
		return false;	
	}
	

}
