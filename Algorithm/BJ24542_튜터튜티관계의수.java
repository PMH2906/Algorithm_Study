import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//문제의 그래프는 여러 개의 트리로 이루어져 있다. 따라서, 총 경우의 수(정답)는 각 트리의 노드 수를 모두 곱한 값이다.
//그래프는 무조건 사이클이 없음!!!! 
public class BJ24542_튜터튜티관계의수 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, gCnt=0;
	static long ans=1;
	static List<Integer>[] node;
	static boolean visited[];
	public static void main(String[] args) throws IOException {

		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		node = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=0;i<=N;i++) {
			node[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			tokens = new StringTokenizer(input.readLine());
			int f = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			node[f].add(s);
			node[s].add(f);
		}
		
		// 모든 노드 탐색 
		for(int i=1;i<=N;i++) {
			if(!visited[i]) {
				visited[i]=true;
				ans = (ans *dfs(i))%1000000007;
			}
		}
		System.out.println(ans);
	}
	// 연결된 트리마다의 노드 갯수 탐색 
	private static int dfs(int nodeNum) {
		gCnt=1; // 하나의 노드 들어가면 하나의 갯수 세어주기 
		for(int next : node[nodeNum]) {
//			System.out.println(next);
			if(!visited[next]) {
				visited[next]=true;
				gCnt+=dfs(next); // 리턴되면서 모든 노드 갯수 더해주기 
			}
		}
//		System.out.println("cng"+gCnt);
		return gCnt; // 하나의 트리의 총 노드 갯수 리턴  
	}

}
