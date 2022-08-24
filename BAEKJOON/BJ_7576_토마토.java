package s;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_토마토 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static boolean[][] visited;
	static int M,N,ans;
	static int[][] tomato;
	static Queue<Position> q = new LinkedList<>();
	static Position[] deltas = {new Position(-1,0),new Position(1,0),new Position(0,-1),new Position(0,1)};
	static boolean check;
	
	public static class Position{
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		ans=-1;
		tomato = new int[M][N];
		visited = new boolean[M][N];
		
		for(int r=0;r<M;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) {
				tomato[r][c]= Integer.parseInt(tokens.nextToken());
				if(tomato[r][c]==1) {
					q.add(new Position(r,c));
					visited[r][c]=true;
				}
				if(tomato[r][c]==-1) visited[r][c]=true;
			}
		}
		bfs();
		check=true;
		for(int r=0;r<M;r++) {
			for(int c=0;c<N;c++) {
				if(visited[r][c]==false) check=false;
			}
		}
		if(check) System.out.println(ans);
		else System.out.println(-1);
	}


	private static void bfs() {
		while(!q.isEmpty()) {
			ans++;
			int size = q.size();
			for(int s=0;s<size;s++) {
				Position now = q.poll();
				for(int d=0;d<deltas.length;d++) {
					int nx = now.x+deltas[d].x;					
					int ny = now.y+deltas[d].y;
					if(nx<0||nx>=M||ny<0||ny>=N) continue;
					
					if(!visited[nx][ny]&&tomato[nx][ny]==0) {
						visited[nx][ny] = true;
						q.add(new Position(nx,ny));
					}
					
				}
			}
		}
	}

}
