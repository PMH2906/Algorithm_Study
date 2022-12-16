package com.ssafy.prim;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimTest {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int V;
	static int[][] graph;
	static boolean[] visited;
	static int[] minCostTable;
	
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(instr));
		V = Integer.parseInt(input.readLine());
		graph = new int[V][V];
	
		for(int r=0;r<V;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<V;c++) {
				graph[r][c]=Integer.parseInt(tokens.nextToken());
			}
		}
		
//		prim();
		prim2();
	}
	private static void prim2() {
		int totalCost = 0;
		boolean [] visited = new boolean[V];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		//임의의 출발점 선정 - 비용 = 0, 아직 방문 X
		pq.offer(new Node(0,0)); // 임의의 출발점(0번) 이때의 비용 0원
		int nodeCnt =0;
		
		while(!pq.isEmpty()) {
			// 대장데려오기
			Node minCostHead = pq.poll();
			
			// 할 일
			// 이미 방문 된 노드면 skip
			if(visited[minCostHead.no]) {
				continue;
			}
			// 여기서 visited 처리하자!
			visited[minCostHead.no] = true;
			totalCost += minCostHead.cost;
			nodeCnt++;
			
			// 모든 정점 탐색이 완료되면 그만!
			if(nodeCnt==V) break;
			
			// 자식 탐색
			for(int i=0;i<V;i++) {
				if(!visited[i] && graph[minCostHead.no][i]!=0) {
					// 시간 복잡도를 위해 공간복잡도를 희생
					pq.offer(new Node(i, graph[minCostHead.no][i])); // 이 노드는 다음에 바로 다음 방문되는 node가 아니기 때문에 여기서 visited 처리해주면 X
																	 // cost가 가장 적은 node를 방문하기 때문에
																	 // 방문할 지는 뺄때 결정됨~!
				}
			}
		}
		
		System.out.printf("최소비용 : %d%n", totalCost );
	}
	private static void prim() {
		int totalCost = 0;
		visited = new boolean[V];
		minCostTable = new int[V];
		// 초기화 작업 - 모든 노드는 비 MST그룹으로 간주 . 무한대로 초기화
		Arrays.fill(minCostTable, Integer.MAX_VALUE);
		
		// 임의의 출발점 설정 - 비용 =0. 아직 방문한 것은 아님
		minCostTable[0]=0;
		
		// 모든 정점 대상으로 MST 구성
		for(int v=0;v<V;v++) {
			// 가장 비용이 저렴한 정점 찾기
			int minCost = Integer.MAX_VALUE;
			int minCostVertex = -1;
			
			for(int i=0;i<minCostTable.length;i++) {
				//미방문이면서 저렴이 찾기 -> 이번에 MST그룹에 추가될 녀석~~
				if(!visited[i]&&minCostTable[i]<minCost) {
					minCost = minCostTable[i];
					minCostVertex = i;
				}
			}
			//방문 지점이 결정되었다면 가보자
			visited[minCostVertex] = true;
			// 지점 방문 결과 비용을 갱신
			totalCost += minCost;
			
			// 새로운 요원이 포섭할 수 있는 인원 찾아보기
			for(int i=0;i<V;i++) {
				// 미방문, 연결 필요, 갱신 비용이 더 저렴할 것
				if(!visited[i] && graph[minCostVertex][i]!=0 &&  graph[minCostVertex][i]<minCostTable[i]) {
					minCostTable[i]=graph[minCostVertex][i];
				}
			}
		}
		
		
		System.out.printf("최소비용 : %d%n", totalCost );
	}
	static class Node implements Comparable<Node>{
		int no;
		int cost;
		public Node (int no, int cost) {
			this.no = no;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Node [no=" + no + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	// 연결되어있으면 양수
	static String instr = "7\r\n" + 
			"0 32 31 0 0 60 51\r\n" + 
			"32 0 21 0 0 0 0\r\n" + 
			"31 21 0 0 46 0 25\r\n" + 
			"0 0 0 0 34 18 0\r\n" + 
			"0 0 46 34 0 40 51\r\n" + 
			"60 0 0 18 40 0 0\r\n" + 
			"51 0 25 0 51 0 0";

}
