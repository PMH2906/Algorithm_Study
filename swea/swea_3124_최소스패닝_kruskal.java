package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import s.swea_3124_최소스패닝트리.Node;

public class swea_3124_최소스패닝_kruskal {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int V, E;
	static long totalCost=0;
	static boolean[] visited;
	static List<Node> nodes;
	public static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;
		
		
		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}


		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			
			visited = new boolean[V];
			
			totalCost=0;
			
			for(int e=0;e<E;e++) {
				tokens=new StringTokenizer(input.readLine());
				int from = Integer.parseInt(tokens.nextToken())-1;
				int to = Integer.parseInt(tokens.nextToken())-1;
				int cost = Integer.parseInt(tokens.nextToken());
				
				nodes.add(new Node(from, to, cost));
			}

			kruskal();
			output.append(String.format("#%d %d\n", test_case, totalCost));
		}
		System.out.println(output);
	}

	private static void kruskal() {
		Collections.sort(nodes);
		
		
		
	}

}
