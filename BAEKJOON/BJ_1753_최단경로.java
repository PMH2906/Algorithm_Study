package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int V, E, Start;
	static int[] minWeight;
	static boolean[] visited;
	static List<Node>[] weights;
	
	public static class Node implements Comparable<Node>{
		int nN, w;

		public Node(int nN, int w) {
			super();
			this.nN = nN;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
	}
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		Start = Integer.parseInt(input.readLine())-1;
		weights = new LinkedList[V];
		for(int v=0;v<V;v++ ) weights[v] = new LinkedList<>();
		
		for(int e=0;e<E;e++) {
			tokens = new StringTokenizer(input.readLine());
			int node1 = Integer.parseInt(tokens.nextToken())-1;
			int node2 = Integer.parseInt(tokens.nextToken())-1;
			int w = Integer.parseInt(tokens.nextToken());
			
			weights[node1].add(new Node(node2, w));
		}
//		dijkstra();
		dijkstra2();
		for(int v=0;v<V;v++) {
			if(minWeight[v]==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(minWeight[v]);
		}
	}

	private static void dijkstra() {
		minWeight = new int[V];
		visited = new boolean[V];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[Start] = 0;
		int min,minVertex;
		
		for(int v=0;v<V;v++) {
			min = Integer.MAX_VALUE;
			minVertex = -1;
			for(int nv=0;nv<V;nv++) { // 가장 weight가 작은 node찾기
				if(!visited[nv]&&min>minWeight[nv]) {
					min = minWeight[nv];
					minVertex = nv;
				}
			}
			if(minVertex==-1) continue; // 못 가는 경우도 있으므로!
			visited[minVertex] = true;// 가장 작은 weight node 방문처리
			for(Node nv : weights[minVertex]) { // 해당 node를 거친 weight 보다 원래 weight 작으면 pass, 크면  갱신
				if(!visited[nv.nN]&& minWeight[nv.nN]>minWeight[minVertex]+nv.w) {
					minWeight[nv.nN] = minWeight[minVertex]+nv.w;
				}
			}
		}
	}
	private static void dijkstra2() { // 우선순위큐사용
		minWeight = new int[V];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[Start] = 0;
		int min,minVertex;
		
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선 순위 사용하므로 weight가 작은 node가 앞에 있음
		pq.add(new Node(Start,0)); // 처음 시작할 노드와 weight를 0으로 하여 우선순위 삽입
		while(!pq.isEmpty()) {
			Node now = pq.poll(); //가장 작은 weight를 가진 Node 꺼내기
			
			for(Node nv : weights[now.nN]) { // 해당 node를 거친 weight 보다 원래 weight 작으면 pass, 크면  갱신
				if(minWeight[nv.nN]>minWeight[now.nN]+nv.w) {
					minWeight[nv.nN]=minWeight[now.nN]+nv.w;
					pq.add(new Node(nv.nN, minWeight[nv.nN]));
				}
			}
		}
		
	}

}
