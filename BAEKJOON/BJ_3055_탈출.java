package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055_탈출 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, ans;
	static char[][] map;
	static boolean [][][] visited;
	static Queue<Position> sMove = new LinkedList<>(), water=new LinkedList<>();
	static Position dPoint; 
	static int [][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	public static class Position{
		int x,y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map=new char[R][C];
		ans=0; 
		visited = new boolean[R][C][2];
		for(int r=0;r<R;r++) {
			map[r] = input.readLine().toCharArray();
			for(int c=0;c<C;c++) {
				if(map[r][c]=='S') {
					sMove.add(new Position(r,c));
					map[r][c]='.';
					visited[r][c][0]=true;
				}
				else if(map[r][c]=='D') {
					dPoint = new Position(r, c);
				}
				else if(map[r][c]=='*') {
					water.add(new Position(r,c));
					visited[r][c][1]=true;
				}
			}
		}
		
		if(move()) System.out.println(ans++);
		else System.out.println("KAKTUS");
		
	}
	private static boolean move() {
		while(!sMove.isEmpty()) {
			int size = water.size();
			for(int s=0;s<size;s++) {
				Position w = water.poll();
				for(int d=0;d<deltas.length;d++) {
					int nr = w.x+deltas[d][0];
					int nc = w.y+deltas[d][1];
					if(nr<0|| nr>=R || nc<0 || nc>=C) continue;
					if(!visited[nr][nc][1]&&map[nr][nc]=='.') {
						water.add(new Position(nr,nc));
						visited[nr][nc][1]=true;
						map[nr][nc]='*';
					}
				}				
			}
			size = sMove.size();
			ans++;
			for(int s=0;s<size;s++) {
				Position beaver = sMove.poll();
				for(int d=0;d<deltas.length;d++) {
					int nr = beaver.x+deltas[d][0];
					int nc = beaver.y+deltas[d][1];
					if(nr<0|| nr>=R || nc<0 || nc>=C) continue;
					if(nr==dPoint.x&& nc==dPoint.y) return true;
					if(!visited[nr][nc][0]&&map[nr][nc]=='.') {
						sMove.add(new Position(nr,nc));
						visited[nr][nc][0]=true;
					}
				}				
			}
		}
		return false;
	}

}
