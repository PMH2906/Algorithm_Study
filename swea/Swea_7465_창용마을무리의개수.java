package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Swea_7465_창용마을무리의개수 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, cnt;
	static boolean[] visited;
	static List<Integer>[] people;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			people = new ArrayList[N];
			visited = new boolean[N];
			cnt=0;
			
			for(int n=0;n<N;n++) people[n] = new ArrayList<>();
			
			for(int m=0;m<M;m++) {
				tokens = new StringTokenizer(input.readLine());
				int a= Integer.parseInt(tokens.nextToken())-1;
				int b= Integer.parseInt(tokens.nextToken())-1;
				
				people[a].add(b);
				people[b].add(a);
			}
			for(int n=0;n<N;n++) {
				if(!visited[n]) {
					dfs(n);
					cnt++;
				}
			}
			output.append(String.format("#%d %d\n", test_case, cnt));
		}
		System.out.println(output);
	}

	private static void dfs(int n) {
		if(visited[n]) return;
		
		visited[n] = true;
		for(int num : people[n]) {
			if(!visited[num]) {
//				visited[num] = true;
				dfs(num);
			}
		}
	}

}
