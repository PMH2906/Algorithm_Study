import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16985_Maze {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][][] map = new int[5][5][5];
	static int[] choosed = new int [5], rotationChoosed = new int[5]; 
	static int[][]deltas = {{0,0,-1},{0,0,1},{0,-1,0},{0,1,0},{-1,0,0},{1,0,0}};
	static int[][][] info = {{{0,0,0},{4,4,4}}, {{0,0,4},{4,4,0}}, {{0,4,0},{4,0,4}}, {{0,4,4},{4,0,0}}};
	static int ans = Integer.MAX_VALUE, cnt=0;
	
	public static void main(String[] args) throws IOException {
		for(int h=0;h<5;h++) {
			for(int r=0;r<5;r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0;c<5;c++) {
					map[h][r][c]=Integer.parseInt(tokens.nextToken());
					if(map[h][r][c]==1) cnt++;
				}
			}
		}
		
		// 전부 0(갈 수 없음)일때랑 1(모두 통과되므로 최소이동 수가 12임)일때 고려해서 미리 출력.  
		if(cnt==125) {
			System.out.println(12);
			return;
		}else if(cnt==0) {
			System.out.println(-1);
		}
		
		permutation(0, new boolean[5]);
		if(ans==Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(ans);
	}

	// 쌓을 순서 순열로!!! 
	private static void permutation(int nth, boolean[] visited) {

		if(nth==choosed.length) {
			rotationPermutation(0);
			return;
		}
		for(int i=0;i<5;i++) {
			if(!visited[i]) {
				choosed[nth]=i;
				visited[i]=true;
				permutation(nth+1, visited);
				if(ans==12) return; 
				visited[i]=false;
			}
		}
	}

	// 각도 중복 순열로!! 
	private static void rotationPermutation(int nth) {
		int[][][] newMap = new int[5][5][5];
		if(nth==rotationChoosed.length) {
			for(int i=0;i<choosed.length;i++) {
				newMap[i]=rotate(choosed[i], rotationChoosed[choosed[i]]); //순서대로 쌓을 수 있도록 0번째 판에 놓일 판의 번호를 기반으로, 해당 번호에 맞는 각도로 회전하고 순서대로 쌓아주기 
			}
			dfs(newMap);
			return;
		}
		for(int i=0;i<360;i+=90) {
			rotationChoosed[nth]=i;
			rotationPermutation(nth+1);
			if(ans==12) return; 
		}
		
		
	}

	// dfs 
	private static void dfs(int[][][] newMap) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];

		for(int i=0;i<info.length;i++) { // 출발점과 도착점 고려해주기 
			int[] start = info[i][0];
			int[] end = info[i][1];
			if(newMap[start[0]][start[1]][start[2]]==0) continue; // 출발점이 0이면 들어갈 수 없으므로 탐색 중지 
			
			q.add(new int[] {start[0], start[1], start[2], 0});
			visited[start[0]][start[1]][start[2]]=true;
			
			while(q.size()>0) {
				int[] n = q.poll();
				if(n[0]==end[0]&&n[1]==end[1]&&n[2]==end[2]) { // 도착하면 최소 이동 수 구해주기 
					ans = Math.min(ans, n[3]);
					if(ans==12) return; 
				}
				
				for(int d=0;d<deltas.length;d++) {
					int nh = n[0] + deltas[d][0];
					int nx = n[1] + deltas[d][1];
					int ny = n[2] + deltas[d][2];
					
					if(nh<0||nx<0||ny<0||nh>=5||nx>=5||ny>=5) continue;
					
					if(!visited[nh][nx][ny]&&newMap[nh][nx][ny]==1) {
						q.add(new int[] {nh, nx, ny, n[3]+1});
						visited[nh][nx][ny] = true;
					}
				}
			}
			
		}

	}

	// 판 회전 
	private static int[][] rotate(int nth, int angle) {
		if(angle==90) {
			int[][] newMap = new int[5][5];
			
			for(int r=0;r<5;r++) {
				for(int c=0;c<5;c++) {
					newMap[c][5-r-1]=map[nth][r][c];
				}
			}
			return newMap;
		}else if(angle==180) {
			int[][] newMap = new int[5][5];
			
			for(int r=0;r<5;r++) {
				for(int c=0;c<5;c++) {
					newMap[5-r-1][5-c-1]=map[nth][r][c];
				}
			}
			return newMap;
		}else if(angle==270) {
			int[][] newMap = new int[5][5];
			
			for(int r=0;r<5;r++) {
				for(int c=0;c<5;c++) {
					newMap[5-c-1][r]=map[nth][r][c];
				}
			}
			return newMap;
		}	
		return map[nth];
	}

}
