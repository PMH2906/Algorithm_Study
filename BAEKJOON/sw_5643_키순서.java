package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_5643_키순서 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static List<Integer>[] list;
	static int T,N,M,check,ans=0;
	static boolean[] visited;
	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(input.readLine());
			M = Integer.parseInt(input.readLine());
			list = new LinkedList[N+1];
			for(int i=0;i<=N;i++) {
				list[i] = new LinkedList<>();
			}
			for(int m=0;m<M;m++) {
				tokens = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				list[a].add(b);
				
			}
			for(int n=1;n<=N;n++) {
				visited=new boolean[N+1];
				check=0;
				for(int i=0;i<list[n].size();i++) {
					visited[list[n].get(i)]=true;
					check+=1;
				}
				bfs(n);
				if(check==N-1) {
					System.out.println(n);
					ans+=1;
				}
			}
			output.append(String.format("#%d %d", test_case, ans));
		}
		System.out.println(output);
	}
	private static void bfs(int root) {
//		for(int n=1;n<=N;n++) {
//			Queue<Integer> q = new LinkedList<Integer>();
//			int m=0;
//			if(!visited[n]&&n!=root) {
//				q.add(n);
//				while(q.size()>0) {
//					m+=1;
//					int now = q.poll();
//					for(int i=0;i<list[now].size();i++) {
//						if(!visited[list[now].get(i)]) {
//							if(list[now].get(i)==root) {
//								check+=m;
//								break;
//							}
//							q.add(list[now].get(i));
//							visited[list[now].get(i)]=true;
//						}
//					}
//				}
//			}
//		}
		for(int n=1;n<=N;n++) {
			if(!visited[n]) {
				
			}
		}
	}

}
/*
1
6
6
1 5
3 4
5 4
4 2
4 6
5 2
 * */
