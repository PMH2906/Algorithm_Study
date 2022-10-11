package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int N,M,map[][],pass;
	static float fuel;
	static Point taxi, finish[];
	static int[][] deltas= {{-1,0},{0,-1},{0,1},{1,0}};
	static boolean[][] visited;
	
	public static class Point implements Comparable<Point>{
		int x, y, dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Point o) {
			if(this.dist==o.dist) {
				if(this.x==o.x) return Integer.compare(this.y, o.y);
				else return Integer.compare(this.x, o.x);				
			}else return Integer.compare(this.dist, o.dist);
		}
	}
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		fuel = Integer.parseInt(tokens.nextToken());
		// 초기화
		pass=0;
		map = new int[N][N];
		finish = new Point[M];
		
		for(int r=0;r<N;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		tokens = new StringTokenizer(input.readLine());
		taxi = new Point(Integer.parseInt(tokens.nextToken())-1,Integer.parseInt(tokens.nextToken())-1,0);
	
		for(int m=2;m<M+2;m++) {
			tokens = new StringTokenizer(input.readLine());
			map[Integer.parseInt(tokens.nextToken())-1][Integer.parseInt(tokens.nextToken())-1] = m;
			finish[m-2]=new Point(Integer.parseInt(tokens.nextToken())-1, Integer.parseInt(tokens.nextToken())-1, 0);
		}
		
		for(int m=0;m<M;m++) {
			taxi = find_person(taxi.x,taxi.y);
			
			if(taxi==null) { // 벽으로 인해 손님을 찾지 못 할 경우
				System.out.println(-1);
				return;
			}else if(fuel-taxi.dist<=0) { // 손님을 찾다가 연료가 없을 경우
				System.out.println(-1);
				return;
			}
			
			fuel-=taxi.dist;//연료비 줄이기
			int person = map[taxi.x][taxi.y]-2;//사람 번호
			map[taxi.x][taxi.y]=0;//손님자리 빈칸으로 만들기
			
			taxi=find_dest(taxi.x, taxi.y, person);
	
			if(taxi==null) { // 벽으로 인해 목적지를 찾지 못 할 경우
				System.out.println(-1);
				return;
			}else if(fuel-taxi.dist<0) { // 목적지를 찾다가 연료가 없을 경우
				System.out.println(-1);
				return;
			}
			fuel+=taxi.dist;//연료비 충전
		}
		System.out.println((int)fuel);
		
	}
	private static Point find_dest(int x, int y, int person) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new Point(x,y,0));
		visited[x][y]=true;
		
		
		while(q.size()>0) {
			Point now = q.poll();
			if(finish[person].x==now.x&&finish[person].y==now.y) {
				return new Point(now.x, now.y, now.dist);
			}
			for(int d=0;d<deltas.length;d++) {
				int nx = now.x+deltas[d][0];
				int ny = now.y+deltas[d][1];

				if(nx<0||nx>=N||ny<0||ny>=N) continue;
			
				if(map[nx][ny]!=1&&!visited[nx][ny]) {
					q.add(new Point(nx,ny,now.dist+1)); // 벽제외모두탐색
					visited[nx][ny]=true;
				}
			}
	
		}
		
		return null; // 목적지가 없으면 null
	}
	private static Point find_person(int x, int y) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		if(map[x][y]>=1) return new Point(x,y,0);
		q.add(new Point(x,y,0));
		
		visited[x][y]=true;
		while(q.size()>0) {
			int size = q.size();
			
			if(pq.size()>0) {
				return pq.poll();
			}
			
			for(int s=0;s<size;s++) { // 거리가 같은 손님의 x,y행을 비교하기 위해 q의 size만큼 반복해서 손님pq에 넣어줌!
				Point now = q.poll();
				for(int d=0;d<deltas.length;d++) {
					int nx = now.x+deltas[d][0];
					int ny = now.y+deltas[d][1];
					
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					
					if(map[nx][ny]!=1&&!visited[nx][ny]) {
						if(map[nx][ny]>=1) pq.add(new Point(nx,ny,now.dist+1)); // 손님만 넣어주기. 같은 거리에 있는 손님만 pq에 들어감
						q.add(new Point(nx,ny,now.dist+1)); // 벽제외모두 넣어주기
						visited[nx][ny]=true;
					}
				}
			}
		}

		return null; // 손님이 없으면 null;
	}
	
	
}
