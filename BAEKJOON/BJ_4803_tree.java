package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_4803_tree {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static ArrayList<Integer>[] graph; //ArrayList<Integer> 타입의 배열
	static int treeCnt;
	static boolean[] visited;
	static int TC=0;
	
	static boolean treeCheck(int Node, int RootNode) {
		visited[Node] = true; //지나간 Node 체크
		for(int i=0;i<graph[Node].size();i++) {
			if((int)graph[Node].get(i)== RootNode) continue; // 연결된 Node중에서 RootNode와 같으면 패스
			if(visited[(int)graph[Node].get(i)]) return false; // 연결된 Node중에서 RootNode를 제외하고 이미 지나간 Node가 있으면 사이클을 생성 -> false리턴.
			visited[(int)graph[Node].get(i)] = true;
			if(!treeCheck((int)graph[Node].get(i),Node)) return false; // 재귀함수 돌면서 사이클을 생성하면(어떤 하나의 재귀함수에서 false를 리턴) -> false
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		while(true) {
			TC +=1;
			tokens=new StringTokenizer(input.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			if(N==0&&M==0) break;
			
			//모든 값 초기화
			graph = new ArrayList[N];
			for(int i=0;i<N;i++) graph[i] = new ArrayList<Integer>();
			treeCnt =0;
			visited = new boolean[N];
			
			for(int i=0;i<M;i++) {
				tokens=new StringTokenizer(input.readLine());
				int node1= Integer.parseInt(tokens.nextToken())-1;
				int node2= Integer.parseInt(tokens.nextToken())-1;
				graph[node1].add(node2); // 각 Node와 연결된 Node정보
				graph[node2].add(node1);
			}
			for(int i=0;i<N;i++) {
				if(visited[i]==false) {
					if(treeCheck(i,i))treeCnt+=1;
				}
			}
			if(treeCnt==0) output.append(String.format("Case %d: No trees.\n", TC));
			else if(treeCnt==1) output.append(String.format("Case %d: There is one tree.\n", TC));
			else output.append(String.format("Case %d: A forest of %d trees.\n", TC,treeCnt));
		}
		System.out.println(output);
	}
}
