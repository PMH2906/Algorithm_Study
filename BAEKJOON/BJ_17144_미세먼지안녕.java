package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 26.
@see
@git
@youtube
@performance
@category #
@note */
public class BJ_17144_미세먼지안녕{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, T, ans=0;
	static int [][] room;
	static Queue<Dust> dust = new LinkedList<Dust>();
	static List<Cleaner> cleaner = new LinkedList<Cleaner>();
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	public static class Dust{
		int x, y, amount;

		public Dust(int x, int y, int amount) {
			super();
			this.x = x;
			this.y = y;
			this.amount = amount;
		}	
		public static void move() {
			int size = dust.size();
			for(int i=0;i<size;i++) {
				Dust now = dust.poll();
				int moveAmount = now.amount/5;
				int cnt=0;
			
				if(moveAmount>0) {
					for(int d=0;d<deltas.length;d++) {
						int nx = now.x+deltas[d][0];
						int ny = now.y+deltas[d][1];
						if(nx<0||nx>=R||ny<0||ny>=C) continue;
						if(room[nx][ny]!=-1) {
							room[nx][ny] += moveAmount;
							cnt++;
						}
					}
					room[now.x][now.y] -= moveAmount*cnt;
				}
			}
		}
	}
	public static class Cleaner{
		int x,y;

		public Cleaner(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public static void work(Cleaner c1, Cleaner c2) {
			// 반시계
			for(int r=c1.x-2;r>=0;r--) room[r+1][0]=room[r][0];
			for(int c=1;c<C;c++) room[0][c-1] = room[0][c];
			for(int r=1;r<=c1.x;r++) room[r-1][C-1] = room[r][C-1];
			for(int c=C-2;c>=1;c--) room[c1.x][c+1] = room[c1.x][c];
			room[c1.x][1] =0;
			// 시계
			for(int r=c2.x+2;r<R;r++) room[r-1][0] =room[r][0];
			for(int c=1;c<C;c++) room[R-1][c-1] = room[R-1][c];
			for(int r=R-2;r>=c2.x;r--) room[r+1][C-1] = room[r][C-1];
			for(int c=C-2;c>=1;c--) room[c2.x][c+1]=room[c2.x][c];
			room[c2.x][1]=0;
		}
		
	}
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		room = new int[R][C];
		for(int r=0;r<R;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<C;c++) {
				room[r][c] = Integer.parseInt(tokens.nextToken());
				if(room[r][c]==-1) cleaner.add(new Cleaner(r,c));
				else if(room[r][c]>0) dust.add(new Dust(r,c,room[r][c]));
			}
		}
		Cleaner cleaner1 = cleaner.get(0);
		Cleaner cleaner2 = cleaner.get(1);
		while(--T>=0) {
			Dust.move();
			Cleaner.work(cleaner1, cleaner2);
			if(T==0) break;
			for(int r=0;r<R;r++) {
				for(int c=0;c<C;c++) {
					if(room[r][c]>0) dust.add(new Dust(r,c,room[r][c]));
				}
			}
		
		}		
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(room[r][c]>0) ans+=room[r][c];
			}
		}
		System.out.println(ans);
	}
}