package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13549_숨바꼭질3 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,K;
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[] visited = new boolean[100001];
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		K=Integer.parseInt(tokens.nextToken());
		if(N>K) {
			System.out.println(N-K); 
			return;
		}
		else if(N==K){
			System.out.println(0); 
			return;
		}
		else {
			q.add(new int[] {N,0});
			while(q.size()>0) {
				int[] info = q.poll();
				N=info[0];
				visited[N]=true;
				if(N==K) min=Math.min(min,info[1]); // 해당 위치에서의 가장 작은 소요시간
				
				if(N*2<100001 && !visited[N*2]) q.add(new int[] {N*2,info[1]}); 
				if(N+1<100001 && !visited[N+1]) q.add(new int[] {N+1,info[1]+1});
				if(N-1>=0 && !visited[N-1]) q.add(new int[] {N-1,info[1]+1});

			}
		}
		System.out.println(min);
	}

}
