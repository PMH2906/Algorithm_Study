package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_10026_적록색약 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, ans1, ans2;
	static char[][] grid;
	static boolean[][] visited;
	static int[][] deltas= {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<int[]> q = new LinkedList<int[]>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		grid=new char[N][N];
		visited = new boolean[N][N];
		
		for(int r =0; r<N;r++) {
			grid[r] = input.readLine().toCharArray();
		}
		for(int r =0; r<N;r++) {
			for(int c=0;c<N;c++) {
				if(!visited[r][c]) {
					q.add(new int[] {r,c});
					ans1++;
					bfs1(grid[r][c]);
				}
			}
		}
		visited = new boolean[N][N];
		for(int r =0; r<N;r++) {
			for(int c=0;c<N;c++) {
				if(!visited[r][c]) {
					q.add(new int[] {r,c});
					ans2++;
					if(grid[r][c]=='B') bfs1(grid[r][c]);
					else bfs2('R', 'G');
				}
			}
		}
		System.out.println(ans1+" "+ans2);
	}

	private static void bfs2(char color1, char color2) {
		while(!q.isEmpty()) {
			int [] now = q.poll();
			for(int d=0;d<deltas.length;d++) {
				int nx = now[0]+deltas[d][0];
				int ny = now[1]+deltas[d][1];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				
				if(!visited[nx][ny] && (grid[nx][ny]==color1||grid[nx][ny]==color2)) {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
				}
			}
		}
	}

	private static void bfs1(char color) {
		while(!q.isEmpty()) {
			int [] now = q.poll();
			for(int d=0;d<deltas.length;d++) {
				int nx = now[0]+deltas[d][0];
				int ny = now[1]+deltas[d][1];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				
				if(!visited[nx][ny] && grid[nx][ny]==color) {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
	}

}
