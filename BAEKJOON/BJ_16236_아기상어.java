package s;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BJ_16236_아기상어 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, size, ans, eatCnt;
	static int[][] map;
	static boolean [][]visited;
	static PriorityQueue<Position >fish = new PriorityQueue<>();
	static Position[] deletas = {new Position(-1, 0,0),new Position(0, -1,0),new Position(0, 1,0),new Position(1, 0,0)}; // 상 좌 우 하
	
	public static class Position implements Comparable<Position>{
		int x, y,time;
		public Position(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		@Override
		public int compareTo(Position o) { // 물고기의 가장 위에서 왼쪽을 찾기위해 정렬
			if(this.time==o.time) {
				if(this.x==o.x) return this.y-o.y;
				else return this.x-o.x;
			}
			else return this.time-o.time;
		}

	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		size =2; eatCnt=0; ans =0;
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int r=0;r<N;r++) {
			tokens= new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if(map[r][c]==9) {
					fish.add(new Position(r, c,0));
					visited[r][c] = true;
					map[r][c]=0;
				}
			}
		}
		bfs();
		System.out.println(ans);
		
	}

	private static void bfs() {
		while(!fish.isEmpty()) {

				Position now = fish.poll();
				if(map[now.x][now.y]<size&&map[now.x][now.y]!=0) { // 현재 위치에 물고기가 있으면 시간 갱신 및 큐와 visited 초기화
					visited = new boolean[N][N];
					eatCnt++;  
					ans+=now.time;
					map[now.x][now.y]=0; // 물고기 먹으면 0으로
					fish.clear();
					fish.offer(new Position(now.x, now.y,0));
					
					if(eatCnt==size) { // 상어크기와 물고기 수가 같을 경우 상어크기 up
						size++;
						eatCnt=0;
					}
					continue; // 우선순위 큐 사용함으로써 상어크기보다 작은 물고기를 만나면 가장 첫번째 나오는 것이 가장 위에서 왼쪽 위치에 해당하는 물고기임! 해당 위치 물고기 먹고 다시 큐 돌기~~~  
				}
				for(int d=0;d<deletas.length;d++) {
					Position next = new Position(now.x+deletas[d].x, now.y+deletas[d].y, now.time+1);
					
					if(next.x<0 || next.x>=N ||next.y<0||next.y>=N) continue;
					if(!visited[next.x][next.y]&&(map[next.x][next.y]==0||map[next.x][next.y]<=size)) {
						fish.offer(new Position(next.x, next.y, next.time));
						visited[next.x][next.y]=true;
					}
				}
		}
	}

}
