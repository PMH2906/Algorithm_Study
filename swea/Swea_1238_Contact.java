package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Swea_1238_Contact {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static List<Integer>[] contactInfo;
	static Queue<Integer> q;
	static int N, start, result;
	static boolean[] visited;

	private static void call(int start) {
		visited[start]=true;
		for(int i=0;i<contactInfo[start].size();i++) q.add(contactInfo[start].get(i));
		while(q.size()>0) {
			int size = q.size();
			result = Integer.MIN_VALUE;
			for(int s=0;s<size;s++) {
				int from = q.poll();
				for(int i=0;i<contactInfo[from].size();i++) {
					if(!visited[contactInfo[from].get(i)]) {
						visited[contactInfo[from].get(i)]=true;
						q.add(contactInfo[from].get(i));
					}
				}
				result = Math.max(from, result);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		for(int test_case = 1; test_case <=10; test_case++)
		{
			contactInfo = new List[100];
			visited = new boolean[100];
			q = new LinkedList<Integer>();
			for(int i=0;i<100;i++) contactInfo[i] = new LinkedList<>();				
			
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			start = Integer.parseInt(tokens.nextToken())-1;
				 
			tokens = new StringTokenizer(input.readLine());
			for(int n=0;n<N/2;n++) {
				int from = Integer.parseInt(tokens.nextToken())-1;
				int to = Integer.parseInt(tokens.nextToken())-1;
				if(!contactInfo[from].contains(to))contactInfo[from].add(to);
			}
			call(start);
			output.append(String.format("#%d %d\n", test_case, result+1));
		}
		System.out.println(output);

	}

}
