import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13903_출근 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int R, C, N, map[][], dir[][], ans;
    static PriorityQueue<Info> q = new PriorityQueue<>();
    static boolean visited[][];
    
    public static class Info implements Comparable<Info>{
    	int x, y, cnt;

		public Info(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
    	
		public int compareTo(Info o) {
			return Integer.compare(this.cnt, o.cnt);
		}
    	
    }
    
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for(int r=0;r<R;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(r==0&&map[r][c]==1) {
					q.add(new Info(r,c,0));
					visited[r][c] = true;
				}
			}
		}
		N = Integer.parseInt(input.readLine());
		
		dir = new int [N][2];
		
		for(int n=0;n<N;n++) {
			tokens = new StringTokenizer(input.readLine());
			dir[n][0] = Integer.parseInt(tokens.nextToken());
			dir[n][1] = Integer.parseInt(tokens.nextToken());
		}
		
		bfs();
		
		System.out.println(ans);
	}

	private static void bfs() {
		while(q.size()>0) {
			Info now = q.poll();
			
			if(now.x==R-1) {
				ans = now.cnt;
				break;
			}
			
			for(int n=0;n<N;n++) {
				int nx = now.x+dir[n][0];
				int ny = now.y+dir[n][1];
				
				if(nx<0||nx>=R||ny<0||ny>=C) continue;
				
				if(map[nx][ny]==1&&!visited[nx][ny]) {
					q.add(new Info(nx,ny,now.cnt+1));
					visited[nx][ny] = true;
				}
			}
		}
		
	}

}
