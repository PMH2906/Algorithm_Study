import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1240_노드사이의거리_DSF {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int N,M,ans;
	
	static List<Node>[] nodes;
	
	static class Node {
		int dest;
		int dist;
		
		public Node(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		nodes = new ArrayList[N+1];
		
		for(int n=0;n<=N;n++) {
			nodes[n] = new ArrayList<Node>();
		}
		
		for(int n=0;n<N-1;n++) {
			tokens = new StringTokenizer(input.readLine());
			
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			int dist = Integer.parseInt(tokens.nextToken());
			
			nodes[to].add(new Node(from, dist));
			nodes[from].add(new Node(to, dist));
		}
		
		for(int m=0;m<M;m++) {
			tokens = new StringTokenizer(input.readLine());
			
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			
			dfs(to, from, -1, 0);
			
			output.append(ans+"\n");
			
		}
		System.out.println(output);
	}

	private static void dfs(int to, int from, int pre, int dist) {
		if(to == from) {
			ans = dist;
		}
		for(Node nextNode : nodes[from]) {
			if(nextNode.dest!=pre) {
				dfs(to, nextNode.dest,from, dist+nextNode.dist);
			}
		}
		
	}

}
